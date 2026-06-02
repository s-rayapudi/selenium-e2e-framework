package com.utility;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constants.Browsers;

public class BrowserUtilities {
    private WebDriver driver;
    Logger logger = LoggerUtility.getLogger(getClass());

    public WebDriver getDriver() {
        return driver;
    }
    
    public BrowserUtilities(WebDriver driver) {
        this.driver = driver;
        logger.info("BrowserUtilities initialized with the provided WebDriver.");
    }

    public BrowserUtilities(Browsers browserName){
        logger.info("Initializing BrowserUtilities with browser: {}", browserName);
        if(browserName == Browsers.CHROME){
            driver = new ChromeDriver();
        }
        else if(browserName == Browsers.EDGE){
            driver = new EdgeDriver();
        }
        else if(browserName == Browsers.FIREFOX){
            driver = new FirefoxDriver();
        }
        else {
            logger.error("Invalid browser name provided: {}", browserName);
            throw new RuntimeException("Invalid browser name provided: " + browserName);
        }
    }

    public void maximizeWindow(){
        logger.info("Maximizing the browser window.");
        driver.manage().window().maximize();
    }

    public void navigateToUrl(String url){
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    public void quitBrowser(){
        logger.info("Closing the browser.");
        driver.quit();
    }

    public void clickOnElement(By locator){
        logger.info("Clicking on element with locator: {}", locator);
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void enterTextToElement(By locator, String textToEnter){
        logger.info("Entering text '{}' into element with locator: {}", textToEnter, locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(textToEnter);
    }

    public String getTextFromElement(By locator){
        logger.info("Getting text from element with locator: {}", locator);
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

}
