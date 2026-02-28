package com.studentmisportal.backend.dto;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateElectiveResponseDto {
    private Long id;
    private String moduleName;
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate registrationDeadline;
    private Boolean isPublished;
    private List<ElectiveSubjectResponseDto> electiveSubjects;
}
