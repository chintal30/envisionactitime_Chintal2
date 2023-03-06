package com.envision.actitime.filereader;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ExcelReaderAndDataProvider {
    static Sheet sheet;

    static {
        try (FileInputStream fis = new FileInputStream("actitime_testdata/ActiTimeTestData.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            sheet = wb.getSheet("Sheet1");
            System.out.println(wb);
            System.out.println(sheet.getSheetName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "excel_valid_login_tests")
    public static Object[][] testdata3(Method methodName) {
        ArrayList<String[]> outerArrayList = new ArrayList<>();

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row eachRow = sheet.getRow(rowIndex);

            String testCaseName = eachRow.getCell(1).getStringCellValue();
            String executionStatus = eachRow.getCell(2).getStringCellValue();

            ArrayList<String> innerArrayList = new ArrayList<>();
            if (testCaseName.equalsIgnoreCase(methodName.getName()) && executionStatus.equalsIgnoreCase("valid")) {
                for (int dataCellIndex = 3; dataCellIndex < eachRow.getPhysicalNumberOfCells(); dataCellIndex++) {
                    innerArrayList.add(eachRow.getCell(dataCellIndex).getStringCellValue());
                }
                outerArrayList.add(innerArrayList.toArray(new String[0]));
            }
        }
        return outerArrayList.toArray(new String[0][0]);
    }

    @DataProvider(name = "excel_invalid_login_tests")
    public static Object[][] testdata4(Method methodName) {
        ArrayList<String[]> outerArrayList = new ArrayList<>();

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row eachRow = sheet.getRow(rowIndex);

            String testCaseName = eachRow.getCell(1).getStringCellValue();
            String executionStatus = eachRow.getCell(2).getStringCellValue();

            ArrayList<String> innerArrayList = new ArrayList<>();
            if (testCaseName.equalsIgnoreCase(methodName.getName()) && executionStatus.equalsIgnoreCase("invalid")) {
                for (int dataCellIndex = 3; dataCellIndex < eachRow.getPhysicalNumberOfCells(); dataCellIndex++) {
                    innerArrayList.add(eachRow.getCell(dataCellIndex).getStringCellValue());
                }
                outerArrayList.add(innerArrayList.toArray(new String[0]));
            }
        }
        return outerArrayList.toArray(new String[0][0]);
    }


//    public static void printExcelSheetData() {
//        ArrayList<String[]> outerArrayList = new ArrayList<>();
//        for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
//            Row eachRow = sheet.getRow(rowIndex);
//            ArrayList<String> innerArrayList = new ArrayList<>();
//            for (int dataCellIndex = 0; dataCellIndex < eachRow.getPhysicalNumberOfCells(); dataCellIndex++) {
//                innerArrayList.add(eachRow.getCell(dataCellIndex).getStringCellValue());
//            }
//            outerArrayList.add(innerArrayList.toArray(new String[0]));
//        }
//        String[][] a = outerArrayList.toArray(new String[0][0]);
//        for (int i = 1; i < a.length; i++) {
//            for (int j = 3; j < a[i].length; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println(); // print a newline after each row
//        }
//    }
//
//    public static void main(String[] args) {
//        printExcelSheetData();
//    }
}
