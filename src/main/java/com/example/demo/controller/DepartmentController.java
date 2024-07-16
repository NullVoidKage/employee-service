package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/department")
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return new ResponseEntity<>("Department added with ID: " + savedDepartment.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable long id, @RequestBody Department department) {
        department.setId(id); // Ensure the ID is set
        departmentRepository.save(department);
        return new ResponseEntity<>("Department updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable long id) {
        departmentRepository.deleteById(id);
        return new ResponseEntity<>("Department deleted successfully.", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/departments")
    public ResponseEntity<String> deleteAllDepartments() {
        departmentRepository.deleteAll();
        return new ResponseEntity<>("All departments deleted successfully.", HttpStatus.NO_CONTENT);
    }
}

