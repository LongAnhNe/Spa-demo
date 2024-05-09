package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.PasswordResetToken;
import com.spa.Online.Spa.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImp implements PasswordResetTokenService{
    @Autowired
    private PasswordResetTokenRepository pas;
    @Override
    public PasswordResetToken findByToken(String token) {
        PasswordResetToken passwordResetToken = pas.findByToken(token);
        return passwordResetToken;
    }

    @Override
    public void delete(PasswordResetToken resetToken) {
        pas.delete(resetToken);
    }
}
