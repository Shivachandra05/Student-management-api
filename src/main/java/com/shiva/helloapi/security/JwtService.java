/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.security.JwtService
 *  io.jsonwebtoken.Claims
 *  io.jsonwebtoken.Jwts
 *  io.jsonwebtoken.SignatureAlgorithm
 *  io.jsonwebtoken.security.Keys
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.security.core.userdetails.UserDetails
 *  org.springframework.stereotype.Service
 */
package com.shiva.helloapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value(value="${jwt.secret}")
    private String secret;
    @Value(value="${jwt.expiration}")
    private long expirationMs;

    public String generateToken(UserDetails udt) {
        Date now = new Date();
        long current = now.getTime();
        long expirytime = current + this.expirationMs;
        Date exp = new Date(expirytime);
        return Jwts.builder().setSubject(udt.getUsername()).setIssuedAt(now).setExpiration(exp).signWith(this.getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor((byte[])this.secret.getBytes());
    }

    public boolean isTokenvalid(String token, UserDetails udt) {
        Claims claim = this.extractAllClaims(token);
        String Username = this.extractUsername(token);
        Date exp = claim.getExpiration();
        return Username.equals(udt.getUsername()) && exp.after(new Date());
    }

    private Claims extractAllClaims(String token) {
        return (Claims)Jwts.parserBuilder().setSigningKey(this.getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return this.extractAllClaims(token).getSubject();
    }
}

