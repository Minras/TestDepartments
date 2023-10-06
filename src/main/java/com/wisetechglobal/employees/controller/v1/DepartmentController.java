package com.wisetechglobal.employees.controller.v1;

import com.wisetechglobal.employees.persistence.entity.Department;
import com.wisetechglobal.employees.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<Department> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Integer id) {
        return departmentService.updateDepartment(department, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable("id") Integer id) {
        departmentService.deleteDepartmentById(id);
    }
}
