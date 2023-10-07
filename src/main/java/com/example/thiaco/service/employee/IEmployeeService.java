package com.example.thiaco.service.employee;

import com.example.thiaco.dto.EmployeeReqUpDTO;
import com.example.thiaco.dto.EmployeeResDTO;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends IGeneralService<Employee,Long> {
    List<Employee> saveAll(List<Employee> products);
    List<Employee> findEmployeesByDeletedIsFalse( );

     Employee findEmployeeById(Long id);


    Employee findEmployeeByEmployeeId(Long employeeId);
    Employee update(Long id,EmployeeReqUpDTO employeeReqDTO);

    List<Employee> deleteEmployee(Employee employee);

    void importToDb(MultipartFile multipartfile);

    void exportToExcel(HttpServletResponse response) throws IOException;

    Page<EmployeeResDTO> getEmployeesByDeletedIsFalse(Pageable pageable);

    int existsByEmployee_id(Long employeeId);

    int existsByCccd(String cccd);

    int existsByCmnd(String cmnd);
}
