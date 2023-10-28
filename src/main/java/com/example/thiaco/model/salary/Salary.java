package com.example.thiaco.model.salary;

import com.example.thiaco.dto.SalaryDTO;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.salary.ISalaryService;
import com.example.thiaco.service.salary.SalaryService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

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


//    @Column(name = "namTinhLuong",nullable = false)
//    private String yearOfWork;
    @Column(name = "tienLuong",precision = 10,scale = 0,nullable = false)
    private BigDecimal salaryAmount;
    @Column(name = "hsoLuongHienTai",nullable = false)
    private BigDecimal salaryCoEfficient;
    @Column(name = "thongTinKhac")
    private String otherDetails;
    @Column(name = "luongCoBan", precision = 9, scale = 0, nullable = false)
    private BigDecimal basicSalary;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id",nullable = true)
    private Employee employee;

    public BigDecimal getYearOfWork(LocalDate contractDay) {
        LocalDate current = LocalDate.now();
        Period period = Period.between(contractDay, current);
        int year = period.getYears();
        int month = period.getMonths();
        int days = period.getDays();
        BigDecimal totalYear = BigDecimal.valueOf(year).add(BigDecimal.valueOf(month).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP))
                .add(BigDecimal.valueOf(days).divide(BigDecimal.valueOf(365),2,RoundingMode.HALF_UP))
                ;
        return totalYear;
    }



    public SalaryDTO toSalaryDTO(Salary salary) {
        return new SalaryDTO()
                .setId(id)
                .setYearOfWork(salary.getYearOfWork(employee.getEmploymentContractDate()).toString())
                .setSalaryAmount(salaryAmount)
                .setSalaryCoEfficient(salaryCoEfficient)
                .setOtherDetails(otherDetails)
                .setBasicSalary(basicSalary)
                ;
    }
}
