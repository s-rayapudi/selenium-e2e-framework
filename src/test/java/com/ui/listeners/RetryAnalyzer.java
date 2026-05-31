package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Environments;
import com.utility.PropertiesUtility;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT;
    private static int retryCount = 0;

    static {
        int count = 0;
        try {
            count = Integer.parseInt(PropertiesUtility.readProperties(Environments.QE, "MAX_RETRY_COUNT"));
        } catch (Exception e) {
            // fallback to 0 if property cannot be read
        }
        MAX_RETRY_COUNT = count;
    }

    @Override
    public boolean retry (ITestResult result) {
        if(retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }

}
