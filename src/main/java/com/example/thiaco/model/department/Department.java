package com.example.thiaco.model.department;

import com.example.thiaco.dto.DepartmentDTO;
import com.example.thiaco.model.BaseEntity;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@Accessors(chain = true)
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenPhongban", nullable = false)
    private String departmentName;



    public DepartmentDTO toDepartmentDTO() {
        return new DepartmentDTO()
                .setId(id)
                .setDepartment_name(departmentName)
                ;
    }

}
