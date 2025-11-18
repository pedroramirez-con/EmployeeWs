/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import com.ms.bankx.application.mapper.EmployeeApiMapper;
import com.ms.bankx.domain.exception.EmployeeNotFoundException;
import com.ms.bankx.domain.model.Employee;
import com.ms.bankx.domain.port.api.EmployeeServicePort;
import com.ms.bankx.infrastructure.adapter.rest.dto.EmployeeRequest;
import com.ms.bankx.infrastructure.adapter.rest.dto.EmployeeResponse;
import com.ms.bankx.infrastructure.adapter.rest.specification.EmployeeApi;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * Controller that implement the end points defined into EmployeeApi
 */
@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

  private final EmployeeServicePort employeeServicePort;
  private final EmployeeApiMapper mapper;
  
  @Override
  public ResponseEntity<String> getTestCall() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("MyResponseHeader", "MyValue");
    return new ResponseEntity<>("Test sample :: Hello World this server respose OK!!",
        responseHeaders, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
      List<Employee> employees = employeeServicePort.findAllEmployees();
      return ResponseEntity.ok(mapper.toResponseList(employees));
  }

  @Override
  public ResponseEntity<EmployeeResponse> getEmployeeById(Long id) {
      // Manejo de status HTTP correcto (404)
      return employeeServicePort.findEmployeeById(id)
              .map(mapper::toResponse)
              .map(ResponseEntity::ok)
              .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @Override
  public ResponseEntity<List<EmployeeResponse>> createEmployees(List<EmployeeRequest> employeesRequest) {
      List<Employee> employees = mapper.toDomainList(employeesRequest);
      List<Employee> createdEmployees = employeeServicePort.createEmployees(employees);
      return new ResponseEntity<>(mapper.toResponseList(createdEmployees), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<EmployeeResponse> updateEmployee(Long id, EmployeeRequest employeeRequest) {
      Employee employee = mapper.toDomain(employeeRequest);
      Employee updatedEmployee = employeeServicePort.updateEmployee(id, employee);
      return ResponseEntity.ok(mapper.toResponse(updatedEmployee));
  }

  @Override
  public ResponseEntity<Void> deleteEmployee(Long id) {
      employeeServicePort.deleteEmployeeById(id);
      return ResponseEntity.noContent().build(); // Status 204
  }

  @Override
  public ResponseEntity<List<EmployeeResponse>> searchEmployees(String name) {
      List<Employee> employees = employeeServicePort.searchEmployeesByName(name);
      return ResponseEntity.ok(mapper.toResponseList(employees));
  }

}
