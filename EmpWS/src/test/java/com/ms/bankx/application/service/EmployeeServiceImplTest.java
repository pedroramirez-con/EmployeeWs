/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.application.service;

import com.ms.bankx.domain.exception.EmployeeNotFoundException;
import com.ms.bankx.domain.model.Employee;
import com.ms.bankx.domain.port.spi.EmployeePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeePersistencePort persistencePort; // Mockeamos el puerto

    @InjectMocks
    private EmployeeServiceImpl employeeService; // Inyectamos el mock

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().id(1L).firstName("Test").lastName("User").build();
    }

    @Test
    void testFindEmployeeById_Found() {
        // Given
        when(persistencePort.findById(1L)).thenReturn(Optional.of(employee));

        // When
        Optional<Employee> result = employeeService.findEmployeeById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getFirstName());
        verify(persistencePort, times(1)).findById(1L);
    }

    @Test
    void testFindEmployeeById_NotFound() {
        // Given
        when(persistencePort.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<Employee> result = employeeService.findEmployeeById(99L);

        // Then
        assertFalse(result.isPresent());
    }
    
    @Test
    void testDeleteEmployee_NotFound_ThrowsException() {
        // Given
        when(persistencePort.existsById(99L)).thenReturn(false);

        // When/Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(99L);
        });
        
        verify(persistencePort, never()).deleteById(anyLong());
    }
}