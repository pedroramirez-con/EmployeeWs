/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtTokenProvider tokenProvider;

  public JwtAuthFilter(JwtTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String uri = request.getRequestURI();

    // 👇 Ignorar rutas públicas como H2, Swagger, etc.
    if (uri.startsWith("/h2-console") || uri.startsWith("/swagger-ui")
        || uri.startsWith("/v3/api-docs") || uri.startsWith("/auth/token")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String jwt = tokenProvider.resolveToken(request);
      if (jwt != null && tokenProvider.validateToken(jwt)) {
        Authentication auth = tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception ex) {
      // Loggear error de autenticación
    }

    filterChain.doFilter(request, response);
  }
}
