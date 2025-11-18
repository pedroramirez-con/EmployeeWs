/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.domain.port.spi;

import com.ms.bankx.domain.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * Exit Port (SPI Port / Persistence): Describes what the application needs 
 * from the outside (in this case, a database).
 */

public interface EmployeePersistencePort {
  
  /**
   * Method that found the list of all employees in the DataBase.
   * @return List of all employees.
   */  
  List<Employee> findAll();

  /**
   * Method that returns the employee search by ID in the DataBase.
   * @param id ID of the employee to search for.
   * @return Return the found employee.
   */
  Optional<Employee> findById(Long id);
  
  /**
   * Method that stores the list of employees in the DataBase.
   * @param employees List of employees to will stores.
   * @return List of employees stores.
   */  
  List<Employee> saveAll(List<Employee> employees);
  
  /**
   * Method that stores the employee in the DataBase.
   * @param employee to will store.
   * @return employee stored.
   */
  Employee save(Employee employee);
  
  /**
   * Method that delete employee information by ID in the DataBase.
   * @param id ID of the employee to delete for.
   */
  void deleteById(Long id);
  
  /**
   * Method that search employee information by name in the DataBase.
   * @param name Name of the employee to search for.
   * @return Data of employee found.
   */
  List<Employee> findByNameContaining(String name);

  /**
   * Method that validate the employee exist by ID in the DataBase.
   * @param id ID of the employee to validate for.
   * @return Return true if found employee.
   */
  boolean existsById(Long id);
}
