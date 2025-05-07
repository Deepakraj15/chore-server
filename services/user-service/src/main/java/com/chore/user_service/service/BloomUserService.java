package com.chore.user_service.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chore.user_service.repository.UserRepository;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import jakarta.annotation.PostConstruct;

@Service
public class BloomUserService {

    private BloomFilter<String> userBloomFilter;

    private final UserRepository userRepository;

    public BloomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        // Estimate expected number of users and acceptable false positive rate
        int expectedUsers = 1000;  //should fetch this value from the database of configuration file later
        double fpp = 0.01; // 1% false positive rate

        userBloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), expectedUsers, fpp);

        // Load all usernames into the bloom filter
        List<String> usernames = userRepository.findAllUsernames();
        usernames.forEach(userBloomFilter::put);
    }

    public boolean mightContainUser(String username) { // based on bloom filter
        return userBloomFilter.mightContain(username);
    }

}
