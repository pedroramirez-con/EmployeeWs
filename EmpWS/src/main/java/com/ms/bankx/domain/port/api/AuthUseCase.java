/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.port.api;

import com.ms.bankx.domain.model.User;

/**
 * Authentication port. Define the operation of generating a JWT token for a user.
 */
public interface AuthUseCase {
  /**
   * Method that generates a valid JWT token for the given user.
   *
   * @param user user with credentials and roles.
   * @return token JWT signed.
   */
  String generateToken(User user);
}

