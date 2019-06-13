package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class DriverUtils {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        Reporter.log("Opening Chrome instance", true);
        driver = new ChromeDriver();
        return driver;
    }


    public static void closeBrowser() {
        Reporter.log("Closing Chrome", true);
        driver.quit();
    }
}
