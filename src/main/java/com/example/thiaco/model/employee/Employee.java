package com.example.thiaco.model.employee;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.enums.EStatus;
import com.example.thiaco.enums.Earea;
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
import java.time.Period;
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
    @Column(name = "manv", nullable = false,unique = true)
    private Long employee_id;
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
    @ManyToOne
    @JoinColumn(name = "phongban_id",referencedColumnName = "id",nullable = true)
    private Department department;
    @OneToOne(mappedBy = "employee")
    private LocationRegion locationRegion;
    @OneToOne(mappedBy = "employee")
    private Salary salary;

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


    public int getAge() {
        LocalDate current = LocalDate.now();
        return Period.between(dateOfBirth, current).getYears();
    }

    public String getLastName(String name) {
        String  fullname = name.trim();
        int index = fullname.lastIndexOf(" ");
        if (index > -1) {
            return fullname.substring(index + 1,fullname.length());
        }
        return null;
    }

    //Ngày cấp CCCD , CMND phải đầy đủ thông tin vì sử dụng hàm convert
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return new EmployeeDTO()
                .setId(id)
                .setEmployee_id(employee_id)
                .setFullName(fullName)
                .setLastName(employee.getLastName(employee.fullName))
                .setDateOfBirth(EmployeeService.converLocalDateToString(dateOfBirth))
                .setAge(employee.getAge())
                .setGender(gender)
                .setPlaceOfBirth(placeOfBirth)
                .setQualification(qualification)
                .setEducationLevel(educationLevel)
                .setCulturalLevel(culturalLevel)
                .setHomeTown(homeTown)
                .setMaritalStatus(maritalStatus)
                .setPosition(position)
                .setJoiningday(EmployeeService.converLocalDateToString(joiningday))
                .setEmploymentContractDate(EmployeeService.converLocalDateToString(employmentContractDate))
                .setSocialInsuranceMonth(socialInsuranceMonth)
                .setRelationShip(relationShip)
                .setSocialInsuranceNumber(socialInsuranceNumber)
                .setPhoneNumber(phoneNumber)
                .setIdCardNumber(idCardNumber)

                .setDateOfIssueCmnd(dateOfIssueCmnd)
                .setPlaceOfIssueCmnd(placeOfIssueCmnd)

                .setCitizenCardNumber(citizenCardNumber)

                .setDateOfIssue(EmployeeService.converLocalDateToString(dateOfIssue))
                .setPlaceOfIssue(placeOfIssue)
                .setDepartmentDTO(department.toDepartmentDTO())
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO())
                .setSalaryDTO(salary.toSalaryDTO(salary))

                .setEmployeeStatus(employeeStatus.getValue())
                .setDescription(description)

                .setStkBank(stkBank)
                .setNameBank(nameBank)
                .setMasothue(masothue)
                ;
    }

    public HistoryEmployee toHistoryEmployee() {
        return new HistoryEmployee()
                .setEmployeeId(employee_id)
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
                .setDateOfIssueCmnd(dateOfIssueCmnd)
                .setPlaceOfIssueCmnd(placeOfIssueCmnd)
                .setDepartmentName(department.getDepartmentName())
                .setCitizenCardNumber(citizenCardNumber)
                .setDateOfIssue(dateOfIssue)
                .setPlaceOfIssue(placeOfIssue)
                .setLocationRegion(locationRegion)
                .setSalaryEff(salary.getSalaryCoEfficient())
                .setAreaSalary(salary.getBasicSalary())
                .setBasicSalary(salary.getSalaryAmount())
                .setEmployeeStatus(employeeStatus)
                .setDescription(description)
                .setStkBank(stkBank)
                .setNameBank(nameBank)
                .setMasothue(masothue)

                ;
    }

}
