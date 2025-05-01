package com.chore.auth_service.utility;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtUtility {

    private static final String SECRET = "thisisaverysecuresecretkey123456"; // Ensure this is at least 256 bits (32 characters)
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long ACCESS_TOKEN_EXPIRATION = 86400000;  // 24 hours in milliseconds
    private static final long REFRESH_TOKEN_EXPIRATION = 2592000000L;  // 30 days in milliseconds

    // Generate access token
    public static String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Generate refresh token
    public static String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Parse and validate the token
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
