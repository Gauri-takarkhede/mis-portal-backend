package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.ScholarshipRequestDto;
import com.studentmisportal.backend.dto.ScholarshipResponseDto;
import com.studentmisportal.backend.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scholarship")
@RequiredArgsConstructor
public class ScholarshipController {
    private final ScholarshipService scholarshipService;

    @GetMapping("/")
    public ResponseEntity<List<ScholarshipResponseDto>> getAllScholarships() {
        return ResponseEntity.ok(scholarshipService.getAllScholarships());
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseDto> uploadScholarship(@ModelAttribute ScholarshipRequestDto scholarshipDetails) throws IOException {
        String fileName = scholarshipService.uploadFile(scholarshipDetails);
        return ResponseEntity.ok(new ApiResponseDto(fileName));
    }
}
