package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;

/**
 * Created by cpfort on 8/25/17.
 */
public class WebDriverFactory {

    @Value("${chromeDriver.binary.path}")
    private String chromeDriverBinaryPath;

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    public WebDriver webDriver(URL seleniumGridHost, String browser) {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", chromeDriverBinaryPath);

        LOG.debug("Creating WebDriver as {}", browser);

        BrowserType browserType = BrowserType.valueOf(browser);

        switch (browserType) {
            case CHROME:
                driver = chromeDriver(seleniumGridHost);
                break;
            case FIREFOX:
                driver = firefoxDriver(seleniumGridHost);
                break;
            default:
                driver = chromeDriver(seleniumGridHost);
        }

        return driver;
    }

    //Basic Chrome driver
    private WebDriver chromeDriver(URL seleniumGridHost) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        return (WebDriver) (null == seleniumGridHost ? new ChromeDriver() : new RemoteWebDriver(
            seleniumGridHost, desiredCapabilities));
    }

    //Basic Firefox driver
    private WebDriver firefoxDriver(URL seleniumGridHost) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        return (WebDriver) (null == seleniumGridHost ? new FirefoxDriver() : new RemoteWebDriver(
            seleniumGridHost, desiredCapabilities));
    }

    public enum BrowserType {
        CHROME,
        FIREFOX
    }

}
