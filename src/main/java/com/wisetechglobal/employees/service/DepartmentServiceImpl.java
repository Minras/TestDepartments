package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.entity.Department;
import com.wisetechglobal.employees.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.List;

@Service
// Class implementing DepartmentService class
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Integer departmentId) {

        Department departmentDb = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getName())
                && !"".equalsIgnoreCase(
                department.getName())) {
            departmentDb.setName(department.getName());
        }

        return departmentRepository.save(departmentDb);
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        departmentRepository.deleteById(id);
    }
}
