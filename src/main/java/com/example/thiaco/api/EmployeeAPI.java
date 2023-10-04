package com.example.thiaco.api;import com.example.thiaco.dto.EmployeeDTO;import com.example.thiaco.dto.EmployeeReqDTO;import com.example.thiaco.dto.EmployeeReqUpDTO;import com.example.thiaco.exception.DataInputException;import com.example.thiaco.exception.ResourceNotFoundException;import com.example.thiaco.model.LocationRegion.LocationRegion;import com.example.thiaco.model.department.Department;import com.example.thiaco.model.employee.Employee;import com.example.thiaco.model.salary.Salary;import com.example.thiaco.service.department.IDepartmentService;import com.example.thiaco.service.employee.EmployeeService;import com.example.thiaco.service.employee.IEmployeeService;import com.example.thiaco.service.locationRegion.ILocationRegionService;import com.example.thiaco.service.salary.ISalaryService;import com.example.thiaco.utils.AppUtils;import jakarta.servlet.http.HttpServletResponse;import jakarta.validation.Valid;import lombok.AllArgsConstructor;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.*;import org.springframework.web.multipart.MultipartFile;import java.io.IOException;import java.math.BigDecimal;import java.time.LocalDate;import java.util.*;import java.util.stream.Collectors;@RestController@AllArgsConstructor@RequestMapping("/api/employees")public class EmployeeAPI {    @Autowired    private IEmployeeService employeeService;    @Autowired    private IDepartmentService departmentService;    @Autowired    private ISalaryService salaryService;    @Autowired    private ILocationRegionService locationRegionService;    @Autowired    private AppUtils appUtils;//    @GetMapping()//    public ResponseEntity<?> GetAllEmployeesDeleteFalse(Pageable pageable) {////////        Page<EmployeeResDTO> employeePage = employeeService.getEmployeesByDeletedIsFalse(pageable);////        return new ResponseEntity<>(employeePage, HttpStatus.OK);//    }    @GetMapping()    public ResponseEntity<?> getAllEmployeesDeleteFalse() {        List<Employee> employees = employeeService.findEmployeesByDeletedIsFalse();        List<EmployeeDTO> employeeDTOList = employees.stream().map(employee -> employee.toEmployeeDTO()).collect(Collectors.toList());        return new ResponseEntity<>(employeeDTOList,HttpStatus.OK);    }    @GetMapping("/{id}")    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {        if (id == null) {            throw new DataInputException("Employee does not exist");        }        Optional<Employee> employeeOptional = employeeService.findById(id);        if (!employeeOptional.isPresent()) {            return new ResponseEntity<>(null, HttpStatus.OK);        }        Employee employee = employeeOptional.get();        EmployeeDTO employeeDTO = employee.toEmployeeDTO();        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);    }    @GetMapping("/manv")    public ResponseEntity<?> findEmployeeByMNV(@RequestParam("manv") Long manv) {        Employee employee = employeeService.findEmployeeByEmployeeId(manv);        EmployeeDTO employeeDTO = employee.toEmployeeDTO();        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);    }    @PostMapping()    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeReqDTO employeeReqDTO, BindingResult bindingResult) {//        new EmployeeReqDTO().validate(employeeReqDTO, bindingResult);        if (bindingResult.hasErrors()) {            return appUtils.mapErrorToResponse(bindingResult);//            return new ResponseEntity<>("dfd",HttpStatus.BAD_REQUEST);        }        Employee employee = create(employeeReqDTO);        EmployeeDTO employeeDTO = employee.toEmployeeDTO();        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);    }    public Employee create(EmployeeReqDTO employeeReqDTO) {        Employee employee = new Employee();        Department department = new Department();        Long departmentId = employeeReqDTO.getDepartmentDTO().getId();        Optional<Department> departmentOptional = departmentService.findById(departmentId);        if (!departmentOptional.isPresent()) {            throw new DataInputException("Department is not found");        }        department = departmentOptional.get();        Long employee_id = employeeReqDTO.getEmployee_id();        String fullName = employeeReqDTO.getFullName();        String lastName = employeeReqDTO.getLastName();        //tạo mới gửi lên định dạng năm thàng ngày        LocalDate dateOfBirth = EmployeeService.convertStringToLocalDateImp(employeeReqDTO.getDateOfBirth());        int age = employeeReqDTO.getAge();        String gender = employeeReqDTO.getGender();        String placeOfBirth = employeeReqDTO.getPlaceOfBirth();        String qualification = employeeReqDTO.getQualification();        String educationLevel = employeeReqDTO.getEducationLevel();        String culturalLevel = employeeReqDTO.getCulturalLevel();        String homeTown = employeeReqDTO.getHomeTown();        String accommodation = employeeReqDTO.getAccommodation();        String maritalStatus = employeeReqDTO.getMaritalStatus();        String position = employeeReqDTO.getPosition();        LocalDate joiningDay = EmployeeService.convertStringToLocalDateImp(employeeReqDTO.getJoiningday());        LocalDate employeeeContractDay = EmployeeService.convertStringToLocalDateImp(employeeReqDTO.getEmploymentContractDate());        int socialInsuranceMonth = employeeReqDTO.getSocialInsuranceMonth();        String relationShip = employeeReqDTO.getRelationShip();        String socialInsuranceNumber = employeeReqDTO.getSocialInsuranceNumber();        String phoneNumber = employeeReqDTO.getPhoneNumber();        String idCardNumber = employeeReqDTO.getIdCardNumber();        String citizenCardNumber = employeeReqDTO.getCitizenCardNumber();        LocalDate dateOfIssue = EmployeeService.convertStringToLocalDateImp(employeeReqDTO.getDateOfIssue());        String placeOfIssue = employeeReqDTO.getPlaceOfIssue();        int checkEmployeeId = employeeService.existsByEmployee_id(employee_id);        if (checkEmployeeId==0) {            employee.setEmployee_id(employee_id);        } else {            throw new DataInputException("Duplicate");        }        employee.setFullName(fullName);        employee.setLastName(lastName);        employee.setDateOfBirth(dateOfBirth);        employee.setAge(age);        employee.setGender(gender);        employee.setPlaceOfBirth(placeOfBirth);        employee.setQualification(qualification);        employee.setEducationLevel(educationLevel);        employee.setCulturalLevel(culturalLevel);        employee.setHomeTown(homeTown);        employee.setAccommodation(accommodation);        employee.setMaritalStatus(maritalStatus);        employee.setPosition(position);        employee.setJoiningday(joiningDay);        employee.setEmploymentContractDate(employeeeContractDay);        employee.setSocialInsuranceMonth(socialInsuranceMonth);        employee.setRelationShip(relationShip);        employee.setSocialInsuranceNumber(socialInsuranceNumber);        employee.setPhoneNumber(phoneNumber);        employee.setIdCardNumber(idCardNumber);        int checkCccd = employeeService.existsByCccd(citizenCardNumber);        if (checkCccd == 0) {            employee.setCitizenCardNumber(citizenCardNumber);        } else {            throw new DataInputException("Số CCCD không được trùng");        }        employee.setDateOfIssue(dateOfIssue);        employee.setPlaceOfIssue(placeOfIssue);        employee.setDepartment(department);        // calculate salaryAmount        Salary salary = employeeReqDTO.getSalaryDTO().toSalary();        BigDecimal salaryAmount = employeeReqDTO.getSalaryDTO().getSalaryCoEfficient().multiply(employeeReqDTO.getSalaryDTO().getBasicSalary());        salary.setSalaryAmount(salaryAmount);        LocationRegion locationRegion = employeeReqDTO.getLocationRegionDTO().toLocationRegion();        employeeService.save(employee);        Long manv = employeeReqDTO.getEmployee_id();        Employee newEmployee = employeeService.findEmployeeByEmployeeId(manv);        salary.setEmployee(newEmployee);        salaryService.save(salary);        Salary newSalary = salaryService.findSalaryByEmployee(newEmployee);        locationRegion.setEmployee(newEmployee);        locationRegionService.save(locationRegion);        LocationRegion newLocationRegion = locationRegionService.findLocationRegionByEmployee(newEmployee);        newEmployee.setSalary(newSalary);        newEmployee.setLocationRegion(newLocationRegion);        return newEmployee;    }    @PatchMapping("/{id}")    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeReqUpDTO employeeReqUpDTO, BindingResult bindingResult) {        if (bindingResult.hasErrors()) {            return new AppUtils().mapErrorToResponse(bindingResult);        }        employeeReqUpDTO.setId(id);        Employee employee = employeeService.update(employeeReqUpDTO);        EmployeeDTO employeeDTO = employee.toEmployeeDTO();        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);    }    @DeleteMapping("/{id}")    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {        Optional<Employee> employeeOptional = employeeService.findById(id);        if (!employeeOptional.isPresent()) {            throw new ResourceNotFoundException("Employee is not found");        }        Employee employee = employeeOptional.get();        List<Employee> employeeList = employeeService.deleteEmployee(employee);        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee1 -> employee1.toEmployeeDTO()).collect(Collectors.toList());        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);    }    @PostMapping("/upload")    public void upload(@RequestParam("file") MultipartFile file) {        employeeService.importToDb(file);    }    @GetMapping("/export")    public void export(HttpServletResponse response) throws IOException {        employeeService.exportToExcel(response);    }    @DeleteMapping("/delete")    public ResponseEntity<?> deleteEmployees (@RequestParam("ids") String listEmployeeIDs ) {        String[] arrStrIds = listEmployeeIDs.split(",");        Set<String> uniqueIds = new HashSet<>();        for (String id : arrStrIds) {            uniqueIds.add(id);        }        List<Long> arrIds = new ArrayList<>();        for (String id : uniqueIds) {            Long employeeId = Long.parseLong(id);            arrIds.add(employeeId);        }        for (Long id: arrIds) {            Optional<Employee> employeeOptional = employeeService.findById(id);            if (!employeeOptional.isPresent()) {                throw new DataInputException("Employee is not found");            }            Employee employee = employeeOptional.get();            employee.setDeleted(true);            employeeService.save(employee);        }        List<Employee> employeeList = employeeService.findEmployeesByDeletedIsFalse();        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee -> employee.toEmployeeDTO()).collect(Collectors.toList());        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);    }}