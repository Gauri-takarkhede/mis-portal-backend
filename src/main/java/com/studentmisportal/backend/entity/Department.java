package com.studentmisportal.backend.entity;

import com.studentmisportal.backend.entity.type.DepartmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DepartmentType departmentName;

    private List<String> subjects;
}