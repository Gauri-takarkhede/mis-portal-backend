package com.studentmisportal.backend.service;
import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.FacultyDetailsDto;
import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.entity.FacultyDetails;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import com.studentmisportal.backend.repository.FacultyDetailsRepository;
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
public class FacultyService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StudentDetailsRepository studentDetailsRepository;
    private final FacultyDetailsRepository facultyDetailsRepository;

    public List<UserDto> getAllFaculties()
    {
        List<User> allFaculties= userRepository.findByRole(RoleType.FACULTY);

        return allFaculties.stream()
                .map(user-> {
                    UserDto stuDto = modelMapper.map(user, UserDto.class);
                    stuDto.setDepartment(user.getDepartment().getDepartmentName());
                    stuDto.setRole(user.getRole());
                    return stuDto;
                }).toList();
    }

    public UserDto getFacultyByMis(String mis)
    {
        User faculty =  userRepository.findByMisAndRole(mis, RoleType.FACULTY)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return  modelMapper.map(faculty, UserDto.class);
    }

    public ApiResponseDto addFacultyDetails(String mis, FacultyDetailsDto dto) {

        User user = userRepository.findByMis(mis)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<FacultyDetails> existingDetails =
                facultyDetailsRepository.findByUser(user);

        FacultyDetails facultyDetails;

        if (existingDetails.isPresent()) {
            // UPDATE CASE
            facultyDetails = existingDetails.get();
        } else {
            // INSERT CASE
            facultyDetails = new FacultyDetails();
            facultyDetails.setUser(user);
        }

        facultyDetails.setAddress(dto.getAddress());
        facultyDetails.setCity(dto.getCity());
        facultyDetails.setState(dto.getState());
        facultyDetails.setBloodGroup(dto.getBloodGroup());
        facultyDetails.setReligion(dto.getReligion());
        facultyDetails.setCategory(dto.getCategory());
        facultyDetails.setCourse(dto.getCourse());
        facultyDetails.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        facultyDetails.setGender(dto.getGender());
        facultyDetails.setDateOfJoining(LocalDate.parse(dto.getDateOfJoining()));
        facultyDetails.setSpecialization(dto.getSpecialization());
        facultyDetails.setDesignation(dto.getDesignation());

        facultyDetailsRepository.save(facultyDetails);

        return new ApiResponseDto("Student Details saved successfully");
    }
}
