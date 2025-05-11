package com.chore.auth_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AuthWebClientConfig {

    @Bean
     WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}