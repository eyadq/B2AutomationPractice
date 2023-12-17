package app.vercel.practice.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverUtil {

    public static WebDriver getDriver(String browserType){
        browserType = browserType.toLowerCase().trim();

        switch (browserType){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            default:
                System.out.println("No driver found.\nDriver is null");
                return null;
        }
    }
}
