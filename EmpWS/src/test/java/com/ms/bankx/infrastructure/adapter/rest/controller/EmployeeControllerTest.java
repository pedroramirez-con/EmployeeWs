/**
 * 
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ms.bankx.domain.exception.EmployeeNotFoundException;
import com.ms.bankx.domain.model.Employee;
import com.ms.bankx.domain.port.api.EmployeeServicePort;

//@WebMvcTest(controllers = EmployeeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServicePort employeeServicePort;
    
   // @Test
    @WithMockUser
    void testGetEmployeeById_Found() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("Test").build();
        when(employeeServicePort.findEmployeeById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Test"));
    }

    //@Test
    @WithMockUser
    void testGetEmployeeById_NotFound() throws Exception {
        when(employeeServicePort.findEmployeeById(99L)).thenThrow(new EmployeeNotFoundException(99L));

        mockMvc.perform(get("/employees/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Employee not found with ID: 99"));
    }
}
