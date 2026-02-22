package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
