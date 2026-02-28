package com.studentmisportal.backend.dto;

import lombok.Data;

@Data
public class ELectiveSubjectRequestDto {
    private String subjectName;
    private Integer maxLimit;
}
