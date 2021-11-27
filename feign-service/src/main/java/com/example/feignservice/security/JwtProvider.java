package com.example.feignservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

import static java.util.Date.from;

@Service
@Getter
public class JwtProvider {

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    private String key = "c5b7f374-4145-4cac-b593-3e46c5866326";

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(from(Instant.now()))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }
}
