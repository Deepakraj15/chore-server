package com.chore.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chore.auth_service.pojo.request.AuthRequest;
import com.chore.auth_service.pojo.response.AuthResponse;
import com.chore.auth_service.utility.JwtUtility;

@Service
public class AuthService {
    @Autowired
    private AuthResponse authResponse;

    public AuthResponse generateAuthTokenAndRefreshToken(AuthRequest authRequest) {
        if (validateAuthRequest(authRequest)) {
           authResponse.setAuthToken(JwtUtility.generateAccessToken(authRequest.getUsername()));
           authResponse.setRefreshToken(JwtUtility.generateRefreshToken(authRequest.getUsername()));
        }
        return authResponse;
    }
    
    public boolean validateAuthRequest(AuthRequest authRequest) {
        return true;
    }
}
