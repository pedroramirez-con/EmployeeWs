/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.security;

import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtTokenProviderTest {

    private final JwtTokenProvider tokenProvider = new JwtTokenProvider();

    @Test
    void testGenerateAndValidateToken() {
        String token = tokenProvider.generateToken("admin");
        
        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));
        token = tokenProvider.generateToken("admin");
        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));
        var auth = tokenProvider.getAuthentication(token);
        assertEquals("admin", auth.getName());
    }

    @Test
    void testInvalidToken() {
        String fakeToken = "abc.def.ghi";
        assertFalse(tokenProvider.validateToken(fakeToken));
    }
    
    @Test
    void testResolveToken() {
      HttpServletRequest req = mock(HttpServletRequest.class);
      assertNull(tokenProvider.resolveToken(req));
      when(req.getHeader("Authorization"))
          .thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9...");
      assertNotNull(tokenProvider.resolveToken(req));
      when(req.getHeader("Authorization"))
      .thenReturn("eyJhbGciOiJIUzI1NiJ9...");
      assertNull(tokenProvider.resolveToken(req));
    }
}
