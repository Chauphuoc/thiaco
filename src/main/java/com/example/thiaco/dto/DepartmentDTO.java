package com.example.thiaco.dto;

import com.example.thiaco.model.department.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DepartmentDTO {
    private Long id;
    private String department_name;

    public Department toDepartment() {
        return new Department()
                .setId(id)
                .setDepartmentName(department_name)
                ;
    }
}
