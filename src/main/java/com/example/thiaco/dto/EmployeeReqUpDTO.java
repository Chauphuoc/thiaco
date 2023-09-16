package com.example.thiaco.dto;

import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqUpDTO {
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
    private int socialInsuranceMonth;
    private String relationShip;
    private String socialInsuranceNumber;
    private String phoneNumber;
    private String idCardNumber;
    private String citizenCardNumber;
    private String dateOfIssue;
    private String placeOfIssue;
    private DepartmentDTO departmentDTO;
    private LocationRegionDTO locationRegionDTO;
    private SalaryDTO salaryDTO;

    public Employee toEmployee() {
        return new Employee()
                .setId(id)
                .setEmployee_id(employee_id)
                .setFullName(fullName)
                .setLastName(lastName)
                .setDateOfBirth(EmployeeService.convertStringToLocalDate(dateOfBirth))
                .setGender(gender)
                .setAge(age)
                .setPlaceOfBirth(placeOfBirth)
                .setQualification(qualification)
                .setEducationLevel(educationLevel)
                .setCulturalLevel(culturalLevel)
                .setHomeTown(homeTown)
                .setAccommodation(accommodation)
                .setMaritalStatus(maritalStatus)
                .setPosition(position)
                .setJoiningday(EmployeeService.convertStringToLocalDate(joiningday))
                .setEmploymentContractDate(EmployeeService.convertStringToLocalDate(employmentContractDate))
                .setRelationShip(relationShip)
                .setSocialInsuranceNumber(socialInsuranceNumber)
                .setPhoneNumber(phoneNumber)
                .setIdCardNumber(idCardNumber)
                .setCitizenCardNumber(citizenCardNumber)
                .setDateOfIssue(EmployeeService.convertStringToLocalDate(dateOfIssue))
                .setPlaceOfIssue(placeOfIssue)
                .setDepartment(departmentDTO.toDepartment())
                .setLocationRegion(locationRegionDTO.toLocationRegion())
                .setSalary(salaryDTO.toSalary())
                ;



    }
}
