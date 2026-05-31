package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojos.UserCredentials;

public class ExcelReaderUtility {

    public static Iterator<UserCredentials> readExcelData (String fileName) {
        File excelFile = new File(System.getProperty("user.dir") + "/testdata/" + fileName);

        XSSFWorkbook workbook = null;
        XSSFSheet sheet;
        Iterator<Row> rowIterator;
        UserCredentials userCredentials;
        List<UserCredentials> userCredentialsTestData = new ArrayList<>();
        try {
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet("ExcelLoginData");
            rowIterator = sheet.iterator();
            rowIterator.next(); 
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String emailAddress = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                String userName = row.getCell(2).getStringCellValue();
                userCredentials = new UserCredentials(emailAddress, password, userName);
                userCredentialsTestData.add(userCredentials);
                workbook.close();
            }

        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }

        return userCredentialsTestData.iterator();

    }

}
