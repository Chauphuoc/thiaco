package com.example.thiaco.api;

import com.example.thiaco.dto.DepartmentDTO;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.service.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentAPI {
    @Autowired
    private IDepartmentService departmentService;
    @GetMapping("/getall")
    public ResponseEntity<?> getAllDepartment() {
        List<Department> departments = departmentService.findAllByDeletedIsFalse();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(department -> department.toDepartmentDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOS,HttpStatus.OK);
    }
}
