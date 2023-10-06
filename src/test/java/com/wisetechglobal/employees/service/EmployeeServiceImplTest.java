package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Employee;
import com.wisetechglobal.employees.persistence.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void saveEmployee() {
        Employee employee = new Employee();
        employee.setFirstname("John");
        employee.setLastname("Doe");
        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee employeeDb = employeeService.saveEmployee(employee);
        assertThat(employeeDb.getFirstname()).isSameAs(employee.getFirstname());
        assertThat(employeeDb.getLastname()).isSameAs(employee.getLastname());
        verify(employeeRepository).save(employee);
    }

    @Test
    void fetchEmployeeList() {
        List<Employee> employees = new ArrayList();
        employees.add(new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> expected = employeeService.fetchEmployeeList();
        assertEquals(expected, employees);
        verify(employeeRepository).findAll();
    }

    @Test
    void updateEmployeeHappyPath() {
        Employee employee = new Employee();
        employee.setId(5);
        employee.setFirstname("John");
        employee.setLastname("Doe");

        Employee newEmployee = new Employee();
        newEmployee.setFirstname("Jack");
        newEmployee.setLastname("Smith");

        Employee mergedEmployee = new Employee();
        mergedEmployee.setId(5);
        mergedEmployee.setFirstname("Jack");
        mergedEmployee.setLastname("Smith");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        employeeService.updateEmployee(newEmployee, employee.getId());
        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).save(mergedEmployee);
    }

    @Test
    void updateEmployeeThatDoesntExist() {
        Employee employee = new Employee();
        employee.setId(5);
        employee.setFirstname("John");
        employee.setLastname("Doe");

        Employee newEmployee = new Employee();
        newEmployee.setFirstname("Jack");
        newEmployee.setLastname("Smith");

        Employee mergedEmployee = new Employee();
        mergedEmployee.setId(5);
        mergedEmployee.setFirstname("Jack");
        mergedEmployee.setLastname("Smith");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());
        assertThrows(
                EntityNotFoundException.class,
                () -> employeeService.updateEmployee(newEmployee, employee.getId()),
                "Expected EntityNotFoundException was not thrown"
        );

        verify(employeeRepository).findById(employee.getId());
    }

    @Test
    void deleteEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstname("John");
        employee.setLastname("Doe");
        employee.setId(1);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(employee.getId());
        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).deleteById(employee.getId());
    }

    @Test
    void deleteEmployeeByIdEmployeeDoesntExist() {
        Integer idDoesntExist = 123;
        when(employeeRepository.findById(idDoesntExist)).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> employeeService.deleteEmployeeById(idDoesntExist),
                "Expected EntityNotFoundException was not thrown"
        );
        verify(employeeRepository).findById(idDoesntExist);
    }

}