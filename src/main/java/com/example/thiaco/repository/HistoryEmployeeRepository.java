package com.example.thiaco.repository;

import com.example.thiaco.model.employee.HistoryEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryEmployeeRepository extends JpaRepository<HistoryEmployee, Long> {
    List<HistoryEmployee> findHistoryEmployeesByEmployeeId(Long manv);
}
