package com.example.thiaco.service.salary;

import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.service.IGeneralService;


public interface ISalaryService extends IGeneralService<Salary,Long> {
    Salary findSalaryByEmployee(Employee employee);
}
