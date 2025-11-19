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

  private static final String[] URLS_WITHOUT_TOKEN =
      {"/auth/**", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/health"};

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> {
    }).csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers(URLS_WITHOUT_TOKEN).permitAll()
            .requestMatchers("/employees/**").authenticated().anyRequest().authenticated())
        // para que la consola H2 funcione
        .headers(headers -> headers.frameOptions(frame -> frame.disable())).addFilterBefore(
            new JwtAuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
