package com.example.thiaco.model.salary;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.dto.SalaryEffDTO;
import com.example.thiaco.enums.Earea;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Year;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salaryCoEfficient")
@Accessors(chain = true)
public class SalaryCoEfficient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namHeSoLuong",nullable = false)
    private String year;
    @Column(name = "heSoLuong",nullable = false)
    private BigDecimal salaryEfficientAmount;

    //    @ManyToOne
//    @JoinColumn(name = "phongBan_id",referencedColumnName = "id",nullable = false)
//    private Department department;
    @ManyToOne
    @JoinColumn(name = "nhanvien_id",referencedColumnName = "id")
    private Employee employee;

    @Column(name = "vungluong")
//    @Enumerated(EnumType.STRING)
    private String area;

    public SalaryEffDTO toSalaryEffDTO() {
        return new SalaryEffDTO()
                .setId(id)
                .setYear(year)
                .setSalaryEfficientAmount(salaryEfficientAmount)
                .setEmployeeId(employee.getEmployee_id())
                .setName(employee.getFullName())
                .setArea(area)
                ;
    }
}
