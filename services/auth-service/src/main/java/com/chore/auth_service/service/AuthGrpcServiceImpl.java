package com.chore.auth_service.service;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import com.chore.common.AuthRequest;
import com.chore.common.AuthResponse;
import com.chore.common.AuthServiceGrpc;
import com.chore.common.CredentialsRequest;
import com.chore.common.CredentialsResponse;
import com.chore.common.UserServiceGrpc;

public class AuthGrpcServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    private final ManagedChannel channel;
    private final UserServiceGrpc.UserServiceStub userServiceStub;

    // Constructor: Connects to the UserService.
    public AuthGrpcServiceImpl() {
        this.channel = ManagedChannelBuilder.forAddress("user-service", 50051)
                .usePlaintext()
                .build();
        this.userServiceStub = UserServiceGrpc.newStub(channel);
    }

    @Override
    public void authenticateUser(AuthRequest request, StreamObserver<AuthResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Create the CredentialsRequest to send to UserService.
        CredentialsRequest credentialsRequest = CredentialsRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        // Send the request to the UserService to check credentials.
        userServiceStub.checkCredentials(credentialsRequest, new StreamObserver<CredentialsResponse>() {
            @Override
            public void onNext(CredentialsResponse value) {
                // Build the AuthResponse based on the result from UserService.
                AuthResponse.Builder responseBuilder = AuthResponse.newBuilder();

                if (value.getIsValid()) {
                    responseBuilder.setSuccess(true).setMessage("Authentication successful.");
                } else {
                    responseBuilder.setSuccess(false).setMessage(value.getMessage());
                }

                // Send the response back to the client.
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                // Nothing to do here.
            }
        });
    }
}
