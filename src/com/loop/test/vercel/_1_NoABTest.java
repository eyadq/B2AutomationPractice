package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class _1_NoABTest {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/ab-test.html");

        WebElement noABHeader = driver.findElement(By.tagName("h3"));
        HelperMethods.logPrintMatch("NO A/B Test header text", noABHeader.getText(), VercelConstants.NoAB_HEADER);

        WebElement noABBody = driver.findElement(By.cssSelector("div[class='example']>p"));
        HelperMethods.logPrintMatch("No AB Test body text", noABBody.getText(), VercelConstants.NoAB_BODY);




    }
}
