package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by cpfort on 8/28/17.
 */
public class TestExecutionWithDriverListener extends AbstractTestExecutionListener {

    private static WebDriver driver;

    private static String reportScreenshotsLocation;

    private static String reportLocation;

    private static String screenShotOnSuccess;

    private static String screenshotOnFailure;

    private static String browserName;


}
