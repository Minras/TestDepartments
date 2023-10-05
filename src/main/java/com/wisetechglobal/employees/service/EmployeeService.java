package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> fetchEmployeeList();

    Employee updateEmployee(Employee employee, Integer id);

    void deleteEmployeeById(Integer id);
}

