package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.Elective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectiveRepository extends JpaRepository<Elective, Long> {
}
