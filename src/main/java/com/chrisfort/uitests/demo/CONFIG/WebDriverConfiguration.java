package com.chrisfort.uitests.demo.CONFIG;

import com.chrisfort.uitests.demo.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.net.URL;

/**
 * Created by cpfort on 8/29/17.
 */
@Configuration
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class WebDriverConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverConfiguration.class);

    @Value("${selenium.default.browser}")
    String browser;

    @Value("${selenium.default.browser}")
    WebDriverFactory.BrowserType browserType;

    @Value("${selenium.browser.language}")
    String browserLanguage;

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Autowired
    @Qualifier(value = "seleniumGridHost")
    private URL gridHostUrl;

    @Bean
    public WebDriver webDriver() {
        LOGGER.info("");
        return webDriverFactory.webDriver(gridHostUrl, browserType);
    }

//    @Bean(name = "WebDriver")
//    public WebDriver webDriver() {
//        LOGGER.info(
//            "Creating WebDriver using the following parameters:\nGrid Host = [" + gridHostUrl +
//                "]\nBrowser Type: [" + browser +"]");
//
//        return webDriverFactory.webDriver(gridHostUrl, browser);
//    }

}
