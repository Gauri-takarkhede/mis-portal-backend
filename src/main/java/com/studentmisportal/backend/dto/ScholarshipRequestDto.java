package com.studentmisportal.backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ScholarshipRequestDto {
    private String title;
    private String description;
    private LocalDate deadline;
    private MultipartFile file;
}
