package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.NewDepartmentDto;
import com.studentmisportal.backend.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<String> addDepartment(@Valid @RequestBody NewDepartmentDto department) {
        departmentService.addDepartment(department);
        return ResponseEntity.ok("Department added successfully");
    }

}
