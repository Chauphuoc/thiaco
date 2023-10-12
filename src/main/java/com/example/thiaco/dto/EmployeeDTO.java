package com.example.thiaco.dto;

import com.example.thiaco.enums.EStatus;
import com.example.thiaco.model.salary.Salary;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeDTO {
    private Long id;
    private Long employee_id;
    private String fullName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private int age;
    private String placeOfBirth;
    private String qualification;
    private String educationLevel;
    private String culturalLevel;
    private String homeTown;
    private String accommodation;
    private String maritalStatus;
    private String position;
    private String joiningday;
    private String employmentContractDate;
    private String socialInsuranceMonth;
    private String relationShip;
    private String socialInsuranceNumber;
    private String phoneNumber;
    private String idCardNumber;

    private LocalDate dateOfIssueCmnd;
    private String placeOfIssueCmnd;

    private String citizenCardNumber;
    private String dateOfIssue;
    private String placeOfIssue;
    private DepartmentDTO departmentDTO;
    private LocationRegionDTO locationRegionDTO;
    private SalaryDTO salaryDTO;

    private String employeeStatus;
    private String description;
}
