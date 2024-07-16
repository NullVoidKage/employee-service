package com.example.demo.service;

import com.example.demo.dto.employeeDTO.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        // Ensure the department exists
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setDepartment(department); // Set the department

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateEmployee(EmployeeDTO employeeDTO) {
        // Ensure the department exists
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setDepartment(department); // Set the department

        employeeRepository.save(employee); // This will update if the ID exists
    }

    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    public List<EmployeeDTO> getEmployeesByDepartmentName(String departmentName) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentName(departmentName);
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

}
