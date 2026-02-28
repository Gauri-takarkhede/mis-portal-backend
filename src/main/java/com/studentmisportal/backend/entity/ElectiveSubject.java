package com.studentmisportal.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "elective_subject")
public class ElectiveSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String subjectName;

    private Integer maxLimit;

    @ManyToOne
    @JoinColumn(name = "elective_id")
    private  Elective elective;

}
