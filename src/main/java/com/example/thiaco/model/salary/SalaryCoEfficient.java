package com.example.thiaco.model.salary;

import com.example.thiaco.model.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Year;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salaryCoEfficient")
public class SalaryCoEfficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namHeSoLuong",nullable = false)
    private Year year;
    @Column(name = "heSoLuong",nullable = false)
    private BigDecimal salaryEfficientAmount;
    @OneToOne
    @JoinColumn(name = "phongBan_id",referencedColumnName = "id",nullable = false)
    private Department department;
}
