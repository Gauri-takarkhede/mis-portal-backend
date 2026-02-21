package com.studentmisportal.backend.service;

import com.studentmisportal.backend.dto.UserRegisterRequestDto;
import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.repository.DepartmentRepository;
import com.studentmisportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public String registerUser(UserRegisterRequestDto userRequest){

        boolean userExits = this.userRepository.existsByMis(userRequest.getMis());
        if(userExits){
            return "User already exists";
        }
        User user = new User();
        user.setMis(userRequest.getMis());
        user.setPassword(userRequest.getPassword());
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

}
