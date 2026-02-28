package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.CreateElectiveRequestDto;
import com.studentmisportal.backend.dto.CreateElectiveResponseDto;
import com.studentmisportal.backend.service.ElectiveService;
import com.studentmisportal.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elective")
@RequiredArgsConstructor
public class ElectiveController {

        private final ElectiveService electiveService;
        private final UserService userService;

        @PostMapping
        @PreAuthorize("hasRole('FACULTY')")
        public ResponseEntity<CreateElectiveResponseDto> createElective(
                @RequestBody CreateElectiveRequestDto request, HttpServletRequest req) {

            CreateElectiveResponseDto elective = electiveService.createElective(request, userService.getUserName(req));

            return ResponseEntity.ok(elective);
        }

        @GetMapping
        public ResponseEntity<List<CreateElectiveResponseDto>> getElective(){
            return ResponseEntity.ok(electiveService.findAllElectives());
        }

}
