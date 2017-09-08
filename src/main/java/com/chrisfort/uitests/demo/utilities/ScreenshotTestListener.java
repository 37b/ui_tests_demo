package com.chrisfort.uitests.demo.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * Created by cpfort on 8/29/17.
 */
public class ScreenshotTestListener implements ITestListener {

    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotTestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
    }

    public void onFinish(ITestContext context) {}

    public void onTestStart(ITestResult result) { }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    public void onStart(ITestContext context) { }

    private void takeScreenshot(ITestResult iTestResult) {

        WebDriver driver = ((AbstractTest) iTestResult.getInstance()).driver();

        String reportScreenshotsLocation =
            ((AbstractTest) iTestResult.getInstance()).reportScreenshotsLocation;

        String reportLocation = ((AbstractTest) iTestResult.getInstance()).reportLocation;

        String location = reportLocation + File.separator + reportScreenshotsLocation;

        String fileName = iTestResult.getMethod().getMethodName() + ".png";

        try {
            String filePath = location + File.separator + fileName;
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(filePath));
            LOG.info("Screenshot saved at [{}]", filePath);
        } catch (IOException ioe) {
            LOG.warn("Unable to save screen shot.");
            LOG.error("Details: [{}]", ioe);
        }
    }
}
