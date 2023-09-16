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

        employee.setSalary(employeeReqUpDTO.getSalaryDTO().toSalary());
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
                //Cho vòng lặp chạy trên cột thứ nhất (id), chạy qua tất cả các hàng của cột
                for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0); rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (rowIndex == 0) {
                        continue;
                    }
                    Long employee_id = Long.parseLong(String.valueOf(getValue(row.getCell(1))));
                    String fullName = String.valueOf(getValue(row.getCell(2)));
                    String lastName = String.valueOf(getValue(row.getCell(3)));
                    String dateOfBirth = String.valueOf(getValue(row.getCell(4)));
                    String gender = String.valueOf(getValue(row.getCell(5)));
                    int age = Integer.parseInt(String.valueOf(row.getCell(6)));
                    String placeOfBirth = String.valueOf(row.getCell(7));
                    String qualification = String.valueOf(row.getCell(8));
                    String educationLevel = String.valueOf(row.getCell(9));
                    String culturalLevel = String.valueOf(row.getCell(10));
                    String homeTown =String.valueOf(row.getCell(11));
                    String accommodation =String.valueOf(row.getCell(12));
                    String maritalStatus = String.valueOf(row.getCell(13));
                    String position = String.valueOf(row.getCell(14));
                    String joiningday = String.valueOf(row.getCell(15));
                    String employmentContractDate= String.valueOf(row.getCell(16));
                    int  socialInsuranceMonth = Integer.parseInt(String.valueOf(row.getCell(17))) ;
                    String relationShip = String.valueOf(row.getCell(18));
                    String socialInsuranceNumber= String.valueOf(row.getCell(19));
                    String phoneNumber= String.valueOf(row.getCell(20));
                    String idCardNumber= String.valueOf(row.getCell(21));;
                    String citizenCardNumber= String.valueOf(row.getCell(22));;
                    String dateOfIssue= String.valueOf(row.getCell(23));;
                    String placeOfIssue= String.valueOf(row.getCell(24));

                    int department_id = Integer.parseInt(String.valueOf(row.getCell(25)));
                    Optional<Department> optionalDepartment = departmentRepository.findById((long) department_id);
                    Department department = optionalDepartment.get();

//                    int salary_id = Integer.parseInt(String.valueOf(row.getCell(26)));

//                    String address = String.valueOf(row.getCell(27));
                    Employee employee = new Employee();
                    employee.setEmployee_id(employee_id);
                    employee.setFullName(fullName);
                    employee.setLastName(lastName);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!employees.isEmpty()) {
                employeeRepository.saveAll(employees);
            }
        }
    }


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


}
