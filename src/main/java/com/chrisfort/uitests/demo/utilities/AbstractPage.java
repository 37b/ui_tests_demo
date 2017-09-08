package com.chrisfort.uitests.demo.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by cpfort on 8/25/17.
 */
public class AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);

    private WebDriver driver;

    @Value("${redirect.timeout.seconds}")
    private int redirectTimeout;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Helper methods for interacting with Page Objects

    /**
     * Waits for an element to be clickable. The time is based on the property redirect.timeout
     * .seconds
     *
     * @param element The WebElement to wait for
     */
    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, (long) redirectTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the URL to contain the given string. The time is based on the property redirect
     * .timeout.seconds
     * @param urlSubstring The string to wait for.
     */
    public void waitForUrlToContain(String urlSubstring) {
        LOG.info("Waiting for {} to appear in the URL within {} seconds.", urlSubstring,
            redirectTimeout);

        WebDriverWait wait = new WebDriverWait(driver, (long) redirectTimeout);
        wait.until(ExpectedConditions.urlContains(urlSubstring));
    }

    /**
     * Waits for an element to be visible on the page. The time is based on the property redirect
     * .timeout.seconds
     * @param element The element to wait for.
     */
    public void waitForVisibilityOf(WebElement element) {
        LOG.info("Waiting for element [{}] to be visible within {} seconds.", element.toString(),
            redirectTimeout);

        WebDriverWait wait = new WebDriverWait(driver, (long) redirectTimeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Scrolls the page to the element, if visible.
     * @param element The element to scroll to.
     * @return The element scrolled to
     */
    public WebElement scrollToElement(WebElement element) {
        String js = String.format("window.scroll)0, %s", element.getLocation().getY());

        ((JavascriptExecutor) driver).executeScript(js);
        return element;
    }
}
