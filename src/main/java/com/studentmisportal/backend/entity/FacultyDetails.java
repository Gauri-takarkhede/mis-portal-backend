package com.studentmisportal.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "faculty_details")
public class FacultyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileImage;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}