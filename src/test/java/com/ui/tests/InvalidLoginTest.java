package com.ui.tests;

import static org.testng.Assert.*;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class InvalidLoginTest extends BaseTest {

    private static final String INVALID_EMAIL = "invalid@email.com";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @Test(description = "Verify that a user cannot login with invalid credentials.", groups = {"negative"}, retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void invalidLoginTest() {
        logger.info("Executing invalid lohin test");
        assertEquals(homePage.goToSignInPage().performSignInWithInvalidCredentials(INVALID_EMAIL, INVALID_PASSWORD).captureInvalidSignInErrorMessage(), "Authentication failed.");
        
    }

    @Test(description = "Verify that a user cannot login with empty credentials.", groups = {"negative"}, retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void emptyCredentialsLoginTest() {
        logger.info("Executing empty credentials Login Test");
        assertEquals(homePage.goToSignInPage().performSignInWithInvalidCredentials("", "").captureInvalidSignInErrorMessage(), "An email address required.");
    }

}
