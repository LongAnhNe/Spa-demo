package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.CartException;
import com.spa.Online.Spa.exception.OderException;
import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.Order;
import com.spa.Online.Spa.model.User;
import com.spa.Online.Spa.request.CreateOrderRequest;
import com.spa.Online.Spa.request.CreateSpaRequest;
import com.spa.Online.Spa.response.PaymentResponse;
import com.stripe.exception.StripeException;

import java.util.List;

public interface OrderService {
    public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, CartException, StripeException;
    public Order updateOrder(Long orderId, String orderStatus) throws OderException;
    public List<Order> getOrderOfSpa(Long productId,String orderStatus) throws OderException;

    public List<Order> getUserOrders(Long userId) throws OderException;
    public void cancelOder(Long orderId) throws OderException;

}
