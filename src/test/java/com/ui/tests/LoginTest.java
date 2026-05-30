package com.ui.tests;

import static org.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.constants.Browsers;
import com.ui.pages.HomePage;

public class LoginTest {

    private HomePage homePage;

    @BeforeMethod(description = "Initialize the HomePage before each test method.")
    public void setup() throws Exception {
        homePage = new HomePage(Browsers.EDGE);
    }

    @Test(description = "Verify that a user can log in successfully with valid credentials.", groups = {"smoke", "sanity"})
    public void loginTest() {
        assertEquals(homePage.goToSignInPage().performSignIn("sandeep@qa.com", "Password").getUserName(), "Sandeep Raya");
    }

    @AfterMethod(description = "Close the browser after each test method.")
    public void tearDown() {
        homePage.quitBrowser();
    }
}
