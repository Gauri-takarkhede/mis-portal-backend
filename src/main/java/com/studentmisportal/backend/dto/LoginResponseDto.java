package com.studentmisportal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String message;
    private String token;
    private String mis;
}