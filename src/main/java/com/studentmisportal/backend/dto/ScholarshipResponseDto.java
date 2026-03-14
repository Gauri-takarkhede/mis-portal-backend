package com.studentmisportal.backend.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScholarshipResponseDto {
    private long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private LocalDate creationDate;
    private String fileUrl;
}
