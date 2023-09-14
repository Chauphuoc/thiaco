package com.example.thiaco.service.employee;

import com.example.thiaco.dto.*;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.repository.DepartmentRepository;
import com.example.thiaco.repository.EmployeeRepository;
import com.example.thiaco.repository.LocationRegionRepository;
import com.example.thiaco.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Employee> saveAll(List<Employee> products) {
        return null;
    }

    @Override
    public List<Employee> findEmployeesByDeletedIsFalse() {
        return employeeRepository.findEmployeesByDeletedIsFalse();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Employee findEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findEmployeeByEmployeeId(employeeId);
    }




}
