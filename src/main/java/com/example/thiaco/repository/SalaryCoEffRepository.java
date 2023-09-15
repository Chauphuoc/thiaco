package com.example.thiaco.repository;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryCoEffRepository extends JpaRepository<SalaryCoEfficient,Long> {
    List<SalaryCoEfficient> getSalaryCoEfficientsByDeletedIsFalse();
    List<SalaryCoEfficient> findSalaryCoEfficientsByDepartment(Department department);
}
