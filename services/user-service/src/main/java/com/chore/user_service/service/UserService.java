package com.chore.user_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chore.user_service.entity.User;
import com.chore.user_service.repository.UserRepository;

@Service
public class UserService {
    
  private final PasswordService passwordService;
    private final UserRepository userRepository;

    public UserService(PasswordService passwordService,
                       UserRepository userRepository) {
        this.passwordService = passwordService;
        this.userRepository = userRepository;
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordService.matches(password, user.getPassword());

    }
    
    @Transactional
    public boolean createUser(String username, String password) {
        try {
            if (isUserPresent(username)) {
                return false;
            }
            User user = new User();
            user.setUserId(generateUniqueUserId());
            user.setUsername(username);
            user.setPassword(passwordService.hashPassword(password));
            user.setCreatedAt(String.valueOf(System.currentTimeMillis()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            // error log
            return false;
        }
    }

    public boolean isUserPresent(String username) {
        return userRepository.findByUsername(username) != null;
    }


    private String generateUniqueUserId() {
    String userId;
    do {
        userId = UUID.randomUUID().toString();  // Generate a UUID
    } while (userRepository.existsById(userId));  
    
    return userId;
}
}