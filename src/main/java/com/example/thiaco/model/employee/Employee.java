package com.example.thiaco.model.employee;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.service.employee.EmployeeService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@Accessors(chain = true)
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "manv", nullable = false)
    private Long employee_id;
    @Column(name = "hovanten", nullable = false)
    private String fullName;
    @Column(name = "ten", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "gioitinh", nullable = false)
    private String gender;
    @Column(name = "tuoi", nullable = true)
    private int age;
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
    @Column(name = "choo")
    private String accommodation;
    @Column(name = "tinhtranghonnhan")
    private String maritalStatus;
    @Column(name = "chucvu")
    private String position;
    @Column(name = "ngayvaocongty")
    private LocalDate joiningday;
    @Column(name = "ngaykyhd")
    private LocalDate employmentContractDate;
    @Column(name = "thangdongbhxh")
    private int SocialInsuranceMonth;
    @Column(name = "moiquanhe")
    private String relationShip;
    @Column(name = "so_sobhxh")
    private String socialInsuranceNumber;
    @Column(name = "dienthoai")
    private String phoneNumber;
    @Column(name = "socmnd",nullable = true)
    private String idCardNumber;
    @Column(name = "socccd",nullable = false,unique = true)
    private String citizenCardNumber;
    @Column(name = "ngaycap",nullable = false)
    private LocalDate dateOfIssue;
    @Column(name = "noicap",nullable = false)
    private String placeOfIssue;
    @ManyToOne
    @JoinColumn(name = "phongban_id",referencedColumnName = "id",nullable = false)
    private Department department;
    @OneToOne(mappedBy = "employee")
    private LocationRegion locationRegion;
    @OneToOne(mappedBy = "employee")
    private Salary salary;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO()
                .setId(id)
                .setEmployee_id(employee_id)
                .setFullName(fullName)
                .setLastName(lastName)
                .setDateOfBirth(EmployeeService.converLocalDateToString(dateOfBirth))
                .setAge(age)
                .setGender(gender)
                .setPlaceOfBirth(placeOfBirth)
                .setQualification(qualification)
                .setEducationLevel(educationLevel)
                .setCulturalLevel(culturalLevel)
                .setHomeTown(homeTown)
                .setAccommodation(accommodation)
                .setMaritalStatus(maritalStatus)
                .setPosition(position)
                .setJoiningday(EmployeeService.converLocalDateToString(joiningday))
                .setEmploymentContractDate(EmployeeService.converLocalDateToString(employmentContractDate))
                .setSocialInsuranceMonth(SocialInsuranceMonth)
                .setRelationShip(relationShip)
                .setSocialInsuranceNumber(socialInsuranceNumber)
                .setPhoneNumber(phoneNumber)
                .setIdCardNumber(idCardNumber)
                .setCitizenCardNumber(citizenCardNumber)
                .setDateOfIssue(EmployeeService.converLocalDateToString(dateOfIssue))
                .setPlaceOfIssue(placeOfIssue)
                .setDepartmentDTO(department.toDepartmentDTO())
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO())
                .setSalaryDTO(salary.toSalaryDTO())
                ;
    }

}
