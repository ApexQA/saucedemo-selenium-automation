package drivers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.LogsUtil;
import utilities.PropertiesUtils;

import static org.testng.Assert.fail;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager(){
        super();
    }

    @Step("Create driver instance")
    public static WebDriver createInstance(){
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        LogsUtil.info("Driver is created on:", browserName);
        setDriver(driver);
        return getDriver();
    }

    public static WebDriver getDriver(){
        if(driverThreadLocal.get() == null){
            LogsUtil.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }
}
