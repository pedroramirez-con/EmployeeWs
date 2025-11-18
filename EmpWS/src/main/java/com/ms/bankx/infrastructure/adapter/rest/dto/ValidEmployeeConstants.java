/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.dto;

/**
 * Utility of constants to be send when to validate the employee object request
 */
public class ValidEmployeeConstants {

  /**
   * Constructor to fix rule of SonarQube
   */
  private ValidEmployeeConstants() {
    throw new IllegalStateException("Utility class");
  }
  public static final String FIRST_NAME_NOT_EMPTY = "Primer nombre no puede estar vacío";
  public static final String SEC_NOT_EMPTY= "Apellido paterno no puede estar vacío";
  public static final String DATE_BORN_NOT_NULL= "Fecha de nacimiento no puede ser nula";
  public static final String DATE_BORN_PAST= "Fecha de nacimiento no puede ser actual o futura";
  public static final String DD_MM_YYYY= "dd-MM-yyyy";
  public static final String NOT_EMPTY_POSITION= "Puesto no puede estar vacío";
}
