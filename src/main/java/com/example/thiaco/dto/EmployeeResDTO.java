package com.example.thiaco.dto;

import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.Salary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeResDTO {
    private Long id;
    private Long employee_id;
    private String fullName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private int age;
    private String placeOfBirth;
    private String qualification;
    private String educationLevel;
    private String culturalLevel;
    private String homeTown;
//    private String accommodation;
    private String maritalStatus;
    private String position;
    private LocalDate joiningday;
    private LocalDate employmentContractDate;
    private String socialInsuranceMonth;
    private String relationShip;
    private String socialInsuranceNumber;
    private String phoneNumber;
    private String idCardNumber;
    private String citizenCardNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    private DepartmentDTO departmentDTO;
    private LocationRegionDTO locationRegionDTO;
    private SalaryDTO salaryDTO;

    public EmployeeResDTO(Long id, Long employee_id, String fullName,  LocalDate dateOfBirth, String gender,
                          String placeOfBirth, String qualification, String educationLevel, String culturalLevel, String homeTown
                          , String maritalStatus, String position, LocalDate joiningday
                          , LocalDate employmentContractDate, String socialInsuranceMonth, String relationShip
                          , String socialInsuranceNumber, String phoneNumber, String idCardNumber, String citizenCardNumber, LocalDate dateOfIssue, String placeOfIssue
                          , Department department, LocationRegion locationRegion
                          , Salary salary

    ){
        this.id = id;
        this.employee_id = employee_id;
        this.fullName = fullName;
//        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.qualification = qualification;
        this.educationLevel = educationLevel;
        this.culturalLevel = culturalLevel;
        this.homeTown = homeTown;
        this.maritalStatus = maritalStatus;
        this.position = position;
        this.joiningday = joiningday;
        this.employmentContractDate = employmentContractDate;
        this.socialInsuranceMonth = socialInsuranceMonth;
        this.relationShip = relationShip;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.phoneNumber = phoneNumber;
        this.idCardNumber = idCardNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.dateOfIssue = dateOfIssue;
        this.placeOfIssue = placeOfIssue;
        this.departmentDTO = department.toDepartmentDTO();
        this.locationRegionDTO = locationRegion.toLocationRegionDTO();
        this.salaryDTO = salary.toSalaryDTO(salary);
    }
}
