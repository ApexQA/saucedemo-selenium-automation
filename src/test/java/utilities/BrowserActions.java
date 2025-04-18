package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions(){
    }

    //Navigate to URL
    @Step("Navigating to URL")
    public static void navigateToURL(WebDriver driver, String url){
        driver.get(url);
        LogsUtil.info("Navigated to URL: ", url);
    }

    //Get current URL
    @Step("Getting current URL")
    public static String getCurrentURL(WebDriver driver){
        LogsUtil.info("Current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    //Get page title
    @Step("Getting Page Title")
    public static String getPageTitle(WebDriver driver) {
        LogsUtil.info("Page Title: ", driver.getTitle());
        return driver.getTitle();
    }

    //refresh page
    @Step("Refreshing the page")
    public static void refreshPage(WebDriver driver){
        LogsUtil.info("Refreshing the page.");
        driver.navigate().refresh();
    }

    // quit the browser
    @Step("Closing the browser")
    public static void closeBrowser(WebDriver driver){
        LogsUtil.info("Closing the browser");
        driver.quit();
    }
}
