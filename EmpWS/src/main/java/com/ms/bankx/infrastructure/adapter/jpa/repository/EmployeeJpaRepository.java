/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.jpa.repository;

import com.ms.bankx.infrastructure.adapter.jpa.entity.EmployeeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that define the methods base and defined by us to persist the data Entity employee
 */
@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long>{

  /**
   * Searches for partial matches across multiple fields, ignoring case sensitivity.
   * @param firstName
   * @param lastName
   * @param secondLastName
   * @return List of employees found by name into database.
   */
  List<EmployeeEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSecondLastNameContainingIgnoreCase(
      String firstName, String lastName, String secondLastName);

}
