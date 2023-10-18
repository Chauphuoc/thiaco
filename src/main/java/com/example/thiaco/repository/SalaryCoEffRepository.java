package com.example.thiaco.repository;

import com.example.thiaco.model.salary.SalaryCoEfficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryCoEffRepository extends JpaRepository<SalaryCoEfficient,Long> {

    @Query(value = "SELECT eff.id, eff.created_at, eff.created_by,eff.deleted , eff.update_at,eff.update_by,eff.he_so_luong, " +
            "eff.nam_he_so_luong, eff.vungluong,eff.nhanvien_id " +
            "FROM salary_co_efficient as eff INNER JOIN employee as e on eff.nhanvien_id = e.id where e.deleted = 0",nativeQuery = true)
    List<SalaryCoEfficient> getSalaryCoEfficients();

}
