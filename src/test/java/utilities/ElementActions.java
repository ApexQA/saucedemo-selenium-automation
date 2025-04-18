package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementActions {
    private ElementActions() {
    }

    //sendkeys
    public static void sendData(WebDriver driver, By locator, String data) {

        waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).sendKeys(data);

    }

    //click
    public static void clickElement(WebDriver driver, By locator) {

        waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).click();
    }

    public static String getText(WebDriver driver, By locator) {
        waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator).getText();
    }

}

