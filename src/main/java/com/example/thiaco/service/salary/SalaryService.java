package com.example.thiaco.service.salary;

import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@EnableScheduling
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

    @Override
    public Salary findSalaryByEmployee(Employee employee) {
        return salaryRepository.findSalaryByEmployee(employee);
    }

//    @Scheduled(cron = "0 0 0 1 1 ?")
//    public void updateSalaryCoEfficient() {
//        List<Salary> salaries = salaryRepository.findAll();
//        for (Salary item: salaries) {
//            BigDecimal currentCoEff = item.getSalaryCoEfficient();
//            BigDecimal newCoEff = currentCoEff.add(BigDecimal.valueOf(0.1));
//            item.setSalaryCoEfficient(newCoEff);
//            salaryRepository.save(item);
//        }
//    }
}
