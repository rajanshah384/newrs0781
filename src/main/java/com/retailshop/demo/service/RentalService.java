package com.retailshop.demo.service;

import com.retailshop.demo.model.CheckoutRequest;
import com.retailshop.demo.model.RentalAgreement;

public interface RentalService {
    RentalAgreement checkoutTool(CheckoutRequest checkoutRequest);
}
