package swaglabs.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private ElementActions() {
    }

    //send keys
    public static void sendData(WebDriver driver, WebElement element, String data) {
        Waits.waitForElementVisible(driver, element);
        Scrolling.scrollToElement(driver, element);
        element.sendKeys(data);
    }

    //click
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
            Waits.waitForElementVisible(driver, element); // Ensure visibility check [[2]]
            return element.isDisplayed(); // Native Selenium method [[5]]
        } catch (Exception e) {
            System.out.println("Element not visible or not found");
            return false; // Element not visible or not found
        }
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
}
