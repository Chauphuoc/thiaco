package com.example.thiaco.service.department;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> findAllByDeletedIsFalse() {
        return departmentRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
