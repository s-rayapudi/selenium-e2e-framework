package com.utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.constants.Browsers;

public class BrowserUtilities {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(getClass());

    public WebDriver getDriver() {
        return driver.get();
    }
    
    public BrowserUtilities(WebDriver driver) {
        BrowserUtilities.driver.set(driver);
        logger.info("BrowserUtilities initialized with the provided WebDriver.");
    }

    public BrowserUtilities(Browsers browserName, boolean isHeadless){
        logger.info("Initializing BrowserUtilities with browser: {}", browserName);
        if(browserName == Browsers.CHROME){
            if(isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver.set(new ChromeDriver(options));
            }
            else
                driver.set(new ChromeDriver());
        }
        else if(browserName == Browsers.EDGE){
            if(isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                driver.set(new EdgeDriver(options));
            }
            else
                driver.set(new EdgeDriver());
        }
        else if(browserName == Browsers.FIREFOX){
            if(isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver.set(new FirefoxDriver());
            }
            driver.set(new FirefoxDriver());
        }
        else {
            logger.error("Invalid browser name provided: {}", browserName);
            throw new RuntimeException("Invalid browser name provided: " + browserName);
        }
    }

    public void maximizeWindow(){
        logger.info("Maximizing the browser window.");
        driver.get().manage().window().maximize();
    }

    public void navigateToUrl(String url){
        logger.info("Navigating to URL: {}", url);
        driver.get().get(url);
    }

    public void quitBrowser(){
        logger.info("Closing the browser.");
        driver.get().quit();
    }

    public void clickOnElement(By locator){
        logger.info("Clicking on element with locator: {}", locator);
        WebElement element = driver.get().findElement(locator);
        element.click();
    }

    public void enterTextToElement(By locator, String textToEnter){
        logger.info("Entering text '{}' into element with locator: {}", textToEnter, locator);
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToEnter);
    }

    public String getTextFromElement(By locator){
        logger.info("Getting text from element with locator: {}", locator);
        WebElement element = driver.get().findElement(locator);
        return element.getText();
    }

    public String takeScreenshot(String filePath) {
        logger.info("Taking screenshot and saving to file: {}", filePath);
            TakesScreenshot screenshot = (TakesScreenshot) driver.get();
            File takenScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = formatter.format(date);
            String destinationPath = System.getProperty("user.dir") + "/screenshots/" + filePath + "_" + timestamp + ".png";
            File screenshotDestination = new File(destinationPath);        
        try {
            FileUtils.copyFile(takenScreenshot, screenshotDestination);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage());
            throw new RuntimeException("Unable to save screenshot", e);
        }
        return destinationPath;
    }

}
