package com.studentmisportal.backend.repository;
import com.studentmisportal.backend.entity.Bonafide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonafideRepository extends JpaRepository<Bonafide, Long> {

     List<Bonafide> findByStudentMis(String mis);
}
