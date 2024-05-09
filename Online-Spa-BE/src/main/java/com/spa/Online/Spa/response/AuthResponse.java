package com.spa.Online.Spa.response;

import com.spa.Online.Spa.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
