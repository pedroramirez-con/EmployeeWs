/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.specification;

import com.ms.bankx.infrastructure.adapter.rest.dto.AuthRequest;
import com.ms.bankx.infrastructure.adapter.rest.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Authentication controller interface. Defines the endpoints exposed to generate JWT tokens.
 * Controller for generating JWT tokens.
 * <p>
 * This controller exposes a separate endpoint so clients can request a valid JWT token, which will
 * be used in protected API requests.
 * </p>
 *
 * <h2>Usage</h2>
 * <ul>
 * <li>Send valid credentials (example: username and password).</li>
 * <li>Receive a signed JWT token.</li>
 * <li>Use the token in the Authorization header: Bearer <token>.</li>
 * </ul>
 * 
 * @author FactoryXX
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication and JWT generation")
public interface AuthControllerApi {

  /**
   * Method to generate a JWT token from user credentials.
   * 
   * @param request object with credentials (user and password).
   * @return response with the JWT token.
   */
  @Operation(summary = "Generate token JWT",
      description = "Generates a valid JWT token for authentication in the API.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Generate token JWT success."),
      @ApiResponse(responseCode = "401", description = "Invalid credentials")
  })
  @PostMapping("/token")
  ResponseEntity<TokenResponse> generateToken(AuthRequest request);

}
