package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class _4_BasicAuth extends _$_VercelTestBase {

    public static void run(WebDriver driver){
        driver.get("https://loopcamp.vercel.app/index.html/basic-auth.html");

    }

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        run(driver);
    }
}
