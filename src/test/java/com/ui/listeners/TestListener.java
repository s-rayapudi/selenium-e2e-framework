package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.tests.BaseTest;
import com.utility.BrowserUtilities;
import com.utility.ExtentReportsUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(getClass());

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        ExtentReportsUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} is passed", result.getMethod().getMethodName());
        ExtentReportsUtility.getTestInfo().log(Status.PASS, result.getMethod().getMethodName() + " is PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("{} is failed", result.getMethod().getMethodName());
        logger.error(result.getThrowable().getMessage());
        ExtentReportsUtility.getTestInfo().log(Status.FAIL, result.getMethod().getMethodName() + " is FAILED");
        ExtentReportsUtility.getTestInfo().log(Status.FAIL, result.getThrowable().getMessage());
        Object baseTestInstance = result.getInstance();
        BrowserUtilities browserUtilities = ((BaseTest) baseTestInstance).getInstance();
        logger.info("Capturing screenshot for failed test: {}", result.getMethod().getMethodName());
        String screenshotPath = browserUtilities.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching screenshot to the report: {}", screenshotPath);
        ExtentReportsUtility.getTestInfo().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("{} is skipped", result.getMethod().getMethodName());
        ExtentReportsUtility.getTestInfo().log(Status.SKIP, result.getMethod().getMethodName() + " is SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite {} is started", context.getName());
        ExtentReportsUtility.setUpExtentSparkReporter("report.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite {} is finished", context.getName());
        ExtentReportsUtility.flushReport();
    }

}
