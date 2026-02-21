package com.studentmisportal.backend.dto;

import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.entity.type.DepartmentType;
import com.studentmisportal.backend.entity.type.RoleType;
import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String mis;
    private RoleType role;
    private DepartmentType department;
}
