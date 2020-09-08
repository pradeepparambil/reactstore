package com.teksenz.reactstore.listener;

import com.teksenz.reactstore.lib.TestBase;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    protected static final Logger log = LoggerFactory.getLogger(TestNGListener.class);
    @Override
    public void onTestStart(ITestResult result) {
        log.debug("Test Started - "+ result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.debug("Test Succeeded - "+ result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.debug("Test failed -  "+ result.getMethod().getDescription());
        saveScreenshot(TestBase.getDriver());

    }

    @Attachment
    private byte[] saveScreenshot(RemoteWebDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("Test skipped -  "+ result.getMethod().getDescription());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
