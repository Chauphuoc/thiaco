package com.example.thiaco.service.employee;

import com.example.thiaco.dto.*;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.exception.ResourceNotFoundException;
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
    @Override
    public Employee update(EmployeeReqDTO employeeReqDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeReqDTO.getId());
        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Not found employee");
        }
        Employee employee = optionalEmployee.get();
        employee.setEmployee_id(employeeReqDTO.getEmployee_id());
        employee.setFullName(employeeReqDTO.getFullName());
        employee.setLastName(employeeReqDTO.getLastName());
        employee.setDateOfBirth(employeeReqDTO.getDateOfBirth());
        employee.setGender(employeeReqDTO.getGender());
        employee.setAge(employeeReqDTO.getAge());
        employee.setPlaceOfBirth(employeeReqDTO.getPlaceOfBirth());
        employee.setQualification(employeeReqDTO.getQualification());
        employee.setEducationLevel(employeeReqDTO.getEducationLevel());
        employee.setCulturalLevel(employeeReqDTO.getCulturalLevel());
        employee.setHomeTown(employeeReqDTO.getHomeTown());
        employee.setAccommodation(employeeReqDTO.getAccommodation());
        employee.setMaritalStatus(employeeReqDTO.getMaritalStatus());
        employee.setPosition(employeeReqDTO.getPosition());
        employee.setJoiningday(employeeReqDTO.getJoiningday());
        employee.setEmploymentContractDate(employeeReqDTO.getEmploymentContractDate());
        employee.setSocialInsuranceNumber(employeeReqDTO.getSocialInsuranceNumber());
        employee.setPhoneNumber(employeeReqDTO.getPhoneNumber());
        employee.setIdCardNumber(employeeReqDTO.getIdCardNumber());
        employee.setCitizenCardNumber(employeeReqDTO.getCitizenCardNumber());
        employee.setDateOfIssue(employeeReqDTO.getDateOfIssue());
        employee.setPlaceOfIssue(employeeReqDTO.getPlaceOfIssue());

        employee.setDepartment(employeeReqDTO.getDepartmentDTO().toDepartment());
        employee.setLocationRegion(employeeReqDTO.getLocationRegionDTO().toLocationRegion());
        employee.setSalary(employeeReqDTO.getSalaryDTO().toSalary());
        return employee;
//        private DepartmentDTO departmentDTO;
//        private LocationRegionDTO locationRegionDTO;
//        private SalaryDTO salaryDTO;
    }




}
