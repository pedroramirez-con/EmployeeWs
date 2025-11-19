/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Object what will be exposed by this app to response
 */
@Data
@AllArgsConstructor
public class EmployeeResponse {
  private Long id;
  private String firstName;
  private String secondName;
  private String lastName;
  private String secondLastName;
  private Integer age;
  private String gender;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate birthDate;
  private String position;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime systemRegistrationDate;
  private boolean active;
}
