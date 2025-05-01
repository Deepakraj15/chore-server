package com.chore.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chore.auth_service.service.AuthService;
import com.chore.auth_service.pojo.request.AuthRequest;
import com.chore.auth_service.pojo.response.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    @PostMapping("/auth-token")
    public ResponseEntity<AuthResponse> getAuthToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.generateAuthTokenAndRefreshToken(authRequest));
    }
    @GetMapping("/getStatus")
    public String getStatus() {
        return "Status is up";
    }
    
}
