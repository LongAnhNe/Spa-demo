package com.spa.Online.Spa.service;


import com.spa.Online.Spa.model.PasswordResetToken;

public interface PasswordResetTokenService {

    public PasswordResetToken findByToken(String token);
    public void delete(PasswordResetToken resetToken);
}
