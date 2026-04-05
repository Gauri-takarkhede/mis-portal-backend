package com.studentmisportal.backend.dto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ScholarshipResponseDto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private LocalDate creationDate;
    private String fileUrl;
}
