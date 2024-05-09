package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustumer_Id(Long userId);
}
