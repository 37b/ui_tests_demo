package com.chrisfort.uitests.demo.tests;

import com.chrisfort.uitests.demo.utilities.AbstractTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by cpfort on 8/25/17.
 */
public class HomePageTest extends AbstractTest {

    @Test(description = "first example test")
    public void testDropdownSelect() {
        driver().get(homePage.homePageUrl);
        homePage.dropdownLink.click();

        List<WebElement> dropdownOptions = homePage.dropdownSelect().getOptions();

        for (WebElement options : dropdownOptions) {
            System.err.println(options.getText());
        }


    }
}
