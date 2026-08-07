/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.security.JwtAuthEntryPoint
 *  jakarta.servlet.http.HttpServletRequest
 *  jakarta.servlet.http.HttpServletResponse
 *  org.springframework.security.core.AuthenticationException
 *  org.springframework.security.web.AuthenticationEntryPoint
 *  org.springframework.stereotype.Component
 */
package com.shiva.helloapi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthEntryPoint
implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exp) throws IOException {
        resp.setStatus(401);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"error\":\"Unauthorised\"}");
    }
}

