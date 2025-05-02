package com.chore.auth_service.pojo.request;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
