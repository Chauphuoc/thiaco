package com.example.thiaco.service.salary;

import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryService implements ISalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Override
    public List<Salary> findAll() {
        return null;
    }

    @Override
    public Optional<Salary> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void delete(Salary salary) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
