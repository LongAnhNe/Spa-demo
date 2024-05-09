package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.OrderItem;
import com.spa.Online.Spa.model.Product;
import com.spa.Online.Spa.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setQuantity(orderItem.getQuantity());
        return orderItemRepository.save(newOrderItem);
    }

    @Override
    public Boolean updateProductQuantity(OrderItem orderItem) {
        try {
            Product product = productService.findById(orderItem.getProduct().getId());
            int newQuantity = product.getQuantity() - orderItem.getQuantity();
            this.productService.updateQuantity(product.getId(), newQuantity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
