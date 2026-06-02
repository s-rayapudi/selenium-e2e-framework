package com.ui.tests;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.ui.pojos.UserCredentials;
import com.utility.LoggerUtility;


@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest {

    private HomePage homePage;
    Logger logger = LoggerUtility.getLogger(getClass());

    @BeforeMethod(description = "Initialize the HomePage before each test method.")
    public void setup() throws Exception {
        logger.info("Setting up the HomePage for the test.");
        homePage = new HomePage(Browsers.CHROME);
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from a JSON file.", 
            groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, 
            dataProvider = "loginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginTest(UserCredentials userCredentials) {
        logger.info("Executing login test with valid credentials.");
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from a CSV file.", groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginDataProviderFromCSV")
    public void loginCSVTest(UserCredentials userCredentials) {
        logger.info("Executing login test from CSV with valid credentials.");
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials. When the data is read from an Excel file.", groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginDataProviderFromExcel")
    public void loginExcelTest(UserCredentials userCredentials) {
        logger.info("Executing login test from Excel with valid credentials.");
        assertEquals(homePage.goToSignInPage().performSignIn(userCredentials.getEmailAddress(), userCredentials.getPassword()).getUserName(), userCredentials.getUserName());
    }

    @AfterMethod(description = "Close the browser after each test method.")
    public void tearDown() {
        homePage.quitBrowser();
    }
}
