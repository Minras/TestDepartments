package com.wisetechglobal.employees.service;

import com.wisetechglobal.employees.persistence.entity.Department;
import com.wisetechglobal.employees.persistence.repository.DepartmentRepository;
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

class DepartmentServiceImplTest {

    private DepartmentRepository departmentRepository;
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        departmentService = new DepartmentServiceImpl(departmentRepository);
    }

    @Test
    void saveDepartment() {
        Department department = new Department();
        department.setName("HR");
        when(departmentRepository.save(ArgumentMatchers.any(Department.class))).thenReturn(department);

        Department departmentDb = departmentService.saveDepartment(department);
        assertThat(departmentDb.getName()).isSameAs(department.getName());
        verify(departmentRepository).save(department);
    }

    @Test
    void fetchDepartmentList() {
        List<Department> departments = new ArrayList();
        departments.add(new Department());
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> expected = departmentService.fetchDepartmentList();
        assertEquals(expected, departments);
        verify(departmentRepository).findAll();
    }

    @Test
    void updateDepartmentHappyPath() {
        Department department = new Department();
        department.setId(5);
        department.setName("HR");

        Department newDepartment = new Department();
        newDepartment.setName("Finance");

        Department mergedDepartment = new Department();
        mergedDepartment.setId(5);
        mergedDepartment.setName("Finance");

        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.updateDepartment(newDepartment, department.getId());
        verify(departmentRepository).findById(department.getId());
        verify(departmentRepository).save(mergedDepartment);
    }

    @Test
    void updateDepartmentThatDoesntExist() {
        Department department = new Department();
        department.setId(5);
        department.setName("HR");

        Department newDepartment = new Department();
        newDepartment.setName("Finance");

        Department mergedDepartment = new Department();
        mergedDepartment.setId(5);
        mergedDepartment.setName("Finance");

        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        assertThrows(
                EntityNotFoundException.class,
                () -> departmentService.updateDepartment(newDepartment, department.getId()),
                "Expected EntityNotFoundException was not thrown"
        );

        verify(departmentRepository).findById(department.getId());
    }

    @Test
    void deleteDepartmentById() {
        Department department = new Department();
        department.setName("HR");
        department.setId(1);
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        departmentService.deleteDepartmentById(department.getId());
        verify(departmentRepository).findById(department.getId());
        verify(departmentRepository).deleteById(department.getId());
    }

    @Test
    void deleteDepartmentByIdDepartmentDoesntExist() {
        Integer idDoesntExist = 123;
        when(departmentRepository.findById(idDoesntExist)).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> departmentService.deleteDepartmentById(idDoesntExist),
                "Expected EntityNotFoundException was not thrown"
        );
        verify(departmentRepository).findById(idDoesntExist);
    }
}