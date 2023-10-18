package com.example.thiaco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqUpDepartDTO {
    private DepartmentDTO departmentDTO;
    private BigDecimal salaryCoEfficient;
    private BigDecimal basicSalary;
    private String position;
}
