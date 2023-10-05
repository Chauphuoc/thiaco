package com.example.thiaco.service.salary;

import com.example.thiaco.dto.SalaryEffDTO;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.service.IGeneralService;

import java.util.List;

public interface ISalaryEffService extends IGeneralService<SalaryCoEfficient,Long> {
    List<SalaryCoEfficient> findSalaryCoEfficientsByDepartment(Department department);

    List<SalaryCoEfficient> getSalaryCoEfficientsByDeletedIsFalse();

    public SalaryCoEfficient create(SalaryEffDTO salaryEffDTO);
}
