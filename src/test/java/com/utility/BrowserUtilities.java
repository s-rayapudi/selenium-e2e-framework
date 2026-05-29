package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constants.Browsers;

public class BrowserUtilities {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    
    public BrowserUtilities(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserUtilities(Browsers browserName){
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
            throw new RuntimeException("Invalid browser name provided: " + browserName);
        }
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public void navigateToUrl(String url){
        driver.get(url);
    }

    public void quitBrowser(){
        driver.quit();
    }

    public void clickOnElement(By locator){
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void enterTextToElement(By locator, String textToEnter){
        WebElement element = driver.findElement(locator);
        element.sendKeys(textToEnter);
    }

    public String getTextFromElement(By locator){
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

}
