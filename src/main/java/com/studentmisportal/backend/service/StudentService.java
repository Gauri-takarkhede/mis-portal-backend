package com.studentmisportal.backend.service;
import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.StudentDetailsDto;
import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.entity.StudentDetails;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import com.studentmisportal.backend.repository.StudentDetailsRepository;
import com.studentmisportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StudentDetailsRepository studentDetailsRepository;

    public List<UserDto> getAllStudents()
    {
        List<User> detailsList =
                studentDetailsRepository.findStudentsWithDetails(RoleType.STUDENT);

        return detailsList.stream()
                .map(user -> {

                    UserDto dto = modelMapper.map(user, UserDto.class);
                    dto.setDepartment(user.getDepartment().getDepartmentName());
                    dto.setRole(user.getRole());

                    if(user.getStudentDetails()!=null) {
                        StudentDetailsDto detailsDto =
                                modelMapper.map(user.getStudentDetails(), StudentDetailsDto.class);

                        dto.setStudentDetails(detailsDto);
                    }

                    return dto;
                })
                .toList();

    }

    public UserDto getStudentByMis(String mis)
    {
        User student =  userRepository.findByMisAndRole(mis, RoleType.STUDENT)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return  modelMapper.map(student, UserDto.class);
    }

    public ApiResponseDto addStudentDetails(String mis, StudentDetailsDto dto) {

        User user = userRepository.findByMis(mis)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<StudentDetails> existingDetails =
                studentDetailsRepository.findByUser(user);

        StudentDetails studentDetails;

        if (existingDetails.isPresent()) {
            // UPDATE CASE
            studentDetails = existingDetails.get();
        } else {
            // INSERT CASE
            studentDetails = new StudentDetails();
            studentDetails.setUser(user);
        }

        studentDetails.setAddress(dto.getAddress());
        studentDetails.setCity(dto.getCity());
        studentDetails.setState(dto.getState());
        studentDetails.setBloodGroup(dto.getBloodGroup());
        studentDetails.setReligion(dto.getReligion());
        studentDetails.setCategory(dto.getCategory());
        studentDetails.setCourse(dto.getCourse());
        studentDetails.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        studentDetails.setGender(dto.getGender());
        studentDetails.setDateOfAdmission(LocalDate.parse(dto.getDateOfAdmission()));

        studentDetailsRepository.save(studentDetails);

        return new ApiResponseDto("Student Details saved successfully");
    }
}
