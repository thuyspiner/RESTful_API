package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestFeignClientController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping ("/createNewEmployee")
    public Employee createNewEmployee() {
        Employee employee = new Employee("12313","GDHGDHDGHD");
        return employeeService.saveNewEmployee(employee);
    }

    @GetMapping("/getEmployeeById")
    public Employee getEmployeeById() {
        return employeeService.getEmployeeById("123");
    }
}
