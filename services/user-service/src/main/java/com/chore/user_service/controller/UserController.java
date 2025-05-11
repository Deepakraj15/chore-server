package com.chore.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chore.user_service.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/checkUser")
    public ResponseEntity<Object> checkIfUserPresentOrNot(@RequestParam String username,
            @RequestParam String password) {
        boolean exists = userService.validateUser(username, password);
        if (exists) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<String> createNewUser(@RequestHeader String username, @RequestHeader String password) {
       if (userService.createUser(username, password)) {
        return ResponseEntity.status(201).build(); 
    } else {
        return ResponseEntity.status(409).build();
    }
    }
    
    
}
    

