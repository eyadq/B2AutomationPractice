package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class _1_NoABTest extends _$_VercelTestBase {


    //No A/B Test
    public static final String HEADER_TEXT = "No A/B Test";
    public static final String PARAGRAPH_TEXT = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn from different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";
    WebDriver driver;


     String pageURL = ("https://loopcamp.vercel.app/ab-test.html");


    @Test
    public void testHeader(){
        WebElement noABHeader = driver.findElement(By.tagName("h3"));
        //HelperMethods.logPrintMatch(noABHeader.getText(), VercelConstants.NoAB_HEADER, "NO A/B Test header text");
        Assert.assertEquals(noABHeader.getText(), HEADER_TEXT, "NO A/B Test header text" + MESSAGE_MATCH);
    }

    @Test
    public void testBody (){
        WebElement noABBody = driver.findElement(By.cssSelector("div[class='example']>p"));
        //HelperMethods.logPrintMatch(noABBody.getText(), VercelConstants.NoAB_BODY, "No AB Test body text");
        Assert.assertEquals(noABBody.getText(), PARAGRAPH_TEXT, "NO A/B Test body text" + MESSAGE_MATCH);

    }

    @BeforeClass
    public void setUp(){

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver(BROWSER);
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
