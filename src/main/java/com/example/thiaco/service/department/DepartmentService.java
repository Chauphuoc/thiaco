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
    public Optional<Department> findById() {
        return Optional.empty();
    }


    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public void delete(Department department) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
