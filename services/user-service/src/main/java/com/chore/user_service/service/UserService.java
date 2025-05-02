package com.chore.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chore.user_service.entity.User;
import com.chore.user_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private PasswordService passwordService;
    private UserRepository userRepository;

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordService.matches(password, user.getPassword());

    }
}
