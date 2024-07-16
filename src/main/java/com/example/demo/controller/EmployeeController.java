package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<String> addEmployee(@RequestBody com.example.demo.dto.employeeDTO.EmployeeDTO employeeDTO) {
        com.example.demo.dto.employeeDTO.EmployeeDTO savedEmployee = employeeService.addEmployee(employeeDTO);
        System.out.println("EMPLOYEE");
        return new ResponseEntity<>("Employee added with ID: " + savedEmployee.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<com.example.demo.dto.employeeDTO.EmployeeDTO> getEmployeeById(@PathVariable long id) {
        com.example.demo.dto.employeeDTO.EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<com.example.demo.dto.employeeDTO.EmployeeDTO>> getAllEmployees() {
        List<com.example.demo.dto.employeeDTO.EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody com.example.demo.dto.employeeDTO.EmployeeDTO employeeDTO) {
        employeeDTO.setId(id); // Ensure the ID is set in the DTO
        employeeService.updateEmployee(employeeDTO);
        return new ResponseEntity<>("Employee updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully.", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/department/{departmentName}")
    public ResponseEntity<List<com.example.demo.dto.employeeDTO.EmployeeDTO>>
    getEmployeesByDepartment(@PathVariable String departmentName) {
        List<com.example.demo.dto.employeeDTO.EmployeeDTO> employees =
                employeeService.getEmployeesByDepartmentName(departmentName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
