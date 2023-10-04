package com.example.thiaco.repository;

import com.example.thiaco.model.user.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    List<User> findUsersByDeletedIsFalse();

    User getUserByUsername(String username);

//    @Query(value = "SELECT e.id , e.manv, e.hovanten, e.thangdongbhxh, e.choo,e.tuoi,e.socccd,e.trinhdovanhoa," +
//            "e.date_of_birth,e.ngaycap,e.trinhdohocvan,e.ngaykyhd,e.gioitinh,e.quequan,e.socmnd,e.ngayvaocongty," +
//            "e.ten,e.tinhtranghonnhan,e.dienthoai,e.noisinh,e.noicap,e.chucvu,e.trinhdochuyenmon,e.moiquanhe," +
//            "e.so_sobhxh,e.created_at,e.created_by,e.update_at,e.update_by,e.deleted,e.phongban_id FROM Employee AS e WHERE e.manv = :employeeId ",nativeQuery = true)

    Optional<User> findUserByUsername(String username);
}
