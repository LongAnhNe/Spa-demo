package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItermRepository extends JpaRepository<OrderItem,Long> {
}
