package com.example.thiaco.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqDTO   {
    private Long id;
    @NotEmpty(message = "Mã nhân viên không được để trống")
    @Size(min = 4, max = 4, message = "Mã nhân viên phải có độ dài 4 ký tự")
    private String employee_id;

    @NotEmpty(message = "Họ và tên không được bỏ trống")
    @Pattern(regexp = "^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$",message = "Họ và tên không đúng định dạng")
    private String fullName;
    @NotEmpty(message = "Ngày sinh không được để trống")
    private String dateOfBirth;
    @NotEmpty(message = "Giới tính không được để trống")
    private String gender;

    @NotEmpty(message = "Nơi sinh không được để trống")
    private String placeOfBirth;
    @NotEmpty(message = "Trình độ chuyên môn không được để trống")
    private String qualification;
    @NotEmpty(message = "Trình độ học vấn không được để trống")
    private String educationLevel;
    @NotEmpty(message = "Trình độ văn hoá không được để trống")
    private String culturalLevel;
    @NotEmpty(message = "Quê quán không được để trống")
    private String homeTown;

    @NotEmpty(message = "Tình trạng hôn nhân không được để trống")
    private String MaritalStatus;
    @NotEmpty(message = "Vị trí nhân viên không được để trống")
    private String position;
    @NotEmpty(message = "Ngày vào làm không được để trống")
    private String joiningday;
    @NotEmpty(message = "Ngày ký hợp đồng không được để trống")
    private String EmploymentContractDate;

//    @NotEmpty(message = "Tháng đóng bảo hiểm xã hội không được để trống")
    private String SocialInsuranceMonth;

//    @NotEmpty(message = "Mối quan hệ không được để trống")
    private String relationShip;
//    @NotEmpty(message = "Số sổ bảo hiểm không được để trống")
    private String SocialInsuranceNumber;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,11}$",message = "Số điện thoại phải là số gồm 10 hoặc 11 số")
    private String phoneNumber;
//    @NotEmpty(message = "Số CMND không được để trống")
//    @Pattern(regexp = "^\\d{9}$",message = "CMND phải là 9 số")
    private String idCardNumber;

//    @NotEmpty(message = "Ngày cấp CMND không được để trống")
    private String dateOfIssueCmnd;
//    @NotEmpty(message = "Nơi cấp CMND không được để trống")
    private String placeOfIssueCmnd;


    @NotEmpty(message = "CCCD không được để trống")
    @Pattern(regexp = "^\\d{12}$",message = "CCCD phải là 12 số")
    private String citizenCardNumber;
    @NotEmpty(message = "Ngày cấp CCCD không được để trống")
    private String dateOfIssue;
    @NotEmpty(message = "Nơi cấp CCCD không được để trống")
    private String placeOfIssue;
    @NotNull(message = "Phòng ban không được để trống")
    private DepartmentDTO departmentDTO;
    @NotNull(message = "Địa chỉ không được để trống")
    private LocationRegionDTO locationRegionDTO;
    @NotNull(message = "Lương không được để trống")
    private SalaryDTO salaryDTO;

    @NotEmpty(message = "Trạng thái không được để trống")
    private String employeeStatus;

    private String description;

    private String stkBank;
    private String nameBank;
    private String masothue;


}
