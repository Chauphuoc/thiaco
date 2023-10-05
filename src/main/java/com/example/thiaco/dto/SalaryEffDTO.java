package com.example.thiaco.dto;

import com.example.thiaco.model.department.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "Số năm không được để trống.")
    private String year;
    @NotNull(message = "Hệ số lương không được để trống.")
    @Pattern(regexp = "^-?\\d+(\\.\\d+)?$",message = "Hệ số lương không đúng định dạng.")
    private BigDecimal salaryEfficientAmount;
    @NotNull(message = "Phòng ban không được để trống")
    private DepartmentDTO departmentDTO;
}
