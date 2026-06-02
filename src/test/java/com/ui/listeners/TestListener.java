package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(getClass());

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} is passed", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("{} is failed", result.getMethod().getMethodName());
        logger.error(result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("{} is skipped", result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite {} is started", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite {} is finished", context.getName());
    }

}
