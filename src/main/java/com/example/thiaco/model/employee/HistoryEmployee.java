package com.example.thiaco.model.employee;

import com.example.thiaco.dto.HistoryEmployeeDTO;
import com.example.thiaco.enums.EStatus;
import com.example.thiaco.enums.Earea;
import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.Salary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lichsu_nhanvien")
@Accessors(chain = true)
public class HistoryEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "manv", nullable = false)
    private String employeeId;
    @Column(name = "hovanten", nullable = false)
    private String fullName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "gioitinh", nullable = false)
    private String gender;
    @Column(name = "noisinh",nullable = true)
    private String placeOfBirth;
    @Column(name = "trinhdochuyenmon")
    private String qualification;
    @Column(name = "trinhdohocvan")
    private String educationLevel;
    @Column(name = "trinhdovanhoa")
    private String culturalLevel;
    @Column(name = "quequan")
    private String homeTown;
    @Column(name = "tinhtranghonnhan")
    private String maritalStatus;
    @Column(name = "chucvu")
    private String position;
    @Column(name = "ngayvaocongty")
    private LocalDate joiningday;
    @Column(name = "ngaykyhd")
    private LocalDate employmentContractDate;
    @Column(name = "thangdongbhxh")
    private String socialInsuranceMonth;
    @Column(name = "moiquanhe")
    private String relationShip;
    @Column(name = "so_sobhxh")
    private String socialInsuranceNumber;
    @Column(name = "dienthoai")
    private String phoneNumber;
    @Column(name = "socmnd",nullable = true)
    private String idCardNumber;

    @Column(name = "ngaycap_cmnd", nullable = true)
    private LocalDate dateOfIssueCmnd;

    @Column(name = "noicap_cmnd", nullable = true)
    private String placeOfIssueCmnd;

    @Column(name = "socccd",nullable = true)
    private String citizenCardNumber;
    @Column(name = "ngaycap",nullable = true)
    private LocalDate dateOfIssue;
    @Column(name = "noicap",nullable = true)
    private String placeOfIssue;

    @Column(name = "tenbophan")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "location_id",referencedColumnName = "id")
    private LocationRegion locationRegion;

    @Column(name = "hesoluong")
    private BigDecimal salaryEff;

    @Column(name = "luongvung")
    private BigDecimal areaSalary;

    @Column(name = "luongcoban")
    private BigDecimal basicSalary;

    @Column(name = "tinhtrang", nullable = true)
    @Enumerated(EnumType.STRING)
    private EStatus employeeStatus;

    @Column(name = "note", nullable = true)
    private String description;

    @Column(name = "stk_nganhang")
    private String stkBank;

    @Column(name = "ten_nganhang")
    private String nameBank;

    @Column(name = "masothue")
    private String masothue;




    @Column(name = "ngayketthuc")
    private LocalDate endDay;

    public HistoryEmployeeDTO toHistoryEmployeeDTO() {
        return new HistoryEmployeeDTO()
                .setId(id)
                .setEmployee_id(employeeId)
                .setFullName(fullName)
                .setDateOfBirth(dateOfBirth)
                .setGender(gender)
                .setPlaceOfBirth(placeOfBirth)
                .setQualification(qualification)
                .setEducationLevel(educationLevel)
                .setCulturalLevel(culturalLevel)
                .setHomeTown(homeTown)
                .setMaritalStatus(maritalStatus)
                .setPosition(position)
                .setJoiningday(joiningday)
                .setEmploymentContractDate(employmentContractDate)
                .setSocialInsuranceMonth(socialInsuranceMonth)
                .setRelationShip(relationShip)
                .setSocialInsuranceNumber(socialInsuranceNumber)
                .setPhoneNumber(phoneNumber)
                .setIdCardNumber(idCardNumber)
                .setDateOfIssue(dateOfIssueCmnd)
                .setPlaceOfIssueCmnd(placeOfIssueCmnd)
                .setCitizenCardNumber(citizenCardNumber)
                .setDateOfIssue(dateOfIssue)
                .setPlaceOfIssue(placeOfIssue)
                .setDepartmentName(departmentName)
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO())
                .setSalaryEff(salaryEff)
                .setAreaSalary(areaSalary)
                .setBasicSalary(basicSalary)
                .setEmployeeStatus(employeeStatus.getValue())
                .setDescription(description)
                .setStkBank(stkBank)
                .setNameBank(nameBank)
                .setMasothue(masothue)
                .setEndDay(endDay)
                ;
    }
}
