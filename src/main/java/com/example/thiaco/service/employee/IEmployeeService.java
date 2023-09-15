package com.example.thiaco.service.employee;

import com.example.thiaco.dto.EmployeeReqDTO;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;

import java.util.List;

public interface IEmployeeService extends IGeneralService<Employee,Long> {
    List<Employee> saveAll(List<Employee> products);
    List<Employee> findEmployeesByDeletedIsFalse();

     Employee findEmployeeById(Long id);


    Employee findEmployeeByEmployeeId(Long employeeId);
    Employee update(EmployeeReqDTO employeeReqDTO);
}
