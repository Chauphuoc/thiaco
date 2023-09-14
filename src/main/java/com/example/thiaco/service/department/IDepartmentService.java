package com.example.thiaco.service.department;

import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService extends IGeneralService<Department,Long> {
    public List<Department> findAllByDeletedIsFalse();

    public Optional<Department> findById();
}
