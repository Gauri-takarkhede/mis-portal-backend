package com.studentmisportal.backend.service;

import com.studentmisportal.backend.dto.ApiResponseDto;
import com.studentmisportal.backend.dto.LoginResponseDto;
import com.studentmisportal.backend.dto.UserLoginRequest;
import com.studentmisportal.backend.dto.UserRegisterRequestDto;
import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.repository.DepartmentRepository;
import com.studentmisportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ApiResponseDto registerUser(UserRegisterRequestDto userRequest){
        System.out.println(userRequest);
        boolean userExits = this.userRepository.existsByMis(userRequest.getMis());
        if(userExits){
            return new ApiResponseDto("User already exists");
        }
        User user = new User();
        user.setMis(userRequest.getMis());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        Department department = departmentRepository
                .findByDepartmentName(userRequest.getDepartment());
        user.setDepartment(department);
        user.setPhone(userRequest.getPhone());
        userRepository.save(user);

        return new ApiResponseDto("User registered successfully");
    }

    public LoginResponseDto loginUser(UserLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMis(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            User user = userRepository.findByMis(request.getMis())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(user);

            return new LoginResponseDto(
                    "Login successful",
                    token,
                    user.getMis()
            );
        }

        throw new RuntimeException("Invalid credentials");
    }

}
