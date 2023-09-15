package com.example.thiaco.dto;

import com.example.thiaco.model.department.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Year;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryEffDTO {

    private Long id;
    private String year;
    private BigDecimal salaryEfficientAmount;
    private DepartmentDTO departmentDTO;
}
