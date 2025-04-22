package swaglabs.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10); // [[6]][[9]]

    private Waits() {
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementTextToBeNotEmpty(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT) // Consistent timeout [[9]]
                .until((ExpectedCondition<Boolean>) d -> {
                    try {
                        String text = element.getText().trim();
                        return !text.isEmpty(); // Text exists and is non-empty [[1]]
                    } catch (Exception e) {
                        return false; // Handle stale elements gracefully [[2]]
                    }
                });
    }

    public static void waitForUrlToBe(WebDriver driver, String expectedUrl) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT)
                    .until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            throw new RuntimeException("URL did not match " + expectedUrl, e);
        }
    }

    public static void waitForElementNotPresent(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator)); // [[1]][[5]]
        } catch (Exception e) {
            // Handle cases where element was never present [[9]]
            if (!driver.findElements(locator).isEmpty()) {
                throw new RuntimeException("Element still present: " + locator, e);
            }
        }
    }

    public static void waitForCartItemCountToBeLessThan(WebDriver driver, int count) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getCartItemCount(d) < count); // [[9]]
    }

    private static int getCartItemCount(WebDriver driver) {
        return driver.findElements(By.cssSelector(".cart_item")).size();
    }

    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator)); // [[4]]
    }

//    public static void waitForUrlContains(WebDriver driver, String partialUrl) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlContains(partialUrl));
//    }
}