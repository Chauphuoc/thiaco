package com.example.thiaco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.time.Month;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqDTO implements Validator {
    private Long id;
    private String firstName;
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
    private Month SocialInsuranceMonth;
    private String relationShip;
    private String SocialInsuranceNumber;
    private String phoneNumber;
    private DepartmentDTO departmentDTO;
    private LocationRegionDTO locationRegionDTO;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeReqDTO employeeReqDTO = (EmployeeReqDTO) target;
        String firstName = employeeReqDTO.getFirstName();
        String lastName = employeeReqDTO.getLastName();
        String phoneNumber = employeeReqDTO.getPhoneNumber();
        String address = employeeReqDTO.getLocationRegionDTO().getAddress();
        if (firstName.isEmpty()) {
            errors.reject("fullName.null", "Firstname must be not null");
        }
        if (lastName.isEmpty()) {
            errors.reject("lastName.null","Lastname must be not null ");
        }
        if (address.isEmpty()) {
            errors.reject("Address.null", "Address must not be null");
        }
        if (phoneNumber != null && phoneNumber.length() > 0) {
            if (!phoneNumber.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("phone", "phone.number", "phone must be a number");
            }
            if (phoneNumber.length() == 11) {
                errors.rejectValue("phone", "phone", "Phone number must be 10 digits");
            }

        }
    }
}
