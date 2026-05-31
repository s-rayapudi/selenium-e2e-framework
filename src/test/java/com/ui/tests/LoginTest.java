package com.ui.tests;

import static org.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.ui.pojos.UserCredentials;

public class LoginTest {

    private HomePage homePage;

    @BeforeMethod(description = "Initialize the HomePage before each test method.")
    public void setup() throws Exception {
        homePage = new HomePage(Browsers.CHROME);
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from a JSON file.", 
            groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, 
            dataProvider = "loginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginTest(UserCredentials userCredentials) {
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from a CSV file.", groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginDataProviderFromCSV")
    public void loginCSVTest(UserCredentials userCredentials) {
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from an Excel file.", groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginDataProviderFromExcel")
    public void loginExcelTest(UserCredentials userCredentials) {
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @AfterMethod(description = "Close the browser after each test method.")
    public void tearDown() {
        homePage.quitBrowser();
    }
}
