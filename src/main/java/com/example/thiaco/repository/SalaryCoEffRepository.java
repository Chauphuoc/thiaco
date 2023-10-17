package com.example.thiaco.repository;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryCoEffRepository extends JpaRepository<SalaryCoEfficient,Long> {
    List<SalaryCoEfficient> getSalaryCoEfficientsByDeletedIsFalse();

//    @Query("SELECT sa FROM SalaryCoEfficient as sa WHERE sa.department.departmentName = :name AND sa.year = :year AND sa.salaryEfficientAmount = :eff AND sa.deleted = false")
//    SalaryCoEfficient findSalaryCoEfficientByDepartmentYearSalary(String name, String year, BigDecimal eff);
}
