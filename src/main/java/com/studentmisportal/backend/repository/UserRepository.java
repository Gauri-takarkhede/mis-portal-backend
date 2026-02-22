package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByMis(String mis);
    Optional<User> findByMis(String mis);
    List<User> findByRole(RoleType role);
    Optional<User> findByMisAndRole(String mis, RoleType role);
}
