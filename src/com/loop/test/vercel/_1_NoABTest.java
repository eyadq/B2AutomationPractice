package com.loop.test.vercel;

import com.loop.test.utilities.constants.Messages;
import com.loop.test.utilities.constants.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class _1_NoABTest {


    WebDriver driver;


     String pageURL = ("https://loopcamp.vercel.app/ab-test.html");

    @Test
    public void testHeader(){
        WebElement noABHeader = driver.findElement(By.tagName("h3"));
        //HelperMethods.logPrintMatch(noABHeader.getText(), VercelConstants.NoAB_HEADER, "NO A/B Test header text");
        Assert.assertEquals(noABHeader.getText(), VercelConstants.NoAB_HEADER, "NO A/B Test header text" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testBody (){
        WebElement noABBody = driver.findElement(By.cssSelector("div[class='example']>p"));
        //HelperMethods.logPrintMatch(noABBody.getText(), VercelConstants.NoAB_BODY, "No AB Test body text");
        Assert.assertEquals(noABBody.getText(), VercelConstants.NoAB_BODY, "NO A/B Test body text" + Messages.MESSAGE_MATCH);

    }

    @BeforeClass
    public void setUp(){

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }

    @AfterClass
    public void tearDown(){
    }
}
