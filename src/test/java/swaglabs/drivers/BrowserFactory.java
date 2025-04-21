package swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.nio.file.Files;
import java.util.Map;

public class BrowserFactory {
    public static WebDriver getBrowser(String browserName) {
        switch (browserName.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                Map<String, Object> chromePrefs = Map.of("profile.default_content_setting_values.notifications", false,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile.enabled", false);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                // Create unique user data directory
                try {
                    String userDataDir = Files.createTempDirectory("chrome-user-data").toString();
                    chromeOptions.addArguments("--user-data-dir=" + userDataDir);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create temp directory", e);
                }
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.addArguments("--disable-infobars");
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--remote-allow-origins=*");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                firefoxOptions.setAcceptInsecureCerts(true);
                return new FirefoxDriver(firefoxOptions);

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                safariOptions.setAutomaticInspection(false);
                safariOptions.setAutomaticProfiling(false);
                return new SafariDriver(safariOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-extensions");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--remote-allow-origins=*");

                // Set preferences for Edge
                Map<String, Object> edgePrefs = Map.of(
                        "profile.default_content_setting_values.notifications", false,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile.enabled", false
                );
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}

