package com.example.thiaco.service.salary;

import com.example.thiaco.dto.SalaryEffReqDTO;
import com.example.thiaco.enums.Earea;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<SalaryCoEfficient> findAll() {
        return null;
    }

    @Override
    public Optional<SalaryCoEfficient> findById(Long id) {
        return salaryCoEffRepository.findById(id);
    }

    @Override
    public SalaryCoEfficient save(SalaryCoEfficient salaryCoEfficient) {
        return salaryCoEffRepository.save(salaryCoEfficient);
    }

    @Override
    public void delete(SalaryCoEfficient salaryCoEfficient) {

    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public List<SalaryCoEfficient> getSalaryCoEfficientsByDeletedIsFalse() {
        return salaryCoEffRepository.getSalaryCoEfficients();
    }

    @Override
    public void create(SalaryEffReqDTO request) {
        SalaryCoEfficient salaryCoEfficient = new SalaryCoEfficient();
        salaryCoEfficient.setYear(request.getYear());
        salaryCoEfficient.setSalaryEfficientAmount(request.getSalaryEfficientAmount());

        Long employeeId = Long.parseLong(request.getEmployeeId()) ;
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByManv(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new DataInputException("Không tìm thấy nhân viên! Xin vui lòng nhập lại mã nhân viên!");
        } else {
            Employee employee = employeeOptional.get();
            salaryCoEfficient.setEmployee(employee);
        }
        salaryCoEfficient.setEarea(Earea.getArea(request.getArea()));

        salaryCoEffRepository.save(salaryCoEfficient);

    }
}
