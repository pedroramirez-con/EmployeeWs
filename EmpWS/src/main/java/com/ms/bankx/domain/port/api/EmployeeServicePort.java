/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.port.api;

import com.ms.bankx.domain.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * Entry Port (API Port / Use Case) describes what the application can do
 */
public interface EmployeeServicePort {
  
  /**
   * Method that found the list of all employees.
   * @return List of all employees.
   */
  List<Employee> findAllEmployees();
  
  /**
   * Method that returns the employee search by ID.
   * @param id ID of the employee to search for.
   * @return Return the found employee.
   */
  Optional<Employee> findEmployeeById(Long id);
  
  /**
   * Method that stores the list of employees.
   * @param employees List of employees to will stores.
   * @return List of employees stores.
   */
  List<Employee> createEmployees(List<Employee> employees);
  
  /**
   * Method that updates employee information by ID.
   * @param id ID of the employee to update for.
   * @param Employee data of the employee to update for.
   * @return Data of employee updated.
   */
  Employee updateEmployee(Long id, Employee employee);
  
  /**
   * Method that delete employee information by ID.
   * @param id ID of the employee to delete for.
   */
  void deleteEmployeeById(Long id);
  
  /**
   * Method that search employee information by name.
   * @param name Name of the employee to search for.
   * @return Data of employee found.
   */
  List<Employee> searchEmployeesByName(String name);
}
