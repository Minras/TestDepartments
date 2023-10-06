package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Employee;
import com.wisetechglobal.employees.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String MSG_TPL_ENTITY_DOESNT_EXIST = "Employee with id=%d doesn't exist";

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

        Optional<Employee> result = employeeRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(String.format(MSG_TPL_ENTITY_DOESNT_EXIST, id));
        }
        Employee employeeDb = result.get();
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
        Optional<Employee> result = employeeRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(String.format(MSG_TPL_ENTITY_DOESNT_EXIST, id));
        }
        employeeRepository.deleteById(id);
    }
}
