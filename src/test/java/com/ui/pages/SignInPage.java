package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtilities;

public final class SignInPage extends BrowserUtilities {

    private static final By EMAIL_ID_LOCATOR = By.id("email");
    private static final By PASSWORD_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage performSignIn(String emailId, String password) {
        enterTextToElement(EMAIL_ID_LOCATOR, emailId);
        enterTextToElement(PASSWORD_LOCATOR, password);
        clickOnElement(SIGN_IN_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }

}
