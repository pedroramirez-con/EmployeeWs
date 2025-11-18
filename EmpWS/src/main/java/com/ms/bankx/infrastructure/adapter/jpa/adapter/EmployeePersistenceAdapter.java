/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.jpa.adapter;

import com.ms.bankx.application.mapper.EmployeePersistenceMapper;
import com.ms.bankx.domain.model.Employee;
import com.ms.bankx.domain.port.spi.EmployeePersistencePort;
import com.ms.bankx.infrastructure.adapter.jpa.entity.EmployeeEntity;
import com.ms.bankx.infrastructure.adapter.jpa.repository.EmployeeJpaRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of exit Port (SPI Port / Persistence): Describes what the application needs 
 * from the outside (in this case, a database).
 */
@Component
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements EmployeePersistencePort {

  private final EmployeeJpaRepository repository;
  private final EmployeePersistenceMapper mapper;
  
  @Override
  public List<Employee> findAll() {
      return mapper.toDomainList(repository.findAll());
  }

  @Override
  public Optional<Employee> findById(Long id) {
      return repository.findById(id).map(mapper::toDomain);
  }

  @Override
  public List<Employee> saveAll(List<Employee> employees) {
      List<EmployeeEntity> entities = employees.stream()
              .map(mapper::toEntity)
              .toList();
      return mapper.toDomainList(repository.saveAll(entities));
  }

  @Override
  public Employee save(Employee employee) {
      EmployeeEntity entity = mapper.toEntity(employee);
      return mapper.toDomain(repository.save(entity));
  }

  @Override
  public void deleteById(Long id) {
      repository.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
      return repository.existsById(id);
  }

  @Override
  public List<Employee> findByNameContaining(String name) {
    if (name == null || name.isBlank()) {
        return Collections.emptyList();
    }

    List<EmployeeEntity> entities = repository
        .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSecondLastNameContainingIgnoreCase(
            name, name, name);

    return mapper.toDomainList(entities);

  }

}
