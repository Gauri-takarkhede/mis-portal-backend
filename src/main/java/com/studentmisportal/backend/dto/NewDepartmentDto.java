package com.studentmisportal.backend.dto;

import com.studentmisportal.backend.entity.type.DepartmentType;
import lombok.Data;

import java.util.List;

@Data
public class NewDepartmentDto {
    private DepartmentType departmentName;
    private List<String> subjects;
}
