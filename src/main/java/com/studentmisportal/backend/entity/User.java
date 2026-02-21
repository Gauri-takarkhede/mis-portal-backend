package com.studentmisportal.backend.entity;

import com.studentmisportal.backend.entity.type.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    @Column(unique = true)
    private String mis;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
    private Department department;
}