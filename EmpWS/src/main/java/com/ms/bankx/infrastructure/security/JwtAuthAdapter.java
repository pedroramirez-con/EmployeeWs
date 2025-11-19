/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.security;

import com.ms.bankx.domain.model.User;
import com.ms.bankx.domain.port.api.AuthUseCase;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Implementation of the authentication port using JWT.
 */
@Component
public class JwtAuthAdapter implements AuthUseCase {

    @Value("${app.jwt.secret}") // Debe tener al menos 32 caracteres
    private String secretKey;

    @Value("${app.jwt.expiration-ms}")
    private long validityInMs;

    @Override
    public String generateToken(User user) {
        // Convertir el secretKey a un objeto Key válido para HS256
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(key, SignatureAlgorithm.HS256) // 👈 ahora correcto
                .compact();
    }
}


