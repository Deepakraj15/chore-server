package com.chore.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/login")
    public RequestEntity<Object> getLoginAuthentication(
            @Parameter(description = "User's username", required = true) @RequestHeader(value = "user") String userName,
            @Parameter(description = "User's password", required = true) @RequestHeader(value = "pass") String password) {
                // TODO Learn about how to implement OAUth feature
        return null;
       
    }

}
