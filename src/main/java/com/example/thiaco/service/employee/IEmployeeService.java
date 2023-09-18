package com.example.thiaco.service.employee;

import com.example.thiaco.dto.EmployeeReqDTO;
import com.example.thiaco.dto.EmployeeReqUpDTO;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Map;

public interface IEmployeeService extends IGeneralService<Employee,Long> {
    List<Employee> saveAll(List<Employee> products);
    List<Employee> findEmployeesByDeletedIsFalse();

     Employee findEmployeeById(Long id);


    Employee findEmployeeByEmployeeId(Long employeeId);
    Employee update(EmployeeReqUpDTO employeeReqDTO);

    List<Employee> deleteEmployee(Employee employee);

    void importToDb(MultipartFile multipartfile);

    StreamingResponseBody exportToExcel(HttpServletResponse response);

}
