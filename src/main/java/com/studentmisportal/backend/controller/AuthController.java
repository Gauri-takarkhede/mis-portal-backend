package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.LoginResponseDto;
import com.studentmisportal.backend.dto.UserLoginRequest;
import com.studentmisportal.backend.dto.UserRegisterRequestDto;
import com.studentmisportal.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto> register(@Valid @RequestBody UserRegisterRequestDto newUser){
        ApiResponseDto registrationResponse = userService.registerUser(newUser);
        return ResponseEntity.ok(registrationResponse);
    }

    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDto> login(@Valid @RequestBody UserLoginRequest loginReq){
        LoginResponseDto loginResponse = userService.loginUser(loginReq);
        return ResponseEntity.ok(loginResponse);
    }
}
