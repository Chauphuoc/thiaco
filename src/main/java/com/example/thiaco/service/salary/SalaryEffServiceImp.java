package com.example.thiaco.service.salary;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.repository.SalaryCoEffRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryEffServiceImp implements ISalaryEffService{
    @Autowired
    private SalaryCoEffRepository salaryCoEffRepository;
    @Override
    public List<SalaryCoEfficient> findAll() {
        return null;
    }

    @Override
    public Optional<SalaryCoEfficient> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public SalaryCoEfficient save(SalaryCoEfficient salaryCoEfficient) {
        return null;
    }

    @Override
    public void delete(SalaryCoEfficient salaryCoEfficient) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<SalaryCoEfficient> findSalaryCoEfficientsByDepartment(Department department) {
        return salaryCoEffRepository.findSalaryCoEfficientsByDepartment(department);
    }

    @Override
    public List<SalaryCoEfficient> getSalaryCoEfficientsByDeletedIsFalse() {
        return salaryCoEffRepository.getSalaryCoEfficientsByDeletedIsFalse();
    }
}
