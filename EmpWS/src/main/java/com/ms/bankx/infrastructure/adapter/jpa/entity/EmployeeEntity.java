/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity object that define the data of employees into the DataBase
 */
@Data
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
  @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
  @Column(name = "ID_EMPLOYEE")
  private Long id;

  @Column(name = "FIRST_NAME", nullable = false, length = 100)
  private String firstName;

  @Column(name = "SECOND_NAME", length = 100)
  private String secondName;

  @Column(name = "LAST_NAME", nullable = false, length = 100)
  private String lastName;

  @Column(name = "SECOND_LAST_NAME", length = 100)
  private String secondLastName;

  @Column(name = "AGE")
  private Integer age;

  @Column(name = "GENDER", length = 10)
  private String gender;

  @Column(name = "BIRTH_DATE")
  private LocalDate birthDate;

  @Column(name = "POSITION", length = 150)
  private String position;

  @CreationTimestamp
  @Column(name = "SYSTEM_REGISTRATION_DATE", nullable = false, updatable = false)
  private LocalDateTime systemRegistrationDate;

  @Column(name = "IS_ACTIVE", nullable = false)
  private boolean active;
}
