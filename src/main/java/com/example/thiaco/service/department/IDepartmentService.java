package com.example.thiaco.service.department;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;

import java.util.List;

public interface IDepartmentService extends IGeneralService<Employee,Long> {
    public List<Department> findAllByDeletedIsFalse();
}
