package com.studentmisportal.backend.service;

import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mis) throws UsernameNotFoundException {
        User user = userRepository.findByMis(mis)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserInfoDetails(user);
    }
}