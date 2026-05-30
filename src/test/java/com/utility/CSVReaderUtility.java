package com.utility;

import java.io.File;
import java.io.FileReader;
import java.util.*;

import com.opencsv.CSVReader;
import com.ui.pojos.UserCredentials;

public class CSVReaderUtility {
    public static Iterator<UserCredentials> readCSVFile (String fileName) {
        File csvFile = new File(System.getProperty("user.dir") + "/testData/" + fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        String[] nextLine;
        UserCredentials userCredentials;
        List<UserCredentials> userCredentialsList = null;

        try{
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            userCredentialsList = new ArrayList<>();
            while((nextLine = csvReader.readNext()) != null){
                userCredentials = new UserCredentials(nextLine[0], nextLine[1], nextLine[2]);
                userCredentialsList.add(userCredentials);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userCredentialsList.iterator();
    }
}
