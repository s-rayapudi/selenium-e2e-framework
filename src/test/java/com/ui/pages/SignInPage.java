package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtilities;
import com.utility.LoggerUtility;

public final class SignInPage extends BrowserUtilities {

    private static final By EMAIL_ID_LOCATOR = By.id("email");
    private static final By PASSWORD_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");
    Logger logger = LoggerUtility.getLogger(getClass());

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage performSignIn(String emailId, String password) {
        logger.info("Performing sign in with email: {}", emailId);
        enterTextToElement(EMAIL_ID_LOCATOR, emailId);
        enterTextToElement(PASSWORD_LOCATOR, password);
        clickOnElement(SIGN_IN_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }

}
