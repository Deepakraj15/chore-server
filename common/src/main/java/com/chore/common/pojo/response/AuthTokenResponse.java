package com.chore.common.pojo.response;   

import lombok.Data;

@Data
public class AuthTokenResponse {
    private String authToken;
    private String refreshToken;
}
