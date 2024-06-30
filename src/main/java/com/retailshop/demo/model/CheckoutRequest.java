package com.retailshop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CheckoutRequest {
    private String toolCode;
    private int rentalDayCount;
    private int discountPercent;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy")
    private LocalDate checkoutDate;
}
