package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Notification;
import com.spa.Online.Spa.model.Order;
import com.spa.Online.Spa.model.User;
import com.spa.Online.Spa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class NotificationServiceImplement implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification sendOrderStatusNotification(Order order) {
        Notification notification = new Notification();
        notification.setMessage("your order is "+order.getOrderStatus()+ " order id is - "+order.getId());
        notification.setCustomer(order.getCustomer());
        notification.setSentAt(new Date());
        return notificationRepository.save(notification);
    }

    @Override
    public void sendPromotionalNotification(User user, String message) {

    }

    @Override
    public List<Notification> findUsersNotification(Long userId) {
        return notificationRepository.findByCustomerId(userId) ;
    }
}
