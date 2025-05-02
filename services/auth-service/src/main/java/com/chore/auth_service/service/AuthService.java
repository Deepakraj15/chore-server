package com.chore.auth_service.service;

import org.springframework.stereotype.Service;

import com.chore.auth_service.utility.JwtUtility;
import com.chore.common.CredentialsRequest;
import com.chore.common.CredentialsResponse;
import com.chore.common.UserServiceGrpc;
import com.chore.common.exception.AuthenticationException;
import com.chore.common.pojo.request.AuthTokenRequest;
import com.chore.common.pojo.response.AuthTokenResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class AuthService {

    private final ManagedChannel channel;
    private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public AuthService() {
        this.channel = ManagedChannelBuilder.forAddress("user-service", 50051)
                .usePlaintext()
                .build();
        this.userServiceStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public AuthTokenResponse generateAuthTokenAndRefreshToken(AuthTokenRequest authRequest) throws AuthenticationException {
         AuthTokenResponse authResponse = new AuthTokenResponse();
        CredentialsRequest credentialsRequest = CredentialsRequest.newBuilder()
                .setUsername(authRequest.getUsername())
                .setPassword(authRequest.getPassword())
                .build();

        CredentialsResponse credentialsResponse = userServiceStub.checkCredentials(credentialsRequest);

        if (credentialsResponse.getIsValid()) {
            authResponse.setAuthToken(JwtUtility.generateAccessToken(authRequest.getUsername()));
            authResponse.setRefreshToken(JwtUtility.generateRefreshToken(authRequest.getUsername()));
        } else {
            throw new AuthenticationException("Invalid credentials: " + credentialsResponse.getMessage());
        }

        return authResponse;
    }
}

