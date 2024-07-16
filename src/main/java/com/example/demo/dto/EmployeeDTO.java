package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmployeeDTO {
    long id;
    String name;
     Long departmentId; // Add this field
    long salary;
}
