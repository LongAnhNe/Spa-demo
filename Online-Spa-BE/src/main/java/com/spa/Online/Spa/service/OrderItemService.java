package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.OrderItem;

public interface OrderItemService {
    public OrderItem createOrderItem(OrderItem orderItem);
    public Boolean updateProductQuantity(OrderItem orderItem);
}
