package com.chrisfort.uitests.demo.utilities;

import com.chrisfort.uitests.demo.CONFIG.ApplicationConfiguration;
import com.chrisfort.uitests.demo.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by cpfort on 8/25/17.
 */
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Listeners({ReportsListener.class, ScreenshotTestListener.class})
@TestExecutionListeners(listeners = {TestExecutionWithDriverListener.class,
    DependencyInjectionTestExecutionListener.class},
    mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS)
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);

    @Value("${report.screenshots.location}")
    public String reportScreenshotsLocation;

    @Value("${report.location}")
    public String reportLocation;

    protected HomePage homePage;

    private WebDriver driver;

    @Value("${selenium.grid.host}")
    private URL seleniumGridHost;

    private String chromeBinaryPath;

    private String defaultBrowser;

    private WebDriverFactory webDriverFactory;

    private String browserName;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    @Autowired
    public void setWebDriverFactory(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }

    @BeforeClass
    @Parameters("browser")
    public void init(
        @Optional("CHROME")
            String browser) throws MalformedURLException {

        LOG.info("Creating an instance of the {} browser", browser);

        setBrowserName(((RemoteWebDriver) driver).getCapabilities().getBrowserName());

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        setHomePage(driver);

    }

    @Autowired
    protected void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    private void setHomePage(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    @AfterSuite
    public void teardown() {
        driver().quit();
    }

    public WebDriver driver() {
        return this.driver;
    }
}
