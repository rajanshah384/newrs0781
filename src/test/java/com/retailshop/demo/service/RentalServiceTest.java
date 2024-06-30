package com.retailshop.demo.service;

import com.retailshop.demo.exceptions.InvalidInputException;
import com.retailshop.demo.model.CheckoutRequest;
import com.retailshop.demo.model.RentalAgreement;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalServiceTest {

    private RentalService rentalService = new RentalServiceImpl();

    @Test
    void testInvalidDiscountPercentage() {

        Assertions.assertThrows(InvalidInputException.class, () -> rentalService.checkoutTool(CheckoutRequest.builder()
                .toolCode("JAKR")
                .rentalDayCount(5)
                .discountPercent(101)
                .checkoutDate(LocalDate.of(2015, 9, 03))
                .build()));
    }

    @Test
    void testSecondCaseWeekendChargeYesJuly4OnSaturday() {
        RentalAgreement rentalAgreement = rentalService.checkoutTool(CheckoutRequest.builder()
                .toolCode("LADW")
                .rentalDayCount(3)
                .discountPercent(10)
                .checkoutDate(LocalDate.of(2020, 7, 02))
                .build());

        Assertions.assertEquals(rentalAgreement.getToolCode(), "LADW");
        Assertions.assertEquals(rentalAgreement.getToolType(), "Ladder");
        Assertions.assertEquals(rentalAgreement.getToolBrand(), "Werner");
        Assertions.assertEquals(rentalAgreement.getRentalDays(), 3);
        Assertions.assertEquals(rentalAgreement.getCheckoutDate(), "07/02/20");
        Assertions.assertEquals(rentalAgreement.getDueDate(), "07/05/20");
        Assertions.assertEquals(rentalAgreement.getDailyRentalCharges(), 1.99);
        Assertions.assertEquals(rentalAgreement.getChargeDays(), 2);
        Assertions.assertEquals(rentalAgreement.getPreDiscountedCharge(), 3.98);
        Assertions.assertEquals(rentalAgreement.getDiscountPercentage(), 10);
        Assertions.assertEquals(rentalAgreement.getDiscountAmount(), 0.4);
        Assertions.assertEquals(rentalAgreement.getFinalCharge(), 3.58);
    }

    @Test
    void testThirdCase() {
        RentalAgreement ra = rentalService.checkoutTool(new CheckoutRequest("CHNS", 5, 25, LocalDate.of(2015, 07, 02)));
        Assertions.assertEquals(ra.getToolCode(), "CHNS");
        Assertions.assertEquals(ra.getToolType(), "Chainsaw");
        Assertions.assertEquals(ra.getToolBrand(), "Stihl");
        Assertions.assertEquals(ra.getRentalDays(), 5);
        Assertions.assertEquals(ra.getCheckoutDate(), "07/02/15");
        Assertions.assertEquals(ra.getDueDate(), "07/07/15");
        Assertions.assertEquals(ra.getDailyRentalCharges(), 1.49);
        Assertions.assertEquals(ra.getChargeDays(), 3);
        Assertions.assertEquals(ra.getPreDiscountedCharge(), 4.47);
        Assertions.assertEquals(ra.getDiscountPercentage(), 25);
        Assertions.assertEquals(ra.getDiscountAmount(), 1.12);
        Assertions.assertEquals(ra.getFinalCharge(), 3.35);
    }
//
    @Test
    void testFourthCase() {
        RentalAgreement ra = rentalService.checkoutTool(new CheckoutRequest("JAKD", 6, 0, LocalDate.of(2015, 9, 03)));

        Assertions.assertEquals(ra.getToolCode(), "JAKD");
        Assertions.assertEquals(ra.getToolType(), "Jackhammer");
        Assertions.assertEquals(ra.getToolBrand(), "DeWalt");
        Assertions.assertEquals(ra.getRentalDays(), 6);
        Assertions.assertEquals(ra.getCheckoutDate(), "09/03/15");
        Assertions.assertEquals(ra.getDueDate(), "09/09/15");
        Assertions.assertEquals(ra.getDailyRentalCharges(), 2.99);
        Assertions.assertEquals(ra.getChargeDays(), 3);
        Assertions.assertEquals(ra.getPreDiscountedCharge(), 8.97);
        Assertions.assertEquals(ra.getDiscountPercentage(), 0);
        Assertions.assertEquals(ra.getDiscountAmount(), 0.0);
        Assertions.assertEquals(ra.getFinalCharge(), 8.97);
    }

    @Test
    void testFifthCase() {

        RentalAgreement ra = rentalService.checkoutTool(new CheckoutRequest("JAKR", 9, 0, LocalDate.of(2015, 7, 02)));
        Assertions.assertEquals(ra.getToolCode(), "JAKR");
        Assertions.assertEquals(ra.getToolType(), "Jackhammer");
        Assertions.assertEquals(ra.getToolBrand(), "Ridgid");
        Assertions.assertEquals(ra.getRentalDays(), 9);
        Assertions.assertEquals(ra.getCheckoutDate(), "07/02/15");
        Assertions.assertEquals(ra.getDueDate(), "07/11/15");
        Assertions.assertEquals(ra.getDailyRentalCharges(), 2.99);
        Assertions.assertEquals(ra.getChargeDays(), 6);
        Assertions.assertEquals(ra.getPreDiscountedCharge(), 17.94);
        Assertions.assertEquals(ra.getDiscountPercentage(), 0);
        Assertions.assertEquals(ra.getDiscountAmount(), 0.0);
        Assertions.assertEquals(ra.getFinalCharge(), 17.94);
    }

    @Test
    void testSixthCase() {

        RentalAgreement ra = rentalService.checkoutTool(new CheckoutRequest("JAKR", 4, 50, LocalDate.of(2020, 7, 02)));
        Assertions.assertEquals(ra.getToolCode(), "JAKR");
        Assertions.assertEquals(ra.getToolType(), "Jackhammer");
        Assertions.assertEquals(ra.getToolBrand(), "Ridgid");
        Assertions.assertEquals(ra.getRentalDays(), 4);
        Assertions.assertEquals(ra.getCheckoutDate(), "07/02/20");
        Assertions.assertEquals(ra.getDueDate(), "07/06/20");
        Assertions.assertEquals(ra.getDailyRentalCharges(), 2.99);
        Assertions.assertEquals(ra.getChargeDays(), 1);
        Assertions.assertEquals(ra.getPreDiscountedCharge(), 2.99);
        Assertions.assertEquals(ra.getDiscountPercentage(), 50);
        Assertions.assertEquals(ra.getDiscountAmount(), 1.5);
        Assertions.assertEquals(ra.getFinalCharge(), 1.49);
    }
}
