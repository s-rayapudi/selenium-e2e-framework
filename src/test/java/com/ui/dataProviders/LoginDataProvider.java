package com.ui.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.google.gson.Gson;
import com.ui.pojos.UserCredentials;
import com.ui.pojos.UserCredentialsTestData;
import com.utility.CSVReaderUtility;

import org.testng.annotations.DataProvider;


public class LoginDataProvider {

    @DataProvider(name = "loginDataProvider")
    public static Iterator<Object[]> loginDataProvider() throws FileNotFoundException {

        Gson gson = new Gson();
        File loginDataFile = new File(System.getProperty("user.dir") + "/testData/loginData.json");
        FileReader fileReader = new FileReader(loginDataFile);
        UserCredentialsTestData testData = gson.fromJson(fileReader, UserCredentialsTestData.class);

        List<Object[]> dataToBeReturned = new ArrayList<>();
        for(UserCredentials userCredentials : testData.getUserData()){
            dataToBeReturned.add(new Object[] {userCredentials});
        }
        return dataToBeReturned.iterator();

    }

    @DataProvider(name = "loginDataProviderFromCSV")
    public Iterator<UserCredentials> loginDataProviderFromCSV() {
        return CSVReaderUtility.readCSVFile("loginData.csv");
    }

}
