package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.Department;
import com.studentmisportal.backend.entity.type.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDepartmentName(DepartmentType departmentName);
    public boolean existsByDepartmentName(DepartmentType departmentName);

}
