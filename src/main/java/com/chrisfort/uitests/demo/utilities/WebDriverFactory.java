package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpfort on 8/25/17.
 */
public class WebDriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    @Value("${chromeDriver.binary.path}")
    private String chromeDriverBinaryPath;

    @Value("${firefox.binary.path}")
    private String firefoxBinaryPath;

    public WebDriver webDriver(URL seleniumGridHost, String browser) {
        WebDriver driver = null;

        LOG.debug("Chrome driver path [{}]", chromeDriverBinaryPath);
        LOG.debug("Firefox driver path [{}]", firefoxBinaryPath);

        System.setProperty("webdriver.chrome.driver", chromeDriverBinaryPath);
        System.setProperty("webdriver.gecko.driver", firefoxBinaryPath);

        LOG.debug("Creating WebDriver as {}", browser);

        BrowserType browserType = BrowserType.valueOf(browser);

        switch (browserType) {
            case CHROME:
                driver = chromeDriver(seleniumGridHost);
                break;
            case FIREFOX:
                driver = firefoxDriver(seleniumGridHost);
                break;
            case IPHONE_6_EMULATOR:
                driver = mobileEmulationDriver(seleniumGridHost, "Apple iPhone 6");
                break;
            default:
                driver = chromeDriver(seleniumGridHost);
        }

        return driver;
    }

    //Basic Chrome driver
    private WebDriver chromeDriver(URL seleniumGridHost) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        return (null == seleniumGridHost ? new ChromeDriver() : new RemoteWebDriver(
            seleniumGridHost, desiredCapabilities));
    }

    //Basic Firefox driver
    private WebDriver firefoxDriver(URL seleniumGridHost) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        return (null == seleniumGridHost ? new FirefoxDriver() : new RemoteWebDriver(
            seleniumGridHost, desiredCapabilities));
    }

    private WebDriver mobileEmulationDriver(URL seleniumGridHost, String mobileEmulatedBrowser) {
        Map<String, Object> chromeOptions = new HashMap<>();
        Map<String, String> chromeEmulationOptions = new HashMap<>();
        chromeEmulationOptions.put("deviceName", mobileEmulatedBrowser);
        chromeOptions.put("mobileEmulation", chromeEmulationOptions);

        LOG.debug("ChromeOptions: " + chromeOptions);
        LOG.debug("Chrome emulation options: " + chromeEmulationOptions);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return (null == seleniumGridHost ?
            new ChromeDriver(desiredCapabilities) :
            new RemoteWebDriver(seleniumGridHost, desiredCapabilities));
    }

    public enum BrowserType {
        CHROME,
        FIREFOX,
        IPHONE_6_EMULATOR
    }

}
