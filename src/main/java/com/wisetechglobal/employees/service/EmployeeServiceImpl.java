package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Employee;
import com.wisetechglobal.employees.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return EmployeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer id) {

        Employee employeeDb = employeeRepository.findById(id).get();

        if (Objects.nonNull(employee.getFirstname())
                && !"".equals(employee.getFirstname())) {
            employeeDb.setFirstname(employee.getFirstname());
        }
        if (Objects.nonNull(employee.getLastname())
                && !"".equals(employee.getLastname())) {
            employeeDb.setLastname(employee.getLastname());
        }

        return employeeRepository.save(employeeDb);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
