package com.example.thiaco.repository;

import com.example.thiaco.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findEmployeesByDeletedIsFalse();

    Employee findEmployeeById(Long id);
}
