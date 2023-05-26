package com.br.igorsily.udemycursomicroservicesspringboot.service.impl;

import com.br.igorsily.udemycursomicroservicesspringboot.dtos.v1.EmployeeDTO;
import com.br.igorsily.udemycursomicroservicesspringboot.entity.Employee;
import com.br.igorsily.udemycursomicroservicesspringboot.exception.BadRequestException;
import com.br.igorsily.udemycursomicroservicesspringboot.mapper.EmployeeMapper;
import com.br.igorsily.udemycursomicroservicesspringboot.repository.EmployeeRepository;
import com.br.igorsily.udemycursomicroservicesspringboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());

        if (employeeOptional.isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        employee = employeeRepository.save(employee);

        return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return EmployeeMapper.INSTANCE.toDTOList(employees);
    }

    @Override
    public Page<EmployeeDTO> findAllEmployees(Pageable pageable) {

        Page<Employee> employees = employeeRepository.findAll(pageable);

        return employees.map(EmployeeMapper.INSTANCE::toDTO);
    }

    @Override
    public EmployeeDTO findEmployeeById(UUID id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        return employeeOptional.map(EmployeeMapper.INSTANCE::toDTO).orElseThrow(() -> new BadRequestException("Employee not found"));

    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {

        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);

        if (employee.getId() == null)
            throw new BadRequestException("Employee id is required");

        this.findEmployeeById(employee.getId());

        employeeRepository.save(employee);

        return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    @Override
    public void deleteEmployee(UUID id) {

            this.findEmployeeById(id);

            employeeRepository.deleteById(id);
    }
}
