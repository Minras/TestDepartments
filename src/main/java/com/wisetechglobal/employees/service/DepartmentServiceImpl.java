package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Department;
import com.wisetechglobal.employees.persistence.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

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
