package com.example.demo.services;

import com.example.demo.client.EmployeeClient;
import com.example.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeClient employeeClient;

    public List<Employee> getAllEmployee() {
        ResponseEntity<List<Employee>> response = employeeClient.getAll();
        return response.getBody();
    }

    public Employee saveNewEmployee(Employee newEmployee) {
        ResponseEntity<Employee> response = employeeClient.createNew(newEmployee);
        return response.getBody();
    }

    public Employee getEmployeeById(String id) {
        ResponseEntity<Employee> response = employeeClient.getById(id);
        return response.getBody();
    }
}
