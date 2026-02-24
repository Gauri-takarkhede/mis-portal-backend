package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.BonafideRequestDto;
import com.studentmisportal.backend.dto.BonafideResponseDto;
import com.studentmisportal.backend.service.BonafideService;
import com.studentmisportal.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bonafide")
@RequiredArgsConstructor
public class BonafideController {
    private final BonafideService bonafideService;
    private final UserService userService;

    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/all")
    public ResponseEntity<List<BonafideResponseDto>> getAllBonafides(){
        List<BonafideResponseDto> bonafides = bonafideService.getAllBonafides();
        return ResponseEntity.ok(bonafides);
    }

    @GetMapping("/")
    public ResponseEntity<List<BonafideResponseDto>> getBonafide(HttpServletRequest request){
        List<BonafideResponseDto> bonafides = bonafideService.getBonafides(userService.getMis(request));
        return ResponseEntity.ok(bonafides);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseDto> getBonafide(@Valid  @RequestBody BonafideRequestDto bonafideRequestDto, HttpServletRequest request){

        String response =  bonafideService.createBonafideRequest(
                bonafideRequestDto, userService.getMis(request), userService.getUserName(request)
        );
        System.out.println(response);
            return ResponseEntity.ok(
                   new ApiResponseDto(response)
            );

    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<ApiResponseDto> approveBonafide(@PathVariable Long id, HttpServletRequest request){

        return ResponseEntity.ok(new ApiResponseDto(bonafideService.approveBonafide(id, userService.getUserName(request))));
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<ApiResponseDto> rejectBonafide(@PathVariable Long id, HttpServletRequest request){

        return ResponseEntity.ok(new ApiResponseDto(bonafideService.rejectBonafide(id, userService.getUserName(request))));
    }

}
