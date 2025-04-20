package SwagLabs.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {
    private Scrolling(){
    }

    public static void scrollToElement(WebDriver driver, By locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ElementActions.findElement(driver,locator));
    }
}
