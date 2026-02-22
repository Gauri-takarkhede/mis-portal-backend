package com.studentmisportal.backend.service;
import com.studentmisportal.backend.dto.UserDto;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import com.studentmisportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> getAllFaculties()
    {
        List<User> allFaculties= userRepository.findByRole(RoleType.FACULTY);

        return allFaculties.stream()
                .map(user-> {
                    UserDto stuDto = modelMapper.map(user, UserDto.class);
                    stuDto.setDepartment(user.getDepartment().getDepartmentName());
                    stuDto.setRole(user.getRole());
                    return stuDto;
                }).toList();
    }

    public UserDto getFacultyByMis(String mis)
    {
        User faculty =  userRepository.findByMisAndRole(mis, RoleType.FACULTY)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return  modelMapper.map(faculty, UserDto.class);
    }
}
