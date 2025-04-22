package swaglabs.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private ElementActions() {
    }

    // By Selectors
    public static void sendDataBy(WebDriver driver, By locator, String data) {
        Waits.waitForElementVisibleBy(driver, locator);
        Scrolling.scrollToElementBy(driver, locator);
        findElement(driver, locator).sendKeys(data);
    }

    public static void clickElementBy(WebDriver driver, By locator) {
        Waits.waitForElementVisibleBy(driver, locator);
        Scrolling.scrollToElementBy(driver, locator);
        findElement(driver, locator).click();
    }

    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisibleBy(driver, locator);
        Scrolling.scrollToElementBy(driver, locator);
        return findElement(driver, locator).getText();
    }

    // Web Element Selectors
    public static void clickElement(WebDriver driver, WebElement element) {
        Waits.waitForElementClickable(driver, element);
        Scrolling.scrollToElement(driver, element);
        element.click();
    }

    public static String getText(WebDriver driver, WebElement element) {
        Waits.waitForElementVisible(driver, element);
        return element.getText();
    }

    public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
        try {
            Waits.waitForElementVisible(driver, element);
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not visible or not found");
            return false;
        }
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
}
