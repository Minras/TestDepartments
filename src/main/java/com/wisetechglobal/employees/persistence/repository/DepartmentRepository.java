package com.wisetechglobal.employees.persistence.repository;

import com.wisetechglobal.employees.persistence.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
