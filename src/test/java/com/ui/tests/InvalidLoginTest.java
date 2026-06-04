package com.ui.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;


public class InvalidLoginTest extends BaseTest {

    private static final String INVALID_EMAIL = "invalid@email.com";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @Test(description = "Verify that a user cannot login with invalid credentials.", groups = {"negative"}, retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void invalidLoginTest() {
        logger.info("Executing invalid lohin test");
        assertEquals(homePage.goToSignInPage().performSignInWithInvalidCredentials(INVALID_EMAIL, INVALID_PASSWORD).captureInvalidSignInErrorMessage(), "Authentication failed.");
        
    }


}
