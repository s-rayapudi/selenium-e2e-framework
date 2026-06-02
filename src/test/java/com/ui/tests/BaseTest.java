package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.utility.BrowserUtilities;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class BaseTest {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(getClass());
    private boolean isLambdaTest;

    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Initialize the HomePage before each test method.")
    public void setup(
        @Optional ("chrome") String browser, 
        @Optional ("false") boolean isLambdatest, 
        @Optional ("true") boolean isHeadless,ITestResult result) throws Exception {
        logger.info("Setting up the HomePage for the test.");
        if(isLambdaTest) {
            WebDriver lambdaTestDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaTestDriver);
        }
        else {
            homePage = new HomePage(Browsers.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    @AfterMethod(description = "Close the browser after each test method.")
    public void tearDown() {
        if(isLambdaTest) {
            LambdaTestUtility.quitLambdaTestSession();
        }
        else
            homePage.quitBrowser();
    }

    public BrowserUtilities getInstance() {
        return homePage;
    }

}
