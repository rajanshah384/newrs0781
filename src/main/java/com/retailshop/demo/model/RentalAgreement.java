package com.retailshop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Integer rentalDays;
    private String checkoutDate;
    private String dueDate;
    private Double dailyRentalCharges;
    private Integer chargeDays;
    private Double preDiscountedCharge;
    private Integer discountPercentage;
    private Double discountAmount;
    private Double finalCharge;

	@Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        return str.append("RentalAgreement : \n")
                .append("Tool Code: ").append(toolCode).append("\n")
                .append("Tool Type: ").append(toolType).append("\n")
                .append("Tool Brand: ").append(toolBrand).append("\n")
                .append("Rental Days: ").append(rentalDays).append("\n")
                .append("Checkout Date: ").append(checkoutDate).append("\n")
                .append("Due Date: ").append(dueDate).append("\n")
                .append("Daily Rental Charges: ").append("$"+dailyRentalCharges).append("\n")
                .append("Charge Days: ").append(chargeDays).append("\n")
                .append("Pre-Discounted Charge: ").append(preDiscountedCharge+"%").append("\n")
                .append("Discount Percentage: ").append(discountPercentage+"%").append("\n")
                .append("Discount Amount: ").append("$"+discountAmount).append("\n")
                .append("Final Charge: ").append("$"+finalCharge).append("\n")
                .toString();
    }
}
