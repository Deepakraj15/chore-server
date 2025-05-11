package com.chore.auth_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

@Service
public class WebClientService {

    private final WebClient.Builder webClientBuilder;

    // Constructor-based injection
    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public boolean checkCredentials(String username, String password) {
        WebClient webClient = webClientBuilder.baseUrl("http://user-service:8083/api/v1").build();

        // Make the request and block to get the response
        ClientResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/users/checkUser")
                    .queryParam("username", username)
                    .queryParam("password", password)
                    .build())
            .exchangeToMono(Mono::just)
            .block(); 

        // Check if the response status code is OK (200)
        return response != null && response.statusCode() == HttpStatus.OK;
    }

}
