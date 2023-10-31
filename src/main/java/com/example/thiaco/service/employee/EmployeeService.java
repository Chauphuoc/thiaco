package com.example.thiaco.service.employee;

import com.example.thiaco.dto.EmployeeReqUpDTO;
import com.example.thiaco.enums.EStatus;
import com.example.thiaco.exception.DataInputException;
import com.example.thiaco.exception.ResourceNotFoundException;
import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.department.Department;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.model.salary.Salary;
import com.example.thiaco.repository.DepartmentRepository;
import com.example.thiaco.repository.EmployeeRepository;
import com.example.thiaco.repository.LocationRegionRepository;
import com.example.thiaco.repository.SalaryRepository;
import com.example.thiaco.utils.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Employee> saveAll(List<Employee> products) {
        return null;
    }

    @Override
    public List<Employee> findEmployeesByDeletedIsFalse( ) {
        return employeeRepository.findEmployeesByDeletedIsFalse();
    }

    @Override
    public List<Employee> findEmployeesByDeletedIsTrue() {
        return employeeRepository.findEmployeesByDeletedIsTrue();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Optional<Employee> findEmployeeByManv(String employeeId) {
        return employeeRepository.findEmployeeByManv(employeeId);
    }

    @Override
    public Employee findEmployeeByEmployeeId(String employeeId) {
        return employeeRepository.findEmployeeByEmployeeId(employeeId);
    }
    @Override
    public Employee update(Long id, EmployeeReqUpDTO employeeReqUpDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeReqUpDTO.getId());
        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Not found employee");
        }
        //update gửi lên định dạng ngày tháng năm
        Employee employee = optionalEmployee.get();

        Employee checkMnvEmployee = employeeRepository.findEmployeeByEmployeeId(employeeReqUpDTO.getEmployee_id());
        if (checkMnvEmployee != null && !checkMnvEmployee.getId().equals(id)) {
            throw new DataInputException("Mã nhân viên đã bị trùng. Xin vui lòng nhập lại!");
        }
        employee.setEmployee_id(employeeReqUpDTO.getEmployee_id());

        employee.setFullName(employeeReqUpDTO.getFullName());

        String dataPatternRegexddMMyyyy = "^(\\d{2})-(\\d{2})-(\\d{4})$";
        String dataPatternRegexyyyyMMdd = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        if (Pattern.matches(dataPatternRegexddMMyyyy, employeeReqUpDTO.getDateOfBirth())) {
            LocalDate dateOfBirth = convertStringToLocalDateImp(employeeReqUpDTO.getDateOfBirth());
            LocalDate current = LocalDate.now();
            if (dateOfBirth.isAfter(current)) {
                throw new DataInputException("Ngày sinh nhập không hợp lệ. Xin vui lòng nhập lại");
            } else {
                employee.setDateOfBirth(dateOfBirth);
            }
        } else {
            LocalDate dateOfBirth = convertStringToLocalDate(employeeReqUpDTO.getDateOfBirth());
            LocalDate current = LocalDate.now();
            if (dateOfBirth.isAfter(current)) {
                throw new DataInputException("Ngày sinh nhập không hợp lệ. Xin vui lòng nhập lại");
            } else {
                employee.setDateOfBirth(dateOfBirth);
            }

        }


        employee.setGender(employeeReqUpDTO.getGender());

        employee.setPlaceOfBirth(employeeReqUpDTO.getPlaceOfBirth());
        employee.setQualification(employeeReqUpDTO.getQualification());
        employee.setEducationLevel(employeeReqUpDTO.getEducationLevel());
        employee.setCulturalLevel(employeeReqUpDTO.getCulturalLevel());
        employee.setHomeTown(employeeReqUpDTO.getHomeTown());
        employee.setMaritalStatus(employeeReqUpDTO.getMaritalStatus());
        employee.setPosition(employeeReqUpDTO.getPosition());

        if (Pattern.matches(dataPatternRegexddMMyyyy, employeeReqUpDTO.getJoiningday())) {
            employee.setJoiningday(convertStringToLocalDateImp(employeeReqUpDTO.getJoiningday()));
        } else {
            employee.setJoiningday(convertStringToLocalDate(employeeReqUpDTO.getJoiningday()));
        }

        if (Pattern.matches(dataPatternRegexddMMyyyy, employeeReqUpDTO.getEmploymentContractDate())) {
            LocalDate contractDay = convertStringToLocalDateImp(employeeReqUpDTO.getEmploymentContractDate());
            LocalDate current = LocalDate.now();
            if (contractDay.isAfter(current)) {
                throw new DataInputException("Ngày ký hợp đồng không hợp lệ. Xin vui lòng nhập lại");
            } else {
                employee.setEmploymentContractDate(contractDay);
            }

        } else {
            LocalDate contractDay = convertStringToLocalDate(employeeReqUpDTO.getEmploymentContractDate());
            LocalDate current = LocalDate.now();
            if (contractDay.isAfter(current)) {
                throw new DataInputException("Ngày ký hợp đồng không hợp lệ. Xin vui lòng nhập lại");
            } else {
                employee.setEmploymentContractDate(contractDay);
            }

        }


        employee.setSocialInsuranceMonth(employeeReqUpDTO.getSocialInsuranceMonth());
        employee.setRelationShip(employeeReqUpDTO.getRelationShip());
        employee.setSocialInsuranceNumber(employeeReqUpDTO.getSocialInsuranceNumber());
        employee.setPhoneNumber(employeeReqUpDTO.getPhoneNumber());

//        Employee checkedCmndEmployee = employeeRepository.findEmployeeByIdCardNumber(employeeReqUpDTO.getIdCardNumber());
//        if (checkedCmndEmployee != null && !checkedCmndEmployee.getId().equals(id)) {
//            throw new DataInputException("Cmnd đã bị trùng. Xin vui lòng nhập lại!");
//        }

        if (employeeReqUpDTO.getDateOfIssueCmnd() == null || employeeReqUpDTO.getDateOfIssueCmnd() == "") {
            employee.setDateOfIssueCmnd(null);
        }
        else if (Pattern.matches(dataPatternRegexddMMyyyy, employeeReqUpDTO.getDateOfIssueCmnd())) {
            employee.setDateOfIssueCmnd(convertStringToLocalDateImp(employeeReqUpDTO.getDateOfIssueCmnd()));
        } else {
            employee.setDateOfIssueCmnd(convertStringToLocalDate(employeeReqUpDTO.getDateOfIssueCmnd()));
        }


        employee.setPlaceOfIssueCmnd(employeeReqUpDTO.getPlaceOfIssueCmnd());
        employee.setIdCardNumber(employeeReqUpDTO.getIdCardNumber());

        Employee checkedCccdEmployee = employeeRepository.findEmployeeByCitizenCardNumberAndDeletedIsFalse(employeeReqUpDTO.getCitizenCardNumber());
        if (checkedCccdEmployee != null && !checkedCccdEmployee.getId().equals(id)) {
            throw new DataInputException("Cccd đã bị trùng. Xin vui lòng nhập lại!");
        }
        employee.setCitizenCardNumber(employeeReqUpDTO.getCitizenCardNumber());

        if (Pattern.matches(dataPatternRegexddMMyyyy, employeeReqUpDTO.getDateOfIssue())) {
            employee.setDateOfIssue(convertStringToLocalDateImp(employeeReqUpDTO.getDateOfIssue()));
        } else {
            employee.setDateOfIssue(convertStringToLocalDate(employeeReqUpDTO.getDateOfIssue()));
        }

        employee.setPlaceOfIssue(employeeReqUpDTO.getPlaceOfIssue());

        employee.setEmployeeStatus(EStatus.getEStatus(employeeReqUpDTO.getEmployeeStatus()));
        employee.setDescription(employeeReqUpDTO.getDescription());

        employee.setStkBank(employeeReqUpDTO.getStkBank());
        employee.setNameBank(employeeReqUpDTO.getNameBank());
        employee.setMasothue(employeeReqUpDTO.getMasothue());


        Optional<Department> departmentOptional = departmentRepository.findById(employeeReqUpDTO.getDepartmentDTO().getId());
        if (!departmentOptional.isPresent()) {
            throw new ResourceNotFoundException("Không tìm thấy phòng ban");
        }
        Department curDepartment = departmentOptional.get();
        curDepartment.setDepartmentName(employeeReqUpDTO.getDepartmentDTO().getDepartment_name());
        departmentRepository.save(curDepartment);
        employee.setDepartment(curDepartment);


        Optional<LocationRegion> optionalLocationRegion = locationRegionRepository.findById(employeeReqUpDTO.getLocationRegionDTO().getId());
        if (!optionalLocationRegion.isPresent()) {
            throw new ResourceNotFoundException("Địa chỉ không được tìm thấy");
        }
        LocationRegion curLocationRegion = optionalLocationRegion.get();
        curLocationRegion.setAddress(employeeReqUpDTO.getLocationRegionDTO().getAddress());
        curLocationRegion.setProvinceId(employeeReqUpDTO.getLocationRegionDTO().getProvinceId());
        curLocationRegion.setProvinceName(employeeReqUpDTO.getLocationRegionDTO().getProvinceName());
        curLocationRegion.setDistrictId(employeeReqUpDTO.getLocationRegionDTO().getDistrictId());
        curLocationRegion.setDistrictName(employeeReqUpDTO.getLocationRegionDTO().getDistrictName());
        curLocationRegion.setWardId(employeeReqUpDTO.getLocationRegionDTO().getWardId());
        curLocationRegion.setWardName(employeeReqUpDTO.getLocationRegionDTO().getWardName());
        locationRegionRepository.save(curLocationRegion);
        employee.setLocationRegion(curLocationRegion);

        Optional<Salary> optionalSalary = salaryRepository.findById(employeeReqUpDTO.getSalaryDTO().getId());
        if (!optionalLocationRegion.isPresent()) {
            throw new ResourceNotFoundException("Lương không được tìm thấy");
        }
        Salary curSalary = optionalSalary.get();
        curSalary.setBasicSalary(employeeReqUpDTO.getSalaryDTO().getBasicSalary());
        curSalary.setSalaryCoEfficient(employeeReqUpDTO.getSalaryDTO().getSalaryCoEfficient());
        curSalary.setOtherDetails(employeeReqUpDTO.getSalaryDTO().getOtherDetails());
        BigDecimal salaryAmount = employeeReqUpDTO.getSalaryDTO().getSalaryCoEfficient().multiply(employeeReqUpDTO.getSalaryDTO().getBasicSalary());
        curSalary.setSalaryAmount(salaryAmount);
        salaryRepository.save(curSalary);

        employee.setSalary(curSalary);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public List<Employee> deleteEmployee(Employee employee) {
        employee.setDeleted(true);
        employeeRepository.save(employee);
        List<Employee> employeeList = employeeRepository.findEmployeesByDeletedIsFalse();
        return employeeList;
    }



    @Override
    public void importToDb(MultipartFile multipartfile) {
        if (!multipartfile.isEmpty()) {
            List<Employee> employees = new ArrayList<>();
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(multipartfile.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0); rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (rowIndex == 0) {
                        continue;
                    }
                    //Lấy dữ liệu
                    String employee_id = String.valueOf(getValue(row.getCell(0)));
                    String fullName = String.valueOf(getValue(row.getCell(1)));
                    String dateOfBirth = String.valueOf(getValue(row.getCell(2)));
                    String gender = String.valueOf(getValue(row.getCell(3)));
                    String placeOfBirth = String.valueOf(row.getCell(4));
                    String qualification = String.valueOf(row.getCell(5));
                    String educationLevel = String.valueOf(row.getCell(6));
                    String culturalLevel = String.valueOf(row.getCell(7));
                    String homeTown =String.valueOf(row.getCell(8));
                    String maritalStatus = String.valueOf(row.getCell(9));
                    String position = String.valueOf(row.getCell(10));
                    String joiningday = String.valueOf(row.getCell(11));
                    String employmentContractDate= String.valueOf(row.getCell(12));
                    String  socialInsuranceMonth = String.valueOf(row.getCell(13)) ;
                    String relationShip = String.valueOf(row.getCell(14));
                    String socialInsuranceNumber= String.valueOf(row.getCell(15));
                    String phoneNumber= String.valueOf(row.getCell(16));
                    String idCardNumber= String.valueOf(row.getCell(17));
                    String citizenCardNumber= String.valueOf(row.getCell(18));
                    String dateOfIssue= String.valueOf(row.getCell(19));
                    String placeOfIssue= String.valueOf(row.getCell(20));
                    int department_id = (int) Double.parseDouble(String.valueOf(row.getCell(21)));
                    String address = String.valueOf(row.getCell(22));
                    String ward = String.valueOf(row.getCell(23));
                    String district = String.valueOf(row.getCell(24));
                    String province = String.valueOf(row.getCell(25));
                    String base_salary = String.valueOf(row.getCell(26));
                    String ef_salary = String.valueOf(row.getCell(27));
                    String salaryAmount = String.valueOf(row.getCell(28));

                    String dayOfIssueCmnd = String.valueOf(row.getCell(29));
                    String placeOfIssueCmnd = String.valueOf(row.getCell(30));
                    String status = String.valueOf(row.getCell(31));
                    String description = String.valueOf(row.getCell(32));

                    String stkBank = String.valueOf(row.getCell(33));
                    String nameBank = String.valueOf(row.getCell(34));
                    String masothue = String.valueOf(row.getCell(35));

                    String idWard = String.valueOf(row.getCell(36));
                    String idDistrict = String.valueOf(row.getCell(37));
                    String idProvince = String.valueOf(row.getCell(38));

                    Optional<Department> optionalDepartment = departmentRepository.findById((long) department_id);
                    Department department = optionalDepartment.get();
                    Employee employee = new Employee();
                    employee.setEmployee_id(employee_id);
                    employee.setFullName(fullName);
                    employee.setDateOfBirth(EmployeeService.convertStringToLocalDateImp(dateOfBirth));
                    employee.setGender(gender);
                    employee.setPlaceOfBirth(placeOfBirth);
                    employee.setQualification(qualification);
                    employee.setEducationLevel(educationLevel);
                    employee.setCulturalLevel(culturalLevel);

                    employee.setHomeTown(homeTown);

                    employee.setMaritalStatus(maritalStatus);
                    employee.setPosition(position);
                    employee.setJoiningday(EmployeeService.convertStringToLocalDateImp(joiningday));
                    employee.setEmploymentContractDate(EmployeeService.convertStringToLocalDateImp(employmentContractDate));

                    employee.setSocialInsuranceMonth(socialInsuranceMonth);
                    employee.setRelationShip(relationShip);
                    employee.setSocialInsuranceNumber(socialInsuranceNumber);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setIdCardNumber(idCardNumber);

                    if (dayOfIssueCmnd == "" || dayOfIssueCmnd == null || dayOfIssueCmnd.isEmpty()) {
                        employee.setDateOfIssueCmnd(null);
                    } else {
                        employee.setDateOfIssueCmnd(EmployeeService.convertStringToLocalDateImp(dayOfIssueCmnd));
                    }

                    employee.setPlaceOfIssueCmnd(placeOfIssueCmnd);
                    employee.setCitizenCardNumber(citizenCardNumber);
                    employee.setDateOfIssue(EmployeeService.convertStringToLocalDateImp(dateOfIssue));
                    employee.setPlaceOfIssue(placeOfIssue);
                    employee.setEmployeeStatus(EStatus.getEStatus(status));
                    employee.setDescription(description);

                    employee.setStkBank(stkBank);
                    employee.setNameBank(nameBank);
                    employee.setMasothue(masothue);

                    employee.setDepartment(department);
                    LocationRegion locationRegion = new LocationRegion();
                    locationRegion.setAddress(address);
                    locationRegion.setWardId(idWard);
                    locationRegion.setWardName(ward);
                    locationRegion.setDistrictId(idDistrict);
                    locationRegion.setDistrictName(district);
                    locationRegion.setProvinceId(idProvince);
                    locationRegion.setProvinceName(province);
                    employee.setLocationRegion(locationRegion);
                    locationRegion.setEmployee(employee);
                    locationRegionRepository.save(locationRegion);

                    Salary salary = new Salary();
                    salary.setBasicSalary(BigDecimal.valueOf(Double.valueOf(base_salary)));
                    salary.setSalaryCoEfficient(BigDecimal.valueOf(Double.valueOf(ef_salary)));
                    salary.setSalaryAmount(BigDecimal.valueOf(Double.valueOf(salaryAmount)) );
                    salary.setEmployee(employee);
                    salaryRepository.save(salary);

                    employee.setSalary(salary);

                    employees.add(employee);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!employees.isEmpty()) {
                employeeRepository.saveAll(employees);
            }
        }
    }


//  Đếm kể cả thanh tiêu đề
    public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
        int numOfNonEmptyCells = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    numOfNonEmptyCells++;
                }
            }
        }
        return numOfNonEmptyCells;
    }
    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case _NONE:
                return null;
            default:
                break;
        }
        return null;
    }
    public static LocalDate convertStringToLocalDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, formatter);
    }

    public static LocalDate convertStringToLocalDateImp(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(str, formatter);
    }

    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public static String checkConvertLocalDateToString(LocalDate date) {
        if (date == null) {
            return "";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return date.format(formatter);
        }
    }


    @Override
    public void exportToExcel(HttpServletResponse response) throws  IOException {
        //setting thời gian tạo ngày tháng tên file,... cho file excel
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Employee> employees = employeeRepository.findEmployeesByDeletedIsFalse();
        ExcelGenerator generator = new ExcelGenerator(employees);
        generator.generateExcelFile(response);
    }


//    @Override
//    public Page<EmployeeResDTO> getEmployeesByDeletedIsFalse(Pageable pageable) {
//
//        Page<EmployeeResDTO> employees = employeeRepository.getEmployeesByDeletedIsFalse(pageable);
//        return employees;
//    }

    @Override
    public int existsByEmployee_id(String employeeId) {
        return employeeRepository.existsByEmployee_id(employeeId);
    }

    @Override
    public int existsByCccd(String citizenCardNumber) {
        return employeeRepository.existsByCccd(citizenCardNumber);
    }

    @Override
    public int existsByCmnd(String cmnd) {
        return employeeRepository.existsByCmnd(cmnd);
    }

    @Override
    public Employee findEmployeeByCitizenCardNumber(String cccd) {
        return employeeRepository.findEmployeeByCitizenCardNumber(cccd);
    }

    @Override
    public Employee findEmployeeByCitizenCardNumberAndDeletedIsFalse(String cccd) {
        return employeeRepository.findEmployeeByCitizenCardNumberAndDeletedIsFalse(cccd);
    }


}
