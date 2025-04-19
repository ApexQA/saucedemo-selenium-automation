package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {
    private Scrolling(){
    }

    @Step("Scrolling to the element: {0}")
    public static void scrollToElement(WebDriver driver, By locator){
        LogsUtil.info("Scrolling to the element: ", locator.toString());
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView(true);", ElementActions.findElement(driver, locator));
    }
}
