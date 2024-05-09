package com.spa.Online.Spa.request;

import com.spa.Online.Spa.model.Address;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long restaurantId;

    private Address deliveryAddress;
}
