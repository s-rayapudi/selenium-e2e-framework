package com.ui.tests;

import static org.testng.Assert.*;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojos.UserCredentials;

@Listeners({com.ui.listeners.TestListener.class})
public class PasswordResetTest extends BaseTest {

    private static final String NON_REGISTERED_EMAIL = "chow@email.com";

    @Test(description = "Verify that a user can reset password successfully with valid email.", 
            groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, 
            dataProvider = "loginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void passwordResetTest(UserCredentials userCredentials) {
        logger.info("Executing password reset test with valid email: {}", userCredentials.getEmailAddress());
        assertEquals(homePage.goToSignInPage().clickOnForgotPasswordLink().resetPassword(userCredentials.getEmailAddress()).capturePasswordResetSuccessMessage(), "A confirmation email has been sent to your address: " + userCredentials.getEmailAddress());
    }

    @Test(description = "Verify that a user cannot create a new account with an email that already exists.", 
            groups = {"smoke", "sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, 
            dataProvider = "loginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void accountCreationWithExistingEmailTest(UserCredentials userCredentials) {
        logger.info("Executing account creation with existing email test using email: {}", userCredentials.getEmailAddress());
        assertEquals(homePage.goToSignInPage().createAccountWithExistingEmail(userCredentials.getEmailAddress()).captureAccountCreationErrorMessage(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
    }

    @Test(description = "Verify that a user cannot reset password with an email that does not exist.", 
            groups = {"negative"}, retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void passwordResetWithNonRegisteredEmailTest() {
        logger.info("Executing password reset test with non-registered email: {}", NON_REGISTERED_EMAIL);
        assertEquals(homePage.goToSignInPage().clickOnForgotPasswordLink().resetPassword(NON_REGISTERED_EMAIL).captureAccountDoesNotExistErrorMessage(), "There is no account registered for this email address.");
    }

}
