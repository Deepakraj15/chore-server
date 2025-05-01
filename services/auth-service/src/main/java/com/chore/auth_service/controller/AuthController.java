package com.chore.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chore.common.pojo.request.AuthRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/auth-token")
    public String getAuthToken(@RequestBody AuthRequest authRequest) {
        return "dfs";
    }
    @GetMapping("getStatus")
    public String getStatus() {
        return "Status is up";
    }
    
}
