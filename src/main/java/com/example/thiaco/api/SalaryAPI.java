package com.example.thiaco.api;

import com.example.thiaco.dto.SalaryEffDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.SalaryCoEfficient;
import com.example.thiaco.service.department.IDepartmentService;
import com.example.thiaco.service.salary.ISalaryEffService;
import com.example.thiaco.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salary")
public class SalaryAPI {
    @Autowired
    private ISalaryEffService salaryEffService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private AppUtils appUtils;

    @GetMapping("/eff")
    public ResponseEntity<?> getAllSalaryEff() {
        List<SalaryCoEfficient> salaryCoEfficientList = salaryEffService.getSalaryCoEfficientsByDeletedIsFalse();
        List<SalaryEffDTO> salaryEffDTOList = salaryCoEfficientList.stream().map(salaryCoEfficient -> salaryCoEfficient.toSalaryEffDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(salaryEffDTOList, HttpStatus.OK);
    }

    @PostMapping("/eff")
    public ResponseEntity<?> createSalaryEff(@RequestBody SalaryEffDTO salaryEffDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        SalaryCoEfficient salaryCoEfficient = salaryEffService.create(salaryEffDTO);
        return new ResponseEntity<>(salaryCoEfficient.toSalaryEffDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSalaryEffs(@RequestParam String salaryEffIds) {
        String[] arrStringIds = salaryEffIds.split(",");
        Set<String> uniqueIds = new HashSet<>();
        for (String id : arrStringIds) {
            uniqueIds.add(id);
        }
        List<Long> arrIds = new ArrayList<>();
        for (String item : uniqueIds) {
            Long salaryEffId = Long.parseLong(item);
            arrIds.add(salaryEffId);
        }
        for (Long id: arrIds) {
            Optional<SalaryCoEfficient> salaryCoEfficientOptional = salaryEffService.findById(id);
            if (!salaryCoEfficientOptional.isPresent()) {
                throw new DataInputException("SalaryCoEff không tìm thấy");
            }
            SalaryCoEfficient salaryCoEfficient = salaryCoEfficientOptional.get();
            salaryCoEfficient.setDeleted(true);
            salaryEffService.save(salaryCoEfficient);
        }
        List<SalaryCoEfficient> list = salaryEffService.getSalaryCoEfficientsByDeletedIsFalse();
        List<SalaryEffDTO> dtoList = list.stream().map(salaryCoEfficient -> salaryCoEfficient.toSalaryEffDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
