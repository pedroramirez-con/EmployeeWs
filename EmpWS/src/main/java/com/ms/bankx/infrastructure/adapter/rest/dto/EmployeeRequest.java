/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Object what will be exposed by this app to request
 */
@Data
public class EmployeeRequest {
  @NotBlank(message = ValidEmployeeConstants.FIRST_NAME_NOT_EMPTY)
  @Size(max = 100)
  private String firstName;
  @Size(max = 100)
  private String secondName;
  @NotBlank(message = ValidEmployeeConstants.SEC_NOT_EMPTY)
  @Size(max = 100)
  private String lastName;
  @Size(max = 100)
  private String secondLastName;

  private Integer age;
  @Size(max = 10)
  private String gender;

  @NotNull(message = ValidEmployeeConstants.DATE_BORN_NOT_NULL)
  @Past(message = ValidEmployeeConstants.DATE_BORN_PAST)
  @Schema(type = "string", format = "date", example = "19-11-2025")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidEmployeeConstants.DD_MM_YYYY)
  private LocalDate birthDate;

  @NotBlank(message = ValidEmployeeConstants.NOT_EMPTY_POSITION)
  @Size(max = 150)
  private String position;

  @NotNull
  private boolean active;
}
