package com.example.thiaco.model.salary;

import com.example.thiaco.dto.SalaryDTO;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salary")
@Accessors(chain = true)
public class Salary extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "nhanVien_id",referencedColumnName = "id",nullable = true)
//    private Employee employee;
    @Column(name = "namTinhLuong",nullable = false)
    private int yearOfWork;
    @Column(name = "tienLuong",precision = 10,scale = 0,nullable = false)
    private BigDecimal salaryAmount;
    @Column(name = "hsoLuongHienTai",nullable = false)
    private BigDecimal salaryCoEfficient;
    @Column(name = "thongTinKhac")
    private String otherDetails;
    @Column(name = "luongCoBan", precision = 9, scale = 0, nullable = false)
    private BigDecimal basicSalary;


    public SalaryDTO toSalaryDTO() {
        return new SalaryDTO()
                .setId(id)
                .setYearOfWork(yearOfWork)
                .setSalaryAmount(salaryAmount)
                .setSalaryCoEfficient(salaryCoEfficient)
                .setOtherDetails(otherDetails)
                .setBasicSalary(basicSalary);
    }
}
