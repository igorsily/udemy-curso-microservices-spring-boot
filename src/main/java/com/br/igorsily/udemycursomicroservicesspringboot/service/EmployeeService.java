package com.br.igorsily.udemycursomicroservicesspringboot.service;

import com.br.igorsily.udemycursomicroservicesspringboot.dtos.v1.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findAllEmployees();

    Page<EmployeeDTO> findAllEmployees(Pageable pageable);

    EmployeeDTO findEmployeeById(UUID id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(UUID id);
}
