package app.vercel.practice.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class BasicAuth extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String HEADER_TEXT = "Basic Auth";
    private static final String PARAGRAPH_TEXT = "Congratulations! You must have the proper credentials.";
    @Test
    public  void login() {
        driver.get(pageURL);
        WebElement linkFOrBasicAuth = driver.findElement(By.linkText("Basic Auth"));
        linkFOrBasicAuth.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(USERNAME);
        alert.accept();
        alert.sendKeys(PASSWORD);
        alert.accept();

        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        WebElement paragraph = driver.findElement(By.xpath("//p[contains(text(), 'Congratulations')]"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);
    }
}
