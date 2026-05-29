package com.ui.pages;

import org.openqa.selenium.By;

import com.constants.Browsers;
import com.utility.BrowserUtilities;

public final class HomePage extends BrowserUtilities {

    public HomePage(Browsers browser) {
        super(browser);
        maximizeWindow();
        navigateToUrl("https://automationpractice.techwithjatin.com/");
    }

    private static final By SIGN_IN_LOCATOR = By.xpath("//a[contains(text(), 'Sign')]");

    public SignInPage goToSignInPage() {
        clickOnElement(SIGN_IN_LOCATOR);
        return new SignInPage(getDriver());
    }

}
