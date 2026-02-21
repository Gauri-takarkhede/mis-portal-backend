package com.studentmisportal.backend.service;

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

    public String registerUser(UserRegisterRequestDto userRequest){
        System.out.println(userRequest);
        boolean userExits = this.userRepository.existsByMis(userRequest.getMis());
        if(userExits){
            return "User already exists";
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

        return "User registered successfully";
    }

    public String loginUser(UserLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMis(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(request.getMis());
        }

        throw new RuntimeException("Invalid credentials");
    }

}
