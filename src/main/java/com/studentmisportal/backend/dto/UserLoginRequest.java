package com.studentmisportal.backend.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String mis;
    private String password;
}
