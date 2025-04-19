package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.module.FindException;

public class ElementActions {
    private ElementActions(){
    }

    //send keys
    @Step("Sending data: {data} to the element: {locator}")
    public static void sendData(WebDriver driver, By locator, String data){
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(data);
        LogsUtil.info("Data is created: ", data, "in the field: ", locator.toString());
    }

    //click
    @Step("Clicking on the element: {locator}")
    public static void clickElement(WebDriver driver, By locator){
        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
        LogsUtil.info("Clicked on the element: ", locator.toString());
    }

    @Step("Getting the test from the element: {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtil.info("Getting text from the element: ", locator.toString(), "Text: ", findElement(driver, locator).getText());
        return findElement(driver, locator).getText();
    }
    public static WebElement findElement(WebDriver driver, By locator){
        LogsUtil.info("Finding element: ", locator.toString());
        return driver.findElement(locator);
    }

    public static String getTextFromInput(WebDriver driver, By locator){
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtil.info("Getting text from the input field: " + locator.toString(), " Text: ", findElement(driver, locator).getDomAttribute("value"));
        return findElement(driver, locator).getDomAttribute("value");
    }


}
