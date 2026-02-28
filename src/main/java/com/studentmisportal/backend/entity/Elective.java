package com.studentmisportal.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "elective")
public class Elective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName;

    private String createdBy;

    private LocalDate createdDate;

    private LocalDate registrationDeadline;

    private Boolean isPublished;

    @OneToMany(mappedBy = "elective", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectiveSubject> electiveSubjects;
}