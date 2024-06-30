package com.retailshop.demo;

import java.time.LocalDate;

import com.retailshop.demo.model.CheckoutRequest;
import com.retailshop.demo.model.RentalAgreement;
import com.retailshop.demo.service.RentalServiceImpl;


public class DemoApplication {

	public static void main(String[] args) {
			
		CheckoutRequest request = new CheckoutRequest("LADW", 20, 10, LocalDate.parse("2024-06-08"));
		new DemoApplication().checkout(request);
	}
	
	public RentalAgreement checkout(CheckoutRequest checkoutRequest) {
        //log.info("Start: RentalAgreement");
        return new RentalServiceImpl().checkoutTool(checkoutRequest);
    }

}
