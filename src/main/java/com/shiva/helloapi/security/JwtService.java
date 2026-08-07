package com.shiva.helloapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    public String generateToken(UserDetails userDetails) {

        Date now = new Date();

        long currentTime = now.getTime();
        long expiryTime = currentTime + expirationMs;

        Date expirationDate =
                new Date(expiryTime);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(
                        getSignKey(),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    private Key getSignKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        Claims claims =
                extractAllClaims(token);

        String username =
                extractUsername(token);

        Date expiration =
                claims.getExpiration();

        return username.equals(
                        userDetails.getUsername()
                )
                && expiration.after(
                        new Date()
                );
    }

    private Claims extractAllClaims(
            String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        getSignKey()
                )
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(
            String token) {

        return extractAllClaims(token)
                .getSubject();
    }
}