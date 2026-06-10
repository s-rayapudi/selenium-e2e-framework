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
    private static final By INVALID_CREDENTIALS_ERROR_LOCATOR = By.xpath("//div[contains(@class, 'alert')]/ol/li");
    private static final By FORGGOT_PASSWORD_LINK_LOCATOR = By.xpath("//a[text()='Forgot your password?']");
    private static final By PASSWORD_RESET_EMAIL_FIELD_LOCATOR = By.id("email");
    private static final By PASSWORD_RETRIEVE_BUTTON_LOCATOR = By.xpath("//span[text()='Retrieve Password']");
    private static final By PASSWORD_REST_SUCCESS_MESSAGE_LOCATOR = By.xpath("//div[@id='center_column']/div/p");
    private static final By ACCOUNT_CREATION_EMAIL_FIELD_LOCATOR = By.xpath("//input[@id='email_create']");
    private static final By CREATE_ACCOUNT_BUTTON_LOCATOR = By.id("SubmitCreate");
    private static final By ACCOUNT_ALREADY_EXISTS_ERROR_LOCATOR = By.xpath("//div[@id='create_account_error']/ol/li");
    private static final By ACCOUNT_DOES_NOT_EXIST_ERROR_LOCATOR = By.xpath("//div[contains(@class, 'alert')]/ol/li");

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

    public SignInPage performSignInWithInvalidCredentials(String emailId, String password) {
        logger.info("Performing sign in with invalid credentials: email: {}, password: {}", emailId, password);
        enterTextToElement(EMAIL_ID_LOCATOR, emailId);
        enterTextToElement(PASSWORD_LOCATOR, password);
        clickOnElement(SIGN_IN_BUTTON_LOCATOR);
        return new SignInPage(getDriver());
    }

    public String captureInvalidSignInErrorMessage() {
        return getTextFromElement(INVALID_CREDENTIALS_ERROR_LOCATOR);
    }

    public SignInPage clickOnForgotPasswordLink() {
        clickOnElement(FORGGOT_PASSWORD_LINK_LOCATOR);
        logger.info("Clicked on forgot password link.");
        return this;
    }

    public SignInPage resetPassword(String emailId) {
        enterTextToElement(PASSWORD_RESET_EMAIL_FIELD_LOCATOR, emailId);
        clickOnElement(PASSWORD_RETRIEVE_BUTTON_LOCATOR);
        logger.info("Submitted password reset request for email: {}", emailId);
        return this;
    }

    public String capturePasswordResetSuccessMessage() {
        return getTextFromElement(PASSWORD_REST_SUCCESS_MESSAGE_LOCATOR);
    }

    public SignInPage createAccountWithExistingEmail(String emailId) {
        enterTextToElement(ACCOUNT_CREATION_EMAIL_FIELD_LOCATOR, emailId);
        clickOnElement(CREATE_ACCOUNT_BUTTON_LOCATOR);
        logger.info("Attempted to create account with existing email: {}", emailId);
        return this;
    }

    public String captureAccountCreationErrorMessage() {
        return getTextFromElement(ACCOUNT_ALREADY_EXISTS_ERROR_LOCATOR);
    }

    public String captureAccountDoesNotExistErrorMessage() {
        return getTextFromElement(ACCOUNT_DOES_NOT_EXIST_ERROR_LOCATOR);
    }

}
