package com.studentmisportal.backend.dto;
import com.studentmisportal.backend.entity.type.*;
import lombok.Data;

@Data
public class StudentDetailsDto {
    private String address;
    private String city;
    private String state;
    private BloodGroupType bloodGroup;
    private ReligionType religion;
    private CategoryType category;
    private CourseType course;
    private String dateOfBirth;
    private GenderType gender;
    private String dateOfAdmission;
    private String profileImage;
}