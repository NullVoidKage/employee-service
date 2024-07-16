package com.example.demo.mapper;

import com.example.demo.dto.employeeDTO.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setDepartmentId(employee.getDepartment().getId()); // Map department ID
        return dto;
    }

    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());

        // Set department (assuming you fetch it from the database)
        if (dto.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(dto.getDepartmentId());
            employee.setDepartment(department);
        }

        return employee;
    }
}
