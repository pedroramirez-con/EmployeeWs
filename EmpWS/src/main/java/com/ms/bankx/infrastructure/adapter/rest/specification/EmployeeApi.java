/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.specification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.ms.bankx.infrastructure.adapter.rest.dto.EmployeeRequest;
import com.ms.bankx.infrastructure.adapter.rest.dto.EmployeeResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * REST API interface for managing employees.
 * Provides endpoints for creating, retrieving, updating, and deleting employee records.
 */
@Tag(name = "Employee Management", description = "APIs for managing employees")
@SecurityRequirement(name = "bearerAuth") // Requires JWT authentication
@RequestMapping("/employees")
public interface EmployeeApi {

  /**
   * Endpoint to verify that the application is running correctly.
   * @return a simple string response indicating successful deployment
   */
  @GetMapping("/tst")
  ResponseEntity<String> getTestCall();

  /**
   * Retrieves a list of all employees.
   * @return a list of {@link EmployeeResponse} objects
   */
  @Operation(summary = "Get all employees")
  @ApiResponse(responseCode = "200", description = "List of employees")
  @GetMapping
  ResponseEntity<List<EmployeeResponse>> getAllEmployees();

  /**
   * Retrieves an employee by their unique ID.
   * @param id the ID of the employee to retrieve
   * @return the {@link EmployeeResponse} object if found
   */
  @Operation(summary = "Get employee by ID")
  @ApiResponse(responseCode = "200", description = "Employee found")
  @ApiResponse(responseCode = "404", description = "Employee not found")
  @GetMapping("/{id}")
  ResponseEntity<EmployeeResponse> getEmployeeById(
      @Parameter(description = "Employee ID") @PathVariable Long id);

  /**
   * Creates one or more new employees.
   * @param employees a list of {@link EmployeeRequest} objects to create
   * @return a list of created {@link EmployeeResponse} objects
   */
  @Operation(summary = "Create one or more employees")
  @ApiResponse(responseCode = "201", description = "Employees created")
  @ApiResponse(responseCode = "400", description = "Invalid request")
  @PostMapping
  ResponseEntity<List<EmployeeResponse>> createEmployees(
      @Parameter(description = "List of employees to create") @Valid @RequestBody List<EmployeeRequest> employees);

  /**
   * Updates an existing employee by their ID.
   * @param id the ID of the employee to update
   * @param employeeRequest the updated employee data
   * @return the updated {@link EmployeeResponse} object
   */
  @Operation(summary = "Update an employee (partial or full)")
  @ApiResponse(responseCode = "200", description = "Employee updated")
  @ApiResponse(responseCode = "404", description = "Employee not found")
  @PutMapping("/{id}")
  ResponseEntity<EmployeeResponse> updateEmployee(
      @Parameter(description = "Employee ID") @PathVariable Long id,
      @Parameter(description = "Updated employee data") @Valid @RequestBody EmployeeRequest employeeRequest);

  /**
   * Deletes an employee by their ID.
   * @param id the ID of the employee to delete
   * @return an empty response with status 204 if successful
   */
  @Operation(summary = "Delete an employee")
  @ApiResponse(responseCode = "204", description = "Employee deleted")
  @ApiResponse(responseCode = "404", description = "Employee not found")
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEmployee(
      @Parameter(description = "Employee ID") @PathVariable Long id);

  /**
   * Searches for employees by name.
   * @param name the name to search for
   * @return a list of matching {@link EmployeeResponse} objects
   */
  @Operation(summary = "Search employees by name")
  @ApiResponse(responseCode = "200", description = "List of matching employees")
  @GetMapping("/search")
  ResponseEntity<List<EmployeeResponse>> searchEmployees(
      @Parameter(description = "Search term for employee name") @RequestParam(name = "name") String name);
}

