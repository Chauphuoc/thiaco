package com.example.thiaco.model.employee;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.model.BaseEntity;
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
    @Column(name = "MaNV", nullable = false)
    private Long employee_id;
    @Column(name = "hoVaTen", nullable = false)
    private String fullName;
    @Column(name = "ten", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "gioitinh", nullable = false)
    private String gender;
    @Column(name = "tuoi", nullable = true)
    private int age;
    @Column(name = "noisinh",nullable = true)
    private String placeOfBirth;
    @Column(name = "trinhDoChuyenMon")
    private String qualification;
    @Column(name = "trinhDoHocVan")
    private String educationLevel;
    @Column(name = "trinhDoVanHoa")
    private String culturalLevel;
    @Column(name = "quequan")
    private String homeTown;
    @Column(name = "ChoO")
    private String accommodation;
    @Column(name = "tinhTrangHonNhan")
    private String maritalStatus;
    @Column(name = "chucVu")
    private String position;
    @Column(name = "ngayVaoCongTy")
    private Date joiningday;
    @Column(name = "ngayKyHD")
    private Date employmentContractDate;
    @Column(name = "thangDongBHXH")
    private int SocialInsuranceMonth;
    @Column(name = "moiQuanHe")
    private String relationShip;
    @Column(name = "soSoBHXH")
    private String socialInsuranceNumber;
    @Column(name = "soDT")
    private String phoneNumber;
    @Column(name = "soCMND",nullable = true)
    private String idCardNumber;
    @Column(name = "soCCCD",nullable = false)
    private String citizenCardNumber;
    @Column(name = "ngayCap",nullable = false)
    private Date dateOfIssue;
    @Column(name = "noiCap",nullable = false)
    private String placeOfIssue;
    @OneToOne
    @JoinColumn(name = "phongBan_id",referencedColumnName = "id",nullable = false)
    private Department department;
    @OneToOne(mappedBy = "employee")
    private LocationRegion locationRegion;
    @OneToOne
    @JoinColumn(name = "bangluong_id",referencedColumnName = "id",nullable = false)
    private Salary salary;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO()
                .setId(id)
                .setEmployee_id(employee_id)
                .setFullName(fullName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
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
                .setJoiningday(joiningday)
                .setEmploymentContractDate(employmentContractDate)
                .setSocialInsuranceMonth(SocialInsuranceMonth)
                .setRelationShip(relationShip)
                .setSocialInsuranceNumber(socialInsuranceNumber)
                .setPhoneNumber(phoneNumber)
                .setIdCardNumber(idCardNumber)
                .setCitizenCardNumber(citizenCardNumber)
                .setDateOfIssue(dateOfIssue)
                .setPlaceOfIssue(placeOfIssue)
                .setDepartmentDTO(department.toDepartmentDTO())
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO())
                .setSalaryDTO(salary.toSalaryDTO())
                ;
    }

}
