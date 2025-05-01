package com.chore.auth_service.pojo.response;   

import lombok.Data;
@Data
public class AuthResponse {
    private String authToken;
    private String refreshToken;
}
