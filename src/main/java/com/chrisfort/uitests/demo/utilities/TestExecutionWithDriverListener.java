package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by cpfort on 8/28/17.
 */
public class TestExecutionWithDriverListener extends AbstractTestExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestExecutionWithDriverListener
        .class);

    private static WebDriver driver;

    private static String reportScreenshotsLocation;

    private static String reportLocation;

    private static String screenShotOnSuccess;

    private static String screenShotOnFailure;

    private static String browserName;

    public static String getReportScreenshotsLocation() {
        return reportScreenshotsLocation;
    }

    public static void setReportScreenshotsLocation(String reportScreenshotsLocation) {
        TestExecutionWithDriverListener.reportScreenshotsLocation = reportScreenshotsLocation;
    }

    public static String getReportLocation() {
        return reportLocation;
    }

    public static void setReportLocation(String reportLocation) {
        TestExecutionWithDriverListener.reportLocation = reportLocation;
    }

    public static String getScreenShotOnSuccess() {
        return screenShotOnSuccess;
    }

    public static void setScreenShotOnSuccess(String screenShotOnSuccess) {
        TestExecutionWithDriverListener.screenShotOnSuccess = screenShotOnSuccess;
    }

    public static String getScreenShotOnFailure() {
        return screenShotOnFailure;
    }

    public static void setScreenShotOnFailure(String screenShotOnFailure) {
        TestExecutionWithDriverListener.screenShotOnFailure = screenShotOnFailure;
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static void setBrowserName(String browserName) {
        TestExecutionWithDriverListener.browserName = browserName;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        TestExecutionWithDriverListener.driver = driver;
    }

    public void prepareTestInstance(TestContext testContext) throws Exception {
        PropertySourcesPlaceholderConfigurer propertiesPlaceholderConfigurer =
            (PropertySourcesPlaceholderConfigurer) testContext.getApplicationContext().getBean(
                "properties");

        PropertySource properties = propertiesPlaceholderConfigurer.getAppliedPropertySources().get(
            PropertySourcesPlaceholderConfigurer.LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME);

        setReportLocation((String) properties.getProperty("report.location"));
        setReportScreenshotsLocation(
            (String) properties.getProperty("report.screenshots.location"));
        setScreenShotOnFailure((String) properties.getProperty("report.screenshots.on.failure"));
        setScreenShotOnSuccess((String) properties.getProperty("report.screenshots.on.success"));

        WebDriver driver = testContext.getApplicationContext().getBean(WebDriver.class);
        setDriver(driver);

        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        setBrowserName(capabilities.getBrowserName().toLowerCase());
    }

    /**
     * This is a hack to pass the driver to the TestNG Test Listener.
     *
     * @param testContext The application context in a test run
     */
    @Override
    public void beforeTestMethod(TestContext testContext) {
        driver = testContext.getApplicationContext().getBean(WebDriver.class);
    }
}
