package com.studentmisportal.backend.dto;

import com.studentmisportal.backend.entity.type.DepartmentType;
import com.studentmisportal.backend.entity.type.RoleType;
import lombok.Data;

@Data
public class StudentDto {
    private String username;
    private String email;
    private String phone;
    private String mis;
    private RoleType role;
    private DepartmentType department;
    private StudentDetailsDto studentDetails;
}
