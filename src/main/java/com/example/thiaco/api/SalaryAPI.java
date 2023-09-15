package com.example.thiaco.api;

import com.example.thiaco.dto.SalaryEffDTO;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.service.department.IDepartmentService;
import com.example.thiaco.service.salary.ISalaryEffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salary")
public class SalaryAPI {
    @Autowired
    private ISalaryEffService salaryEffService;
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/eff")
    public ResponseEntity<?> getAllSalaryEff() {
        List<SalaryCoEfficient> salaryCoEfficientList = salaryEffService.getSalaryCoEfficientsByDeletedIsFalse();
        List<SalaryEffDTO> salaryEffDTOList = salaryCoEfficientList.stream().map(salaryCoEfficient -> salaryCoEfficient.toSalaryEffDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(salaryEffDTOList,HttpStatus.OK);
    }



}
