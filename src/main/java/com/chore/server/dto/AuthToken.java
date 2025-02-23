package com.chore.server.dto;

import lombok.Data;

/**
 * @author Deepakraj K
 * 
 * AuthToken DTO repesentation
 * has authToken and refreshToken each of type string 
 * will be returned to the client upon successful authentication
 * 
 */
@Data
public class AuthToken {
    String authToken;
    String refreshToken;
}
