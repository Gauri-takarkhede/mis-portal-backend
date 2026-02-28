package com.studentmisportal.backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateElectiveRequestDto {
    private String moduleName;
    private LocalDate registrationDeadline;
    private List<ELectiveSubjectRequestDto> subjects;
}
