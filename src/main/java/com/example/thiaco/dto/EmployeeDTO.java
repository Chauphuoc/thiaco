package com.example.thiaco.dto;

import com.example.thiaco.model.salary.Salary;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Month;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeeDTO {
    private Long id;
    private Long employee_id;
    private String fullName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private int age;
    private String placeOfBirth;
    private String qualification;
    private String educationLevel;
    private String culturalLevel;
    private String homeTown;
    private String accommodation;
    private String MaritalStatus;
    private String position;
    private Date joiningday;
    private Date EmploymentContractDate;
    private int SocialInsuranceMonth;
    private String relationShip;
    private String SocialInsuranceNumber;
    private String phoneNumber;
    private String idCardNumber;
    private String citizenCardNumber;
    private Date dateOfIssue;
    private String placeOfIssue;
    private DepartmentDTO departmentDTO;
    private LocationRegionDTO locationRegionDTO;
    private SalaryDTO salaryDTO;
}
