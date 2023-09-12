package com.example.thiaco.repository;

import com.example.thiaco.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findAllByDeletedIsFalse();
}
