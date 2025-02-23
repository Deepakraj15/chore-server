package com.chore.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Operation(summary = "Authenticate User", description = "Authenticates the user based on provided credentials.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/login")
    public RequestEntity<Object> getLoginAuthentication(
            @Parameter(description = "User's username", required = true) @RequestHeader(value = "user") String userName,
            @Parameter(description = "User's password", required = true) @RequestHeader(value = "pass") String password) {
        // TODO Learn about how to implement OAUth feature
        return null;

    }

    @Operation(summary = "Refresh User's AuthToken", description = "Refresh user's authtoken from the given authtoken.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully refreshed user's authtoken"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/refresh-token")
    public Object refreshAuthToken(
            @Parameter(description = "User's refresh tokens", required = true) @RequestHeader(value = "Authentication") String authentication) {
            //TODO : to create a mechanism to referesh authtoken once in a while
        return null;
        }
    

}
