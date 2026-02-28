package com.studentmisportal.backend.dto;

import lombok.Data;

@Data
public class ElectiveSubjectResponseDto {
    private Long id;
    private  String subjectName;
    private Integer maxLimit;
}
