/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.specification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface who contains the endpoints to access to CRUD of employees
 */
// @Tag(name = "Employee Management", description = "APIs para la gestión de Empleados")
// @SecurityRequirement(name = "bearerAuth") // Requiere JWT
@RequestMapping("/employees")
public interface EmployeeApi {

  /**
   * Method who tested the correct deploy the this appWeb.
   * 
   * @return String value
   */
  @GetMapping("/tst")
  ResponseEntity<String> getTestCall();

}
