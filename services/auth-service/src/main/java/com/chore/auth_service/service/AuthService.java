package com.chore.auth_service.service;

import org.springframework.stereotype.Service;

import com.chore.auth_service.pojo.request.AuthRequest;
import com.chore.auth_service.pojo.response.AuthResponse;
import com.chore.auth_service.utility.JwtUtility;

@Service
public class AuthService {
    
    private final WebClientService webClientService;

    public AuthService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }
    public AuthResponse generateAuthTokenAndRefreshToken(AuthRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        if (validateWithUserService(authRequest)) {
                authResponse.setAuthToken(JwtUtility.generateAccessToken(authRequest.getUsername()));
                authResponse.setRefreshToken(JwtUtility.generateRefreshToken(authRequest.getUsername()));
            } else {
                // Handle failed authentication (throw exception or return error response)
                throw new RuntimeException("Invalid credentials");
            }

            return authResponse;
        }

    public boolean validateWithUserService(AuthRequest authRequest) {
        return webClientService.checkCredentials(authRequest.getUsername(), authRequest.getPassword());
    }
}
