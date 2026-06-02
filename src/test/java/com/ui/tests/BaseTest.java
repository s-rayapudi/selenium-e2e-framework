package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.utility.BrowserUtilities;
import com.utility.LoggerUtility;

public class BaseTest {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(getClass());

    @BeforeMethod(description = "Initialize the HomePage before each test method.")
    public void setup() throws Exception {
        logger.info("Setting up the HomePage for the test.");
        homePage = new HomePage(Browsers.CHROME);
    }

    @AfterMethod(description = "Close the browser after each test method.")
    public void tearDown() {
        homePage.quitBrowser();
    }

    public BrowserUtilities getInstance() {
        return homePage;
    }

}
