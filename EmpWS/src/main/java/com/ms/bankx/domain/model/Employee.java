/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object that contain the entity the domain employee
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  private Long id;
  private String firstName;
  private String secondName;
  private String lastName;
  private String secondLastName;
  private Integer age;
  private String gender;
  private LocalDate birthDate;
  private String position;
  private LocalDateTime systemRegistrationDate;
  private boolean active;
}
