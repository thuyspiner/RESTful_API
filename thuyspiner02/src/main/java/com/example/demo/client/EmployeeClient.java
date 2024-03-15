package com.example.demo.client;

import com.example.demo.models.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "Employee-feign-client", url = "http://localhost:8888/emp")
public interface EmployeeClient {

    @GetMapping(value = "/employees")
    ResponseEntity<List<Employee>> getAll();

    @PostMapping("/employees")
    ResponseEntity<Employee> createNew(@RequestBody Employee newEmployee);

    @GetMapping("/employees/{id}")
    ResponseEntity<Employee> getById(@PathVariable String id);
}
