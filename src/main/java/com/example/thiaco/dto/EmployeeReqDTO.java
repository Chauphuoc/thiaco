package com.example.thiaco.dto;

import jakarta.validation.constraints.*;
import lombok.*;
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
public class EmployeeReqDTO   {
    private Long id;
    @NotNull(message = "Mã nhân viên không được để trống")
    @Min(value = 1000,message = "Mã nhân viên phải lớn hơn hoặc bằng 1000")
    @Max(value = 9999,message = "Mã nhân viên phải bé hơn hoặc bằng 9999")
    private Long employee_id;

    @NotEmpty(message = "Họ và tên không được bỏ trống")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+",message = "Họ và tên không đúng định dạng")
    private String fullName;
    @NotEmpty(message = "Tên không được bỏ trống")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+",message = "Tên không đúng định dạng")
    private String lastName;
    @NotNull(message = "Ngày sinh không được để trống")
    private Date dateOfBirth;
    @NotEmpty(message = "Giới tính không được để trống")
    private String gender;
    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 1, message = "Tuổi phải lớn hơn hoặc bằng 1")
    @Max(value = 100, message = "Tuổi phải nhỏ hơn hoặc bằng 100")
    private int age;
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
    @NotEmpty(message = "Chỗ ở không được để trống")
    private String accommodation;

    private String MaritalStatus;
    @NotEmpty(message = "Vị trí nhân viên không được để trống")
    private String position;
    @NotNull(message = "Ngày vào làm không được để trống")
    private Date joiningday;
    @NotNull(message = "Ngày ký hợp đồng không được để trống")
    private Date EmploymentContractDate;
    private int SocialInsuranceMonth;
    private String relationShip;
    private String SocialInsuranceNumber;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^$|[0-9]*$",message = "Số điện thoại phải là số")
    private String phoneNumber;

    private String idCardNumber;
    @NotEmpty(message = "CCCD không được để trống")
    @Pattern(regexp = "^d{9}$",message = "CCCD phải là 9 số")
    private String citizenCardNumber;
    @NotNull(message = "Ngày cấp CCCD không được để trống")
    private Date dateOfIssue;
    @NotEmpty(message = "Nơi cấp CCCD không được để trống")
    private String placeOfIssue;

    private DepartmentDTO departmentDTO;
    @NotNull(message = "Địa chỉ không được để trống")
    private LocationRegionDTO locationRegionDTO;
    @NotNull(message = "Lương không được để trống")
    private SalaryDTO salaryDTO;

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return EmployeeReqDTO.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        EmployeeReqDTO employeeReqDTO = (EmployeeReqDTO) target;
//        String firstName = employeeReqDTO.getFullName();
//        String lastName = employeeReqDTO.getLastName();
//        String phoneNumber = employeeReqDTO.getPhoneNumber();
//        String address = employeeReqDTO.getLocationRegionDTO().getAddress();
//        Long employeeId = employeeReqDTO.getEmployee_id();
//        String cccd = employeeReqDTO.getCitizenCardNumber();
//        // co the dung @Notnull
//        if (firstName.isEmpty()) {
//            errors.reject("fullName.null", "Firstname must be not null");
//        }
//        if (lastName.isEmpty()) {
//            errors.reject("lastName.null","Lastname must be not null ");
//        }
//        if (address.isEmpty()) {
//            errors.reject("address.null", "Address must not be null");
//        }
//        if (phoneNumber != null && phoneNumber.length() > 0) {
//            if (!phoneNumber.matches("(^$|[0-9]*$)")) {
//                errors.rejectValue("phone", "phone.number", "phone must be a number");
//            }
//            if (phoneNumber.length() == 11) {
//                errors.rejectValue("phone", "phone", "Phone number must be 10 digits");
//            }
//        }
//    }
}
