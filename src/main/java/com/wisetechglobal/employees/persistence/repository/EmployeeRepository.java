package com.wisetechglobal.employees.persistence.repository;

import com.wisetechglobal.employees.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
