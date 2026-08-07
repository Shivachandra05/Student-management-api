package com.shiva.helloapi.security.auth.dto;

public record AuthRequest(
        String username,
        String password
) {
}