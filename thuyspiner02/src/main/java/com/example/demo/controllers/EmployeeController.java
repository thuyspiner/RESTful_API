package com.example.demo.controllers;

import com.example.demo.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController{

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of(new Employee("1","Test")));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> one(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Employee(id,"Test"));
    }

}
