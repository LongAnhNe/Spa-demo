package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Notification;
import com.spa.Online.Spa.model.Order;
import com.spa.Online.Spa.model.User;

import java.util.List;

public interface NotificationService {

    public Notification sendOrderStatusNotification(Order order);
   // public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(User user, String message);

    public List<Notification> findUsersNotification(Long userId);

}
