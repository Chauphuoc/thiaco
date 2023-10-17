package com.example.thiaco.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEffReqDTO {
    private Long id;
    @NotEmpty(message = "Số năm không được để trống.")
    private String year;
    @NotNull(message = "Hệ số lương không được để trống.")
    @Pattern(regexp = "^-?\\d+(\\.\\d+)?$",message = "Hệ số lương không đúng định dạng.")
    private BigDecimal salaryEfficientAmount;

    @NotEmpty(message = "Mã số nhân viên không được để trống.")
    @Pattern(regexp = "^\\d{4}$",message = "CMND phải là 9 số")
    private String employeeId;

    @NotEmpty(message = "Vùng không được để trống.")
    private String area;
}
