package com.chore.user_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chore.user_service.entity.User;
import com.chore.user_service.repository.UserRepository;

@Service
public class UserService {
    
  private final PasswordService passwordService;
    private final UserRepository userRepository;
    private final BloomUserService bloomUserService;

    public UserService(PasswordService passwordService,
                       UserRepository userRepository,
                       BloomUserService bloomUserService) {
        this.passwordService = passwordService;
        this.userRepository = userRepository;
        this.bloomUserService = bloomUserService;
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordService.matches(password, user.getPassword());

    }
    
    @Transactional
    public boolean createUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.save(user);
            return true;
        }
        return false; // if user already exists, return false
    }
    public boolean checkUserNameAvailability(String username) {
        return bloomUserService.mightContainUser(username);
    }
}