package com.example.thiaco.repository;

import com.example.thiaco.dto.EmployeeReqDTO;
import com.example.thiaco.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findEmployeesByDeletedIsFalse();

    Employee findEmployeeById(Long id);

    @Query(value = "SELECT e.id , e.manv, e.hovanten, e.thangdongbhxh, e.choo,e.tuoi,e.socccd,e.trinhdovanhoa,e.date_of_birth,e.ngaycap,e.trinhdohocvan,e.ngaykyhd,e.gioitinh,e.quequan,e.socmnd,e.ngayvaocongty,e.ten,e.tinhtranghonnhan,e.dienthoai,e.noisinh,e.noicap,e.chucvu,e.trinhdochuyenmon,e.moiquanhe,e.so_sobhxh,e.created_at,e.created_by,e.update_at,e.update_by,e.deleted,e.phongban_id,e.bangluong_id FROM Employee AS e WHERE e.manv = :employeeId ",nativeQuery = true)
    Employee findEmployeeByEmployeeId(@Param("employeeId") Long employeeId);

}
