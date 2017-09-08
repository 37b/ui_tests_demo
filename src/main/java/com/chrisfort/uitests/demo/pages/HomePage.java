package com.chrisfort.uitests.demo.pages;

import com.chrisfort.uitests.demo.utilities.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by cpfort on 8/25/17.
 */
public class HomePage extends AbstractPage {

    public String homePageUrl = "https://the-internet.herokuapp.com";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Dropdown")
    public WebElement dropdownLink;

    @FindBy(id = "dropdown")
    public WebElement dropdown;

    public Select dropdownSelect() {
        return new Select(dropdown);
    }

}
