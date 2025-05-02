package com.chore.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chore.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
     User findByUsernameAndPassword(String username, String password);
}
