package com.ui.pages;

import org.openqa.selenium.By;

import com.constants.Browsers;
import static com.constants.Environments.*;
import com.utility.BrowserUtilities;
import com.utility.JSONUtility;

public final class HomePage extends BrowserUtilities {

    public HomePage(Browsers browser) throws Exception {
        super(browser);
        maximizeWindow();
        navigateToUrl(JSONUtility.readJSON(QE).getUrl());
    }

    private static final By SIGN_IN_LOCATOR = By.xpath("//a[contains(text(), 'Sign')]");

    public SignInPage goToSignInPage() {
        clickOnElement(SIGN_IN_LOCATOR);
        return new SignInPage(getDriver());
    }

}
