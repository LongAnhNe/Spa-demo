package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.CartException;
import com.spa.Online.Spa.exception.OderException;
import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.*;
import com.spa.Online.Spa.repository.AddressRepository;
import com.spa.Online.Spa.repository.OrderItemRepository;
import com.spa.Online.Spa.repository.OrderRepository;
import com.spa.Online.Spa.repository.UserRepository;
import com.spa.Online.Spa.request.CreateOrderRequest;
import com.spa.Online.Spa.response.PaymentResponse;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentService paymentSerive;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private OrderItemService orderItemService;


    @Override
    public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, CartException, StripeException {
        Address shippAddress = order.getDeliveryAddress();


        Address savedAddress = addressRepository.save(shippAddress);

        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
        }
        System.out.println("user addresses --------------  " + user.getAddresses());

        userRepository.save(user);

/*
        Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
        if(restaurant.isEmpty()) {
            throw new RestaurantException("Restaurant not found with id "+order.getRestaurantId());
        }
*/

        Order createdOrder = new Order();

        createdOrder.setCustomer(user);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setCreateAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        /*        createdOrder.setRestaurant(restaurant.get());*/

        Cart cart = cartService.findCardByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            /*            orderItem.setIngredients(cartItem.getIngredients());*/
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTolalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.caculateCartTotal(cart);

        createdOrder.setTotalAmmount(totalPrice);
        /*        createdOrder.setRestaurant(restaurant.get());*/

        createdOrder.setItems(orderItems);
        Order savedOrder = orderRepository.save(createdOrder);

/*
        restaurant.get().getOrders().add(savedOrder);

        restaurantRepository.save(restaurant.get());
*/


        PaymentResponse res = paymentSerive.generatePaymentLink(savedOrder);
        return res;

    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws OderException {
        Order order=findOrderById(orderId);

        System.out.println("--------- "+orderStatus);

        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            Notification notification=notificationService.sendOrderStatusNotification(order);
            return orderRepository.save(order);
        }
//        completed
        if (orderStatus.equals("COMPLETED")) {
            order.getItems().forEach(orderItem -> {
                this.orderItemService.updateProductQuantity(orderItem);
            });
        }
        else throw new OderException("Please Select A Valid Order Status");


    }

    @Override
    public List<Order> getOrderOfSpa(Long productId, String orderStatus) throws OderException {
        return null;
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws OderException {
        List<Order> orders=orderRepository.findAllUserOrders(userId);
        return orders;
    }

    @Override
    public void cancelOder(Long orderId) throws OderException {
        Order order = findOrderById(orderId);
        if (order == null) {
            throw new OderException("Order not found with the id " + orderId);
        }

        orderRepository.deleteById(orderId);

    }

    private Order findOrderById(Long orderId) throws OderException {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) return order.get();

        throw new OderException("Order not found with the id "+orderId);
    }
}
