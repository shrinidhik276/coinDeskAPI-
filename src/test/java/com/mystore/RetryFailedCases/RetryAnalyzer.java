package com.mystore.RetryFailedCases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxRetryCount = 1; // Max retry attempts

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            // Retry logic: retry only failed tests
            if (count < maxRetryCount) {
                count++;
                System.out.println("Retrying " + result.getName() + " for the " + count + " time.");
                return true;  // Retry the failed test
            }
        }
        return false;  // Don't retry if test is successful or max retries reached
    }
}