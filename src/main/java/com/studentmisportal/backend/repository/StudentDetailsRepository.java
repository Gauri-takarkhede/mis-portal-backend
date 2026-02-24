package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.StudentDetails;
import com.studentmisportal.backend.entity.User;
import com.studentmisportal.backend.entity.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {

    @Query("""
    SELECT u
    FROM User u
    LEFT JOIN FETCH u.studentDetails
    WHERE u.role = :role
""")
    List<User> findStudentsWithDetails(RoleType role);

    Optional<StudentDetails> findByUser(User user);
}
