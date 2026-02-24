package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.FacultyDetailsDto;
import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<List<UserDto>> getStudentProfiles() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/profile/{mis}")
    public ResponseEntity<UserDto> getStudentProfileById(@PathVariable String mis) {
        return ResponseEntity.ok(facultyService.getFacultyByMis(mis));
    }

    @PostMapping("/add-details/{mis}")
    public ResponseEntity<ApiResponseDto> addStudentDetails(@PathVariable String mis,
                                                            @Valid @RequestBody FacultyDetailsDto dto) {
        System.out.println("I am in faculty details controller");
        ApiResponseDto response = facultyService.addFacultyDetails(mis, dto);
        return ResponseEntity.ok(response);
    }
}
