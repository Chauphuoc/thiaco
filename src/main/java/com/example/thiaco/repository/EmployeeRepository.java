package com.example.thiaco.repository;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.dto.EmployeeResDTO;
import com.example.thiaco.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>
         {
    List<Employee> findEmployeesByDeletedIsFalse();

    List<Employee> findEmployeesByDeletedIsTrue();

    @Query(value = "SELECT NEW com.example.thiaco.dto.EmployeeResDTO" +
            "(e.id, e.employee_id, e.fullName,e.lastName, e.dateOfBirth, e.gender, " +
            " e.placeOfBirth,e.qualification, e.educationLevel, e.culturalLevel, " +
            "e.homeTown, e.maritalStatus, e.position ,e.joiningday, " +
            "e.employmentContractDate, e.socialInsuranceMonth, e.relationShip, e.socialInsuranceNumber, " +
            "e.phoneNumber, e.idCardNumber, e.citizenCardNumber, e.dateOfIssue, e.placeOfIssue, e.department, e.locationRegion, e.salary" +
            ")" +
            "FROM Employee AS e WHERE e.deleted = false ")

    Page<EmployeeResDTO> getEmployeesByDeletedIsFalse (Pageable pageable);

    Employee findEmployeeById(Long id);

    @Query(value = "SELECT e.id , e.manv, e.hovanten, e.thangdongbhxh,e.socccd," +
            "e.trinhdovanhoa,e.date_of_birth,e.ngaycap,e.trinhdohocvan,e.ngaykyhd,e.gioitinh," +
            "e.quequan,e.socmnd,e.ngaycap_cmnd, e.noicap_cmnd ,e.ngayvaocongty,e.ten,e.tinhtranghonnhan,e.dienthoai,e.noisinh," +
            "e.noicap,e.chucvu,e.trinhdochuyenmon,e.moiquanhe,e.so_sobhxh," +
            "e.created_at,e.created_by,e.update_at,e.update_by,e.deleted,e.phongban_id, e.tinhtrang, e.note " +
            "FROM Employee AS e WHERE e.manv = :employeeId ",nativeQuery = true)
    Employee findEmployeeByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT count(e.employee_id) FROM Employee AS e WHERE e.employee_id = :employeeId")
    int existsByEmployee_id(Long employeeId);
    @Query("SELECT count(e.citizenCardNumber) FROM Employee AS e WHERE e.citizenCardNumber = :cccd")
    int existsByCccd(String cccd);

    @Query("SELECT count(e.idCardNumber) FROM Employee AS e WHERE e.idCardNumber = :cmnd")
    int existsByCmnd(String cmnd);

    Employee findEmployeeByIdCardNumber(String cmnd);

    Employee findEmployeeByCitizenCardNumber(String cccd);


}
