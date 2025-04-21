package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import swaglabs.utilities.Waits;

public abstract class BasePage {
    protected WebDriver driver;

    // Constructor: Initialize WebDriver and PageFactory
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize @FindBy elements [[9]]
    }

    // Common Actions (optional: delegate to utility classes)
    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Wait Methods (use WebElement directly)
    public void waitForElementVisible(WebElement element) {
        Waits.waitForElementVisible(driver, element);
    }

    public void waitForElementClickable(WebElement element) {
        Waits.waitForElementClickable(driver, element);
    }
}