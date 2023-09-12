package com.example.thiaco.api;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.employee.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeAPI {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping
    public ResponseEntity<?> GetAllEmployeesDeleteFalse ()
    {
        List<Employee> employees = employeeService.findEmployeesByDeletedIsFalse();
        List<EmployeeDTO> employeeDTOList = employees.stream().map(employee -> employee.toEmployeeDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
        if (id == null) {
            throw new DataInputException("Employee does not exist");
        }
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        Employee employee = employeeOptional.get();
        EmployeeDTO employeeDTO = employee.toEmployeeDTO();
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
//    @PostMapping()
//    public ResponseEntity<?> createEmployee (@RequestBody @Validated)
}

