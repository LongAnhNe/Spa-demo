package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.PasswordResetToken;
import com.spa.Online.Spa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
}
