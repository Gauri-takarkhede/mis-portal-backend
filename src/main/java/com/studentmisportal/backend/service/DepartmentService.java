package com.studentmisportal.backend.service;

import com.studentmisportal.backend.dto.NewDepartmentDto;
import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public String addDepartment(NewDepartmentDto department) {
        boolean deptExists = departmentRepository.existsByDepartmentName(department.getDepartmentName());
        if (deptExists) {
            return "Department already exists";
        }
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(department.getDepartmentName());
        newDepartment.setSubjects(department.getSubjects());
        departmentRepository.save(newDepartment);

        return "Department added successfully";
    }
}
