package com.studentmisportal.backend.controller;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.LoginResponseDto;
import com.studentmisportal.backend.dto.UserLoginRequest;
import com.studentmisportal.backend.dto.UserRegisterRequestDto;
import com.studentmisportal.backend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody UserLoginRequest loginReq,
            HttpServletResponse response) {

        LoginResponseDto loginResponse = userService.loginUser(loginReq);

        Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);
        
        return ResponseEntity.ok(new LoginResponseDto(loginResponse.getMessage(), loginResponse.getAccessToken(),"", loginResponse.getMis()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String refreshToken = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LoginResponseDto response = userService.getRefreshToken(refreshToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDto> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // delete cookie
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);

        return ResponseEntity.ok(new ApiResponseDto("User has been logged out"));
    }
}
