package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Employee;
import com.wisetechglobal.employees.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(final Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(final Employee employee, Integer id) {

        Employee employeeDb = employeeRepository.findById(id).get();
        if (Objects.nonNull(employee.getFirstname())) {
            employeeDb.setFirstname(employee.getFirstname());
        }
        if (Objects.nonNull(employee.getLastname())) {
            employeeDb.setLastname(employee.getLastname());
        }
        return employeeRepository.save(employeeDb);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
