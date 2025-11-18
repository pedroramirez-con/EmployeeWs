/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.exception;

/**
 * 
 */
public class EmployeeNotFoundException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @param id
   */
  public EmployeeNotFoundException(Long id) {
    super(ExceptionConstants.NOT_FOUND + id);
  }
}
