package com.wisetechglobal.employees.controller.v1;

import com.wisetechglobal.employees.persistence.entity.Employee;
import com.wisetechglobal.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> fetchEmployeeList() {
        return employeeService.fetchEmployeeList();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Integer id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);
    }
}
