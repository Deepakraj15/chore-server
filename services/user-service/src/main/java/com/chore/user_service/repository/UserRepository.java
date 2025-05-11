package com.chore.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chore.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

     User findByUsername(String username);

     User findByUsernameAndPassword(String username, String password);

     @Query("SELECT u.username FROM User u")
     List<String> findAllUsernames();

}