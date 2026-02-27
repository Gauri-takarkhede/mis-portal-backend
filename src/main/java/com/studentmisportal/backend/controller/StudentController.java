package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.StudentDetailsDto;
import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/profiles")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<List<UserDto>> getStudentProfiles() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/profile/{mis}")
    public ResponseEntity<UserDto> getStudentProfileById(@PathVariable String mis) {
        return ResponseEntity.ok(studentService.getStudentByMis(mis));
    }

    @PostMapping("/add-details/{mis}")
    public ResponseEntity<ApiResponseDto> addStudentDetails(@PathVariable String mis,
                                                            @Valid @RequestBody StudentDetailsDto dto) {
        ApiResponseDto response = studentService.addStudentDetails(mis, dto);
        return ResponseEntity.ok(response);
    }
}
