/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import com.ms.bankx.domain.model.User;
import com.ms.bankx.domain.port.api.AuthUseCase;
import com.ms.bankx.infrastructure.adapter.rest.dto.AuthRequest;
import com.ms.bankx.infrastructure.adapter.rest.dto.TokenResponse;
import com.ms.bankx.infrastructure.adapter.rest.specification.AuthControllerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

  private final AuthUseCase authUseCase;

  @Override
  public ResponseEntity<TokenResponse> generateToken(AuthRequest request) {
    User user = new User(request.getUsername(), request.getPassword(), request.getRoles());
    if ("admin".equals(user.getUsername()) && "1234".equals(user.getPassword())) {
      String token = authUseCase.generateToken(user);
      return ResponseEntity.ok(new TokenResponse(token));
    } else {
      return ResponseEntity.status(401).build();
    }


  }

}
