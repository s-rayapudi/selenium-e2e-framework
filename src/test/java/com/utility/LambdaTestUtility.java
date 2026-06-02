package com.utility;

import java.net.URL;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> localCapabilities = new ThreadLocal<DesiredCapabilities>();

    public static WebDriver initializeLambdaTestSession(String browserName, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", 148);
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "sandeeprayapudiin");
        ltOptions.put("accessKey", "LT_jZhrjvAV99b8I3Q54Oaxa6ATegrm6m3JxPZoZftEir1nBij");
        ltOptions.put("build", "Selenium 4 Java Framework");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 11");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.44.0");
        capabilities.setCapability("LT:Options", ltOptions);
        localCapabilities.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), localCapabilities.get());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        localDriver.set(driver);
        return localDriver.get();

    }

    public static void quitLambdaTestSession() {
        if (localDriver.get() != null) {
            localDriver.get().quit();
        }
    }
}
