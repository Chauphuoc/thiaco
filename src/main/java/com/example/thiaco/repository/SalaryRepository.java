package com.example.thiaco.repository;

import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Salary findSalaryByEmployee(Employee employee);


}
