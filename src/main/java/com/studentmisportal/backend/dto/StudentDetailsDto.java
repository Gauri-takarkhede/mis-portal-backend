package com.studentmisportal.backend.dto;
import com.studentmisportal.backend.entity.type.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDetailsDto {

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotNull
    private BloodGroupType bloodGroup;

    @NotNull
    private ReligionType religion;

    @NotNull
    private CategoryType category;

    @NotNull
    private CourseType course;

    @NotBlank
    private String dateOfBirth;

    @NotNull
    private GenderType gender;

    @NotBlank
    private String dateOfAdmission;

    private String profileImage;
}