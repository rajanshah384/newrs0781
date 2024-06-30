package com.retailshop.demo.service;

import com.retailshop.demo.exceptions.InvalidInputException;
import com.retailshop.demo.model.CheckoutRequest;
import com.retailshop.demo.model.RentalAgreement;
import com.retailshop.demo.model.Tool;
import com.retailshop.demo.model.ToolsCharge;
import com.retailshop.demo.utils.FileUtils;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RentalServiceImpl implements RentalService {

    private static final Logger log = Logger.getLogger(RentalServiceImpl.class.getName());

    private static Map<String, Tool> toolsMap = new HashMap<>();
    private static Map<String, ToolsCharge> toolsChargeMap = new HashMap<>();

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YY");

    static {
        toolsMap = FileUtils.loadToolsData();
        toolsChargeMap = FileUtils.loadToolsChargeData();
    }

    @Override
    public RentalAgreement checkoutTool(CheckoutRequest checkoutRequest) throws InvalidInputException {
        //validate
        validate(checkoutRequest);

        // get the data for the given tool code.
        Tool tool = getTool(checkoutRequest);
        ToolsCharge toolCharge = getToolsCharge(tool);

        //derive the actual number days to charge excluding holidays and weekends
        Integer noOfHolidays = CalendarUtility.getInstance().noOfHolidaysBetween(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDayCount());
        Integer noOfWeekends = CalendarUtility.getInstance().noOfWeekendsBetween(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDayCount());
        Integer noOfWeekDays = checkoutRequest.getRentalDayCount() - noOfWeekends - noOfHolidays;
        log.info("noOfWeekends :{}, noOfHolidays: " + noOfWeekends + ", " + noOfHolidays);
        Integer chargeDays = noOfWeekDays + (toolCharge.isWeekendCharge() ? noOfWeekends : 0) + (toolCharge.isHolidayCharge() ? noOfHolidays : 0);
        log.info("Total Number of days considered to charge:{}" + chargeDays);
        Double totalCharge = chargeDays * (toolCharge.getDailyCharge());
        Double totalChargeRounded = Double.parseDouble(df.format(totalCharge));

        Double discountedAmount = totalCharge * checkoutRequest.getDiscountPercent() / 100.0;
        Double doscountedAmountRounded = Double.parseDouble(df.format(discountedAmount));

        Double finalCharge = Double.parseDouble(df.format(totalChargeRounded - doscountedAmountRounded));

        //Prepare the Response
        return new RentalAgreement(checkoutRequest.getToolCode(), tool.getToolType(), tool.getBrand(), checkoutRequest.getRentalDayCount(),
                dateFormatter.format(checkoutRequest.getCheckoutDate()).toString(),
                dateFormatter.format(checkoutRequest.getCheckoutDate().plusDays(checkoutRequest.getRentalDayCount())),
                toolCharge.getDailyCharge(), chargeDays, totalChargeRounded, checkoutRequest.getDiscountPercent(),
                doscountedAmountRounded, finalCharge);
    }

    private ToolsCharge getToolsCharge(Tool tool) {
        ToolsCharge toolCharge = toolsChargeMap.get(tool.getToolType());
        if (toolCharge == null) throw new InvalidInputException("Invalid Charge - data unavailable");
        return toolCharge;
    }

    private Tool getTool(CheckoutRequest checkoutRequest) {
        Tool tool = toolsMap.get(checkoutRequest.getToolCode());
        if (tool == null) throw new InvalidInputException("Invalid tool code");
        return tool;
    }

    private void validate(CheckoutRequest checkoutRequest) {
        if (checkoutRequest.getRentalDayCount() <= 1) {
            log.info("Invalid Rental Day Count. Count of Rental Days should be more than 1 day.");
            throw new InvalidInputException("Invalid Rental Day Count. Count of Rental Days should be more than 1 day.");
        }
        if (checkoutRequest.getDiscountPercent() < 0 || checkoutRequest.getDiscountPercent() > 100) {
            log.info("Invalid Discount Percentage. Discount Percentage should be between 0-100.");
            throw new InvalidInputException("Invalid Discount Percentage. Discount Percentage should be between 0-100.");
        }
    }
}
