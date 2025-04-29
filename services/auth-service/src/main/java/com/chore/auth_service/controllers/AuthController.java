package com.chore.auth_service.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("getStatus")
    public String getStatus() {
        return "Status is up";
    }
    
}
