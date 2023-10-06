package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Department;
import com.wisetechglobal.employees.persistence.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final String MSG_TPL_ENTITY_DOESNT_EXIST = "Department with id=%d doesn't exist";

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(final Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(final Department department, final Integer id) {

        Optional<Department> result = departmentRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(String.format(MSG_TPL_ENTITY_DOESNT_EXIST, id));
        }
        Department departmentDb = result.get();
        if (Objects.nonNull(department.getName())) {
            departmentDb.setName(department.getName());
        }
        return departmentRepository.save(departmentDb);
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        Optional<Department> result = departmentRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(String.format(MSG_TPL_ENTITY_DOESNT_EXIST, id));
        }
        departmentRepository.deleteById(id);
    }
}
