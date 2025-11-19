/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object with JWT token.
 */
@Data
@AllArgsConstructor
public class TokenResponse {
  private String token;
}
