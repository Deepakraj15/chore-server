package com.chore.auth_service.pojo.response;   

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class AuthResponse {
    private String authToken;
    private String refreshToken;
}
