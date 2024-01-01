package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
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
        Driver.getDriver().get(pageURL);
        WebElement linkFOrBasicAuth = Driver.getDriver().findElement(By.linkText("Basic Auth"));
        linkFOrBasicAuth.click();
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.sendKeys(USERNAME);
        alert.accept();
        alert.sendKeys(PASSWORD);
        alert.accept();

        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        WebElement paragraph = Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'Congratulations')]"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);
    }
}
