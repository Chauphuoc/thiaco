package com.example.thiaco.repository;

import com.example.thiaco.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findAllByDeletedIsFalse();

}
