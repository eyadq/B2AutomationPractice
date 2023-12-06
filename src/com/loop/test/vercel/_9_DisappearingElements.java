package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class _9_DisappearingElements extends _$_VercelTestBase {

    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/disappearing-elements.html";

    public static final String HEADER_TEXT = "Disappearing Elements";
    public static final String PARAGRAPH_TEXT = "This example demonstrates when elements on a page change by disappearing/reappearing on each page load.";
    public static final String HOME = "https://loopcamp.vercel.app/index.html";
    public static final String ABOUT = "https://loopcamp.vercel.app/about/index.html";
    public static final String CONTACT_US = "https://loopcamp.vercel.app/contact-us/index.html";
    public static final String PORTFOLIO = "https://loopcamp.vercel.app/portfolio/index.html";
    public static final String GALLERY = "https://loopcamp.vercel.app/gallery/index.html";

    @Test
    public void testText(){
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);

        WebElement paragraph = driver.findElement(By.xpath("//div[@class='example']//p"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);
    }

    @Test
    public void testDisappearingElements(){
        List<WebElement> buttons = driver.findElements(By.xpath("//li[@class='random-item']//a"));

        for (WebElement button : buttons){
            switch(button.getText()){
                case "Home":
                    Assert.assertEquals(button.getAttribute("href"), HOME, "Link of Home button" + MESSAGE_MATCH);
                    break;
                case "About":
                    Assert.assertEquals(button.getAttribute("href"), ABOUT, "Link of About button" + MESSAGE_MATCH);
                    break;
                case "Contact Us":
                    Assert.assertEquals(button.getAttribute("href"), CONTACT_US, "Link of Contact Us button" + MESSAGE_MATCH);
                    break;
                case "Portfolio":
                    Assert.assertEquals(button.getAttribute("href"), PORTFOLIO, "Link of Portfolio button" + MESSAGE_MATCH);
                    break;
                case "Gallery":
                    Assert.assertEquals(button.getAttribute("href"), GALLERY, "Link of Gallery button" + MESSAGE_MATCH);
                    break;
            }
        }

    }

    @BeforeMethod
    public void setUpMethod() {
        driver = WebDriverFactory.getDriver(BROWSER);
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
