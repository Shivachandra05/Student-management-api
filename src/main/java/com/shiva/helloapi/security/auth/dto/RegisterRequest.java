package com.shiva.helloapi.security.auth.dto;

public record RegisterRequest(
        String username,
        String password
) {
}