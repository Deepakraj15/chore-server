package com.chore.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.chore.common.CredentialsRequest;
import com.chore.common.CredentialsResponse; 
import com.chore.common.UserServiceGrpc.UserServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcServiceImpl extends UserServiceImplBase {
    @Autowired
    private UserService userService;

    @Override
    public void checkCredentials(CredentialsRequest request, StreamObserver<CredentialsResponse> responseObserver) {
        boolean isValid = userService.validateUser(request.getUsername(), request.getPassword());
    
        CredentialsResponse response = CredentialsResponse.newBuilder()
                .setIsValid(isValid)
                .setMessage(isValid ? "Login success" : "Invalid credentials")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
