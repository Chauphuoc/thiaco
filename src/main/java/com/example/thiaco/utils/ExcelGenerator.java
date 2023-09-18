package com.example.thiaco.utils;

import com.example.thiaco.model.employee.Employee;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelGenerator {
    private List < Employee > employeeList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Employee> employeeList) {
        this.employeeList = employeeList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Employee");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(IndexedColors.BLUE.getIndex());
        style.setFont(font);
        String[] listHeader = new String[]{"Mã NV","Họ và tên","Ngày sinh","Tuổi","Địa chỉ","Vị trí","Phòng ban","Lương"};
        for (int i = 0; i < listHeader.length; i++) {
            Cell headerColumn = row.createCell(i);
            headerColumn.setCellValue(listHeader[i]);
            headerColumn.setCellStyle(style);
        }

    }
    private void createCell(Row row, int column, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(column);
        Cell cell = row.createCell(column);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            cell.setCellValue(((LocalDate) valueOfCell).format(formatter));
        } else if (valueOfCell instanceof BigDecimal) {
            cell.setCellValue(valueOfCell.toString());
        }
        else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Employee record: employeeList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getEmployee_id(), style);
            createCell(row,columnCount++,record.getFullName(),style);
            createCell(row,columnCount++,record.getDateOfBirth(),style);
            createCell(row,columnCount++,record.getAge(),style);
            createCell(row,columnCount++,record.getLocationRegion().getAddress(),style);
            createCell(row,columnCount++,record.getPosition(),style);
            createCell(row,columnCount++,record.getDepartment().getDepartmentName(),style);
            createCell(row,columnCount++,record.getSalary().getSalaryAmount(),style);
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
