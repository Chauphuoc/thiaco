package com.example.thiaco.dto;

import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryDTO {

    private Long id;
    private int yearOfWork;
    private BigDecimal salaryAmount;
    private BigDecimal salaryCoEfficient;
    private String otherDetails;
    private BigDecimal basicSalary;

    public Salary toSalary() {
        return new Salary()
                .setId(id)
                .setYearOfWork(yearOfWork)
                .setSalaryAmount(salaryAmount)
                .setSalaryCoEfficient(salaryCoEfficient)
                .setOtherDetails(otherDetails)
                .setBasicSalary(basicSalary)
                ;
    }
}
