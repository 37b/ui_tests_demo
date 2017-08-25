package com.chrisfort.uitests.demo.utilities;

import com.chrisfort.uitests.demo.CONFIG.ApplicationConfiguration;
import com.chrisfort.uitests.demo.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by cpfort on 8/25/17.
 */
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests{

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);

    private WebDriver driver;

    @Value("${selenium.grid.host}")
    private String seleniumGridHost;

    private String chromeBinaryPath;

    private String defaultBrowser;

    private String reportImagesLocation;
    private String reportLocation;

    private WebDriverFactory webDriverFactory;

    protected HomePage homePage;

    private void setHomePage(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    protected void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Autowired
    public void setWebDriverFactory(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }

    public WebDriver driver() {
        return this.driver;
    }

    @BeforeClass
    @Parameters("browser")
    public void init(@Optional("CHROME") String browser) throws MalformedURLException{

        LOG.debug("Creating an instance of the {} browser", browser);

        //Will probably pull this out into another class later

        Map<String, String> options = new HashMap<>();
        options.put("chromeBinaryPath", chromeBinaryPath);

        setWebDriver(
            webDriverFactory.webDriver(
                new URL(seleniumGridHost),
            browser));

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        setHomePage(driver);

    }

    @AfterSuite
    public void teardown() {
        driver().quit();
    }
}
