package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browsers;
import static com.constants.Environments.*;
import com.utility.BrowserUtilities;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtilities {

    Logger logger = LoggerUtility.getLogger(getClass());

    public HomePage(WebDriver driver) throws Exception {
        super(driver);
        maximizeWindow();
        logger.info("Navigating to the home page.");
        navigateToUrl(JSONUtility.readJSON(QE).getUrl());
    }

    public HomePage(Browsers browser, boolean isHeadless) throws Exception {
        super(browser, isHeadless);
        maximizeWindow();
        logger.info("Navigating to the home page.");
        navigateToUrl(JSONUtility.readJSON(QE).getUrl());
    }

    private static final By SIGN_IN_LOCATOR = By.xpath("//a[contains(text(), 'Sign')]");

    public SignInPage goToSignInPage() {
        logger.info("Navigating to the Sign In page.");
        clickOnElement(SIGN_IN_LOCATOR);
        return new SignInPage(getDriver());
    }

}
