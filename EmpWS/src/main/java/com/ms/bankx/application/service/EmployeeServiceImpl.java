/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.application.service;

import com.ms.bankx.domain.exception.EmployeeNotFoundException;
import com.ms.bankx.domain.model.Employee;
import com.ms.bankx.domain.port.api.EmployeeServicePort;
import com.ms.bankx.domain.port.spi.EmployeePersistencePort;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of services into EmployeeServicePort interface
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeServicePort {

  private final EmployeePersistencePort employeePersistencePort;

  @Override
  @Transactional(readOnly = true)
  public List<Employee> findAllEmployees() {
    return employeePersistencePort.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Employee> findEmployeeById(Long id) {
    return employeePersistencePort.findById(id);
  }

  @Override
  @Transactional
  public List<Employee> createEmployees(List<Employee> employees) {
    // Asignar fecha de alta antes de guardar
    List<Employee> employeesToCreate =
        employees.stream().peek(emp -> emp.setSystemRegistrationDate(LocalDateTime.now()))
            .collect(Collectors.toList());
    return employeePersistencePort.saveAll(employeesToCreate);
  }

  @Override
  @Transactional
  public Employee updateEmployee(Long id, Employee employeeDetails) {
    // Programación funcional (Optional) para manejo de existencia
    Employee existingEmployee =
        employeePersistencePort.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    BeanUtils.copyProperties(employeeDetails, existingEmployee,
        getNullPropertyNames(employeeDetails));
    return employeePersistencePort.save(existingEmployee);
  }

  @Override
  @Transactional
  public void deleteEmployeeById(Long id) {
    if (!employeePersistencePort.existsById(id)) {
      throw new EmployeeNotFoundException(id);
    }
    employeePersistencePort.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Employee> searchEmployeesByName(String name) {
    return employeePersistencePort.findByNameContaining(name);
  }

  private String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    return Arrays.stream(src.getPropertyDescriptors()).map(pd -> pd.getName())
        .filter(name -> src.getPropertyValue(name) == null).toArray(String[]::new);
  }

}
