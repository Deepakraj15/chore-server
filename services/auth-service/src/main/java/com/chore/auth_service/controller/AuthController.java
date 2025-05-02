package com.chore.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chore.auth_service.service.AuthService;
import com.chore.common.exception.AuthenticationException;
import com.chore.common.pojo.request.AuthTokenRequest;
import com.chore.common.pojo.response.AuthTokenResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    @PostMapping("/auth-token")
    public ResponseEntity<AuthTokenResponse> getAuthToken(@RequestBody AuthTokenRequest authRequest) {
        try{
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.generateAuthTokenAndRefreshToken(authRequest));
        }catch(AuthenticationException e){
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @GetMapping("/getStatus")
    public String getStatus() {
        return "Status is up";
    }
    
}
