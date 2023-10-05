package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Department;
import com.wisetechglobal.employees.persistence.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Integer id) {

        Department departmentDb = departmentRepository.findById(id).get();

        if (Objects.nonNull(department.getName())
                && !department.getName().isEmpty()) {
            departmentDb.setName(department.getName());
        }

        return departmentRepository.save(departmentDb);
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        departmentRepository.deleteById(id);
    }
}
