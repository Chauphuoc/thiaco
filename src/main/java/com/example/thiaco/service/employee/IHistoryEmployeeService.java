package com.example.thiaco.service.employee;

import com.example.thiaco.model.employee.HistoryEmployee;
import com.example.thiaco.service.IGeneralService;

import java.util.List;

public interface IHistoryEmployeeService extends IGeneralService<HistoryEmployee,Long> {
    List<HistoryEmployee> findHistoryEmployeesByEmployee_id(Long manv);
}
