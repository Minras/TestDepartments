package com.wisetechglobal.employees.persistence.repository;

import com.wisetechglobal.employees.persistence.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
