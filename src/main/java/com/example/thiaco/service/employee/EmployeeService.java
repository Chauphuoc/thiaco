package com.example.thiaco.service.employee;

import com.example.thiaco.dto.*;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public List<Employee> findEmployeesByDeletedIsFalse() {
        return employeeRepository.findEmployeesByDeletedIsFalse();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Employee findEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findEmployeeByEmployeeId(employeeId);
    }
    @Override
    public Employee update(EmployeeReqUpDTO employeeReqUpDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeReqUpDTO.getId());
        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Not found employee");
        }
        Employee employee = optionalEmployee.get();
        employee.setEmployee_id(employeeReqUpDTO.getEmployee_id());
        employee.setFullName(employeeReqUpDTO.getFullName());
        employee.setLastName(employeeReqUpDTO.getLastName());
        employee.setDateOfBirth(convertStringToLocalDate(employeeReqUpDTO.getDateOfBirth()));
        employee.setGender(employeeReqUpDTO.getGender());
        employee.setAge(employeeReqUpDTO.getAge());
        employee.setPlaceOfBirth(employeeReqUpDTO.getPlaceOfBirth());
        employee.setQualification(employeeReqUpDTO.getQualification());
        employee.setEducationLevel(employeeReqUpDTO.getEducationLevel());
        employee.setCulturalLevel(employeeReqUpDTO.getCulturalLevel());
        employee.setHomeTown(employeeReqUpDTO.getHomeTown());
        employee.setAccommodation(employeeReqUpDTO.getAccommodation());
        employee.setMaritalStatus(employeeReqUpDTO.getMaritalStatus());
        employee.setPosition(employeeReqUpDTO.getPosition());
        employee.setJoiningday(convertStringToLocalDate(employeeReqUpDTO.getJoiningday())  );
        employee.setEmploymentContractDate(convertStringToLocalDate(employeeReqUpDTO.getEmploymentContractDate()) );
        employee.setSocialInsuranceNumber(employeeReqUpDTO.getSocialInsuranceNumber());
        employee.setPhoneNumber(employeeReqUpDTO.getPhoneNumber());
        employee.setIdCardNumber(employeeReqUpDTO.getIdCardNumber());
        employee.setCitizenCardNumber(employeeReqUpDTO.getCitizenCardNumber());
        employee.setDateOfIssue(convertStringToLocalDate(employeeReqUpDTO.getDateOfIssue()));
        employee.setPlaceOfIssue(employeeReqUpDTO.getPlaceOfIssue());


        Optional<Department> departmentOptional = departmentRepository.findById(employeeReqUpDTO.getDepartmentDTO().getId());
        if (!departmentOptional.isPresent()) {
            throw new ResourceNotFoundException("Department is not found");
        }
        Department curDepartment = departmentOptional.get();
        curDepartment.setDepartmentName(employeeReqUpDTO.getDepartmentDTO().getDepartment_name());
        departmentRepository.save(curDepartment);
        employee.setDepartment(curDepartment);


        Optional<LocationRegion> optionalLocationRegion = locationRegionRepository.findById(employeeReqUpDTO.getLocationRegionDTO().getId());
        if (!optionalLocationRegion.isPresent()) {
            throw new ResourceNotFoundException("Address is not found");
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
            throw new ResourceNotFoundException("Salary is not found");
        }
        Salary curSalary = optionalSalary.get();
        curSalary.setYearOfWork(employeeReqUpDTO.getSalaryDTO().getYearOfWork());
        curSalary.setBasicSalary(employeeReqUpDTO.getSalaryDTO().getBasicSalary());
        curSalary.setSalaryCoEfficient(employeeReqUpDTO.getSalaryDTO().getSalaryCoEfficient());
        curSalary.setOtherDetails(employeeReqUpDTO.getSalaryDTO().getOtherDetails());
        curSalary.setSalaryAmount(employeeReqUpDTO.getSalaryDTO().getSalaryAmount());
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

    public static Date getDateByString(String dateString){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Parse the input date string into a Date object
            Date date = inputDateFormat.parse(dateString);

            // Format the Date object back into a string
            String formattedDate = outputDateFormat.format(date);

            System.out.println("Original date string: " + dateString);
            System.out.println("Parsed Date: " + date);
            System.out.println("Formatted date string: " + formattedDate);

            return date;
        } catch (ParseException e) {
            System.out.println("Error occurred while parsing the date: " + e.getMessage());
            return null;
        }

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
                    Long employee_id = Long.parseLong(String.valueOf(getValue(row.getCell(0))));
                    String fullName = String.valueOf(getValue(row.getCell(1)));
                    String lastName = String.valueOf(getValue(row.getCell(2)));
                    String dateOfBirth = String.valueOf(getValue(row.getCell(3)));
                    String gender = String.valueOf(getValue(row.getCell(4)));
                    int age =  (int) Double.parseDouble(String.valueOf(row.getCell(5)));
                    String placeOfBirth = String.valueOf(row.getCell(6));
                    String qualification = String.valueOf(row.getCell(7));
                    String educationLevel = String.valueOf(row.getCell(8));
                    String culturalLevel = String.valueOf(row.getCell(9));
                    String homeTown =String.valueOf(row.getCell(10));
                    String accommodation =String.valueOf(row.getCell(11));
                    String maritalStatus = String.valueOf(row.getCell(12));
                    String position = String.valueOf(row.getCell(13));
                    String joiningday = String.valueOf(row.getCell(14));
                    String employmentContractDate= String.valueOf(row.getCell(15));
                    int  socialInsuranceMonth = (int)Double.parseDouble(String.valueOf(row.getCell(16))) ;
                    String relationShip = String.valueOf(row.getCell(17));
                    String socialInsuranceNumber= String.valueOf(row.getCell(18));
                    String phoneNumber= String.valueOf(row.getCell(19));
                    String idCardNumber= String.valueOf(row.getCell(20));;
                    String citizenCardNumber= String.valueOf(row.getCell(21));;
                    String dateOfIssue= String.valueOf(row.getCell(22));;
                    String placeOfIssue= String.valueOf(row.getCell(23));

                    String address = String.valueOf(row.getCell(25));

                    int department_id = (int) Double.parseDouble(String.valueOf(row.getCell(24)));

                    String base_salary = String.valueOf(row.getCell(26));
                    String ef_salary = String.valueOf(row.getCell(27));
                    String salaryAmount = String.valueOf(row.getCell(28));

                    Optional<Department> optionalDepartment = departmentRepository.findById((long) department_id);
                    Department department = optionalDepartment.get();
                    Employee employee = new Employee();
                    employee.setEmployee_id(employee_id);
                    employee.setFullName(fullName);
                    employee.setLastName(lastName);
                    employee.setDateOfBirth(EmployeeService.convertStringToLocalDate(dateOfBirth));
                    employee.setGender(gender);
                    employee.setAge(age);
                    employee.setPlaceOfBirth(placeOfBirth);
                    employee.setQualification(qualification);
                    employee.setEducationLevel(educationLevel);
                    employee.setCulturalLevel(culturalLevel);
                    employee.setEducationLevel(educationLevel);
                    employee.setCulturalLevel(culturalLevel);
                    employee.setHomeTown(homeTown);
                    employee.setAccommodation(accommodation);
                    employee.setMaritalStatus(maritalStatus);
                    employee.setPosition(position);
                    employee.setJoiningday(EmployeeService.convertStringToLocalDate(joiningday));
                    employee.setEmploymentContractDate(EmployeeService.convertStringToLocalDate(employmentContractDate));
                    employee.setSocialInsuranceMonth(socialInsuranceMonth);
                    employee.setRelationShip(relationShip);
                    employee.setSocialInsuranceNumber(socialInsuranceNumber);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setIdCardNumber(idCardNumber);
                    employee.setCitizenCardNumber(citizenCardNumber);
                    employee.setDateOfIssue(EmployeeService.convertStringToLocalDate(dateOfIssue));
                    employee.setPlaceOfIssue(placeOfIssue);
                    employee.setDepartment(department);

                    LocationRegion locationRegion = new LocationRegion();
                    locationRegion.setAddress(address);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(str, formatter);
    }

    public static String converLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    @Override
    public StreamingResponseBody exportToExcel(HttpServletResponse response) {
        //StreamingResponseBody dùng để xuất dữ liệu dưới dạng file excel và trả nó về như một phản hồi HTTP
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No data found in database");
        }
        return outputStream -> {
            generateExcelData(outputStream, employees, response);
        };
    }

    private void generateExcelData(OutputStream outputStream, List<Employee> employees, HttpServletResponse response) {
        try ( //Tạo workbook
              ByteArrayOutputStream out = new ByteArrayOutputStream();
              SXSSFWorkbook workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE)
        )
        {
            System.out.println("Iam here");
            String sheetName = "Employee";
            Sheet sheet = workbook.createSheet(sheetName);
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            CellStyle headerColumnStyle = workbook.createCellStyle();
            headerColumnStyle.setFont(headerFont);
            Row headerRow = sheet.createRow(0);

            //Tạo các header
            String[] columns = new String[]{"Id", "MaNV", "FullName","Age", "Date of birth", "Phone","Address"};
            for (int i = 0; i < columns.length; i++) {
                Cell headerColumn = headerRow.createCell(i);
                headerColumn.setCellValue(columns[i]);
                headerColumn.setCellStyle(headerColumnStyle);
            }

            CellStyle dataColumnDateFormatStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            dataColumnDateFormatStyle.setDataFormat(creationHelper.createDataFormat().getFormat("d/m/yy h:mm;@"));


            int rowIndex = 1;
            for (Employee employee : employees) {
                Row dataRow = sheet.createRow(rowIndex);
                Cell columnId = dataRow.createCell(0);
                columnId.setCellValue(String.valueOf(employee.getId() != null ? employee.getId() : -1));

                Cell columnNV = dataRow.createCell(1);
                columnNV.setCellValue(String.valueOf(employee.getEmployee_id()!=null?employee.getEmployee_id():""));

                Cell columnName = dataRow.createCell(2);
                columnName.setCellValue(employee.getFullName() != null ? employee.getFullName() : "");

                Cell columnAge = dataRow.createCell(3);
                columnAge.setCellValue(String.valueOf(  String.valueOf(employee.getAge())!= null ? employee.getAge() : ""));

                Cell columnDateOfBirth = dataRow.createCell(4);
                columnDateOfBirth.setCellValue(String.valueOf(employee.getDateOfBirth()!=null ? employee.getDateOfBirth() : ""));

                Cell columnPhone = dataRow.createCell(5);
                columnPhone.setCellValue(String.valueOf(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : ""));


                Cell columnAddress = dataRow.createCell(6);
                columnAddress.setCellValue(employee.getLocationRegion().getAddress() != null ? employee.getLocationRegion().getAddress() : "");

                rowIndex++;
            }

            workbook.write(out);
            workbook.dispose();

            String filename = "Export-customer-data" + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.setContentLength(out.size());

            InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
            int BUFFER_SIZE = 1024;
            int bytesRead;
            byte[] buffer = new byte[BUFFER_SIZE];


            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
