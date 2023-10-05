package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department updateDepartment(Department department, Integer departmentId);

    void deleteDepartmentById(Integer departmentId);
}

