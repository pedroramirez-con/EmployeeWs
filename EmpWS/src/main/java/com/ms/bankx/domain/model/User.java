/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity of Domain that show a user Authorized.
 */
@Data
@AllArgsConstructor
public class User {
  private String username;
  private String password;
  private List<String> roles;
}

