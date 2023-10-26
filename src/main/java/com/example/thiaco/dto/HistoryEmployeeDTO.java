package com.example.thiaco.dto;

import com.example.thiaco.enums.EStatus;
import com.example.thiaco.enums.Earea;
import com.example.thiaco.model.LocationRegion.LocationRegion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HistoryEmployeeDTO {

    private Long id;
    private String employee_id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String placeOfBirth;
    private String qualification;
    private String educationLevel;
    private String culturalLevel;
    private String homeTown;
    private String maritalStatus;
    private String position;
    private LocalDate joiningday;
    private LocalDate employmentContractDate;
    private String socialInsuranceMonth;
    private String relationShip;
    private String socialInsuranceNumber;
    private String phoneNumber;
    private String idCardNumber;

    private LocalDate dateOfIssueCmnd;
    private String placeOfIssueCmnd;

    private String citizenCardNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;

    private String departmentName;

    private LocationRegionDTO locationRegionDTO;
    private BigDecimal salaryEff;

    private BigDecimal areaSalary;

    private BigDecimal basicSalary;

    private String employeeStatus;

    private String description;

    private String stkBank;

    private String nameBank;

    private String masothue;



    private LocalDate endDay;
}
