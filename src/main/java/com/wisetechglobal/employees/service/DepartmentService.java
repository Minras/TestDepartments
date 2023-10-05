package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department updateDepartment(Department department, Integer id);

    void deleteDepartmentById(Integer id);
}

