package com.example.thiaco.api;

import com.example.thiaco.dto.EmployeeDTO;
import com.example.thiaco.dto.HistoryEmployeeDTO;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.employee.HistoryEmployee;
import com.example.thiaco.service.employee.IEmployeeService;
import com.example.thiaco.service.employee.IHistoryEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class HistoryEmployeeAPI {

    @Autowired
    private IHistoryEmployeeService historyEmployeeService;

    @Autowired
    private IEmployeeService employeeService;

//    @GetMapping()
//    public ResponseEntity<?> getAllHistoryEmployee() {
//        List<HistoryEmployee> list = historyEmployeeService.findAll();
//        List<HistoryEmployeeDTO> dtoList = list.stream().map(historyEmployee -> historyEmployee.toHistoryEmployeeDTO()).collect(Collectors.toList());
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllHistory(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeByManv(id);
        if (!employeeOptional.isPresent()) {
            throw new DataInputException("Mã nhân viên không đúng. Xin vui lòng kiểm tra lại");
        }
        List<HistoryEmployee> list = historyEmployeeService.findHistoryEmployeesByEmployee_id(id);
        List<HistoryEmployeeDTO> dtoList = list.stream().map(historyEmployee -> historyEmployee.toHistoryEmployeeDTO()).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployees (@RequestParam("ids") String listEmployeeIDs ) {
        String[] arrStrIds = listEmployeeIDs.split(",");
        Set<String> uniqueIds = new HashSet<>();
        for (String id : arrStrIds) {
            uniqueIds.add(id);
        }

        List<Long> arrIds = new ArrayList<>();
        for (String id : uniqueIds) {
            Long employeeId = Long.parseLong(id);
            arrIds.add(employeeId);
        }

        for (Long id: arrIds) {
            Optional<HistoryEmployee> employeeOptional = historyEmployeeService.findById(id);
            if (!employeeOptional.isPresent()) {
                throw new DataInputException("Không tìm thấy nhân viên");
            }
            HistoryEmployee employee = employeeOptional.get();
            historyEmployeeService.delete(employee);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
