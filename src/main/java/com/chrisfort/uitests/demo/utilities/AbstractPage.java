package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by cpfort on 8/25/17.
 */
public class AbstractPage {

    private WebDriver driver;

    @Value("${redirect.timeout.seconds}")
    private int redirectTimeout;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    // Helper methods for interacting with Page Objects

    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, (long)redirectTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
