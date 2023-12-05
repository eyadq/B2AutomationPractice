package com.loop.test.vercel;

import com.loop.test.utilities.*;
import com.loop.test.utilities.constants.Messages;
import com.loop.test.utilities.constants.VercelConstants;
import com.loop.test.utilities.constants.VercelConstantsLong;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class _0_Home {


    WebDriver driver;
    String pageURL = "https://loopcamp.vercel.app/index.html";


    @Test
    public void testHeaderImageLoop(){
        WebElement logoLoop = driver.findElement(By.xpath("//img[@src='./img/logo.svg']"));
        String imagePathLoopActual = logoLoop.getAttribute("src");;
        //HelperMethods.logPrintMatch(imagePathLoopActual, VercelConstants.IMAGE_URL_LOGO_LOOP, "loop image link");
        Assert.assertEquals(imagePathLoopActual, VercelConstants.IMAGE_URL_LOGO_LOOP, ":oop image link" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testHeaderImageAcademy(){
        WebElement logoAcademy= driver.findElement(By.xpath("//img[@src='./img/logo-text.svg']"));
        String imagePathAcademyActual = logoAcademy.getAttribute("src");
        //HelperMethods.logPrintMatch(imagePathAcademyActual, VercelConstants.IMAGE_URL_LOGO_ACADEMY, "academy image link");
        Assert.assertEquals(imagePathAcademyActual, VercelConstants.IMAGE_URL_LOGO_ACADEMY, "Academy image link" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testHeaderAutomationPractice(){
        WebElement testAutomationPractice = driver.findElement(By.cssSelector("span[class='h1y']"));
        //HelperMethods.logPrintMatch(testAutomationPractice.getText(), VercelConstants.MAIN_HEADER_TEST_AUTOMATION_PRACTICE, "test automation practice header text");
        Assert.assertEquals(testAutomationPractice.getText(), VercelConstants.MAIN_HEADER_TEST_AUTOMATION_PRACTICE, "Test automation practice header text" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testHeaderAvailableExamples(){
        WebElement availableExamples = driver.findElement(By.cssSelector("span[class='h2 mb-4']"));
        //HelperMethods.logPrintMatch(availableExamples.getText(), VercelConstants.MAIN_HEADER_AVAILABLE_EXAMPLES, "available practice header text");
        Assert.assertEquals(availableExamples.getText(), VercelConstants.MAIN_HEADER_AVAILABLE_EXAMPLES,  "Available practice header text" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testBodyContent(){
        WebElement unorderedList = driver.findElement(By.cssSelector("ul[class='list-group list-group-flush']"));
        //HelperMethods.logPrintMatch(unorderedList.getText(), VercelConstantsLong.HOME_BODY_CONTENT, "Text of home page content");
        Assert.assertEquals(unorderedList.getText(), VercelConstantsLong.HOME_BODY_CONTENT, "Text of home page content" + Messages.MESSAGE_MATCH);
    }

    @Test
    public void testLinks() {
        List<WebElement> listItems = driver.findElements(By.className("list-group-item"));
        List<WebElement> itemLinks = driver.findElements(By.cssSelector("li[class='list-group-item']>a"));
        String actual = "";
        for (int i = 0; i < listItems.size(); i++) {
            actual += "\n\t" + listItems.get(i).getText() + " has link of " + itemLinks.get(i).getAttribute("href");
        }
            Assert.assertEquals(actual, VercelConstantsLong.HOME_LINKS, "Links for practice items" + Messages.MESSAGE_MATCH);

            Random random = new Random();
            int number = random.nextInt(50);
            String expectedURL = itemLinks.get(number).getAttribute("href");
            itemLinks.get(number).click();

            String actualURL = driver.getCurrentUrl();
            //HelperMethods.logPrintMatch(actualURL, expectedURL, "Link of item clicked on randomly");
            Assert.assertEquals(actualURL, expectedURL);


    }

    @Test
    public void testFooterImageText () {
        WebElement poweredByLoopcamp = driver.findElement(By.cssSelector("div[style='text-align: center;margin-bottom: 40px']"));
        //HelperMethods.logPrintMatch(poweredByLoopcamp.getText(), VercelConstants.MAIN_FOOTER_POWERED_BY_LOOPCAMP, "powered by loopcamp footer text");
        Assert.assertEquals(poweredByLoopcamp.getText(), VercelConstants.MAIN_FOOTER_POWERED_BY_LOOPCAMP, "Powered by loopcamp footer text" + Messages.MESSAGE_MATCH);
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
