package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
/**
 * Created by cpfort on 8/25/17.
 */
public class AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);

    private WebDriver driver;

    private URL seleniumGridHost;

    private String chromeBinaryPath;

    private String defaultBrowser;

    private String reportImagesLocation;
    private String reportLocation;


    @BeforeClass
    @Parameters("browser")
    public void init(@Optional("CHROME") String browser) {

    }
}
