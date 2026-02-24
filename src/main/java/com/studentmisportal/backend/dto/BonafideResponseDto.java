package com.studentmisportal.backend.dto;

import com.studentmisportal.backend.entity.type.BonafideStatusType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BonafideResponseDto {
    private Long id;
    private String studentName;
    private String studentMis;
    private String reason;
    private BonafideStatusType status;
    private String approvedBy;
    private LocalDate approvedDate;
    private String rejectedBy;
    private LocalDate rejectedDate;
}
