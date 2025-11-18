/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.config;

import com.ms.bankx.infrastructure.security.JwtAuthFilter;
import com.ms.bankx.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {}) // habilita CORS con configuración por defecto
            .csrf(csrf -> csrf.disable()) // deshabilitar CSRF para APIs REST
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // API sin estado
            )
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso a Swagger y Health Check sin autenticación
                .requestMatchers("/employees/**", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/health").permitAll()
                // Proteger todos los demás endpoints
                //.requestMatchers("/employees/**").authenticated()
                .anyRequest().authenticated()
            )
            // Añadir nuestro filtro JWT antes del filtro de Spring
            .addFilterBefore(new JwtAuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}