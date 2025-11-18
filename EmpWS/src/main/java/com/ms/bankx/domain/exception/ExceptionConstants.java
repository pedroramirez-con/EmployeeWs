/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.exception;

/**
 * Utility of constants to custom exceptions
 */
public class ExceptionConstants {
  
  /**
   * Constructor to fix rule of SonarQube
   */
  private ExceptionConstants() {
    throw new IllegalStateException("Utility class");
  }
  public static final String NOT_FOUND = "Employee not found with ID: ";
  public static final String NOT_FAIL = "Employee not update success with ID: ";

  
}
