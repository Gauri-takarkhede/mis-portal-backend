package com.studentmisportal.backend.controller;

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
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequestDto newUser){

        System.out.println(newUser+" incontroller");
        String registrationResponse = userService.registerUser(newUser);
        return ResponseEntity.ok(registrationResponse);
    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest loginReq){
        String loginResponse = userService.loginUser(loginReq);
        return ResponseEntity.ok(loginResponse);
    }
}
