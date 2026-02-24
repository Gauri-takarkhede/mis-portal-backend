package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.FacultyDetails;
import com.studentmisportal.backend.entity.StudentDetails;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacultyDetailsRepository extends JpaRepository<FacultyDetails, Long> {
    @Query("""
    SELECT u
    FROM User u
    LEFT JOIN FETCH u.facultyDetails
    WHERE u.role = :role
""")
    List<User> findFacultyWithDetails(RoleType role);

    Optional<FacultyDetails> findByUser(User user);
}
