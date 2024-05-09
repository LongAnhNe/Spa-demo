package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Order;
import com.spa.Online.Spa.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse generatePaymentLink(Order order) throws StripeException;
}
