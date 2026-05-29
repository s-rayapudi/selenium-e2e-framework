package com.ui.tests;

import com.constants.Browsers;
import com.ui.pages.HomePage;

public class LoginTest {
    public static void main(String[] args) {
        HomePage homePage = new HomePage(Browsers.EDGE);
        String userName = homePage.goToSignInPage()
                .performSignIn("sandeep@qa.com", "Password")
                .getUserName();
        homePage.quitBrowser();
        System.out.println("Login test completed successfully.");
        System.out.println("Logged in user name: " + userName);
    }
}
