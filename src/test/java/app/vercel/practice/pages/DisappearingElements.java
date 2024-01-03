package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DisappearingElements extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/disappearing-elements.html";
    private static final String HEADER_TEXT = "Disappearing Elements";
    private static final String PARAGRAPH_TEXT = "This example demonstrates when elements on a page change by disappearing/reappearing on each page load.";
    private static final String HOME = "https://loopcamp.vercel.app/index.html";
    private static final String ABOUT = "https://loopcamp.vercel.app/about/index.html";
    private static final String CONTACT_US = "https://loopcamp.vercel.app/contact-us/index.html";
    private static final String PORTFOLIO = "https://loopcamp.vercel.app/portfolio/index.html";
    private static final String GALLERY = "https://loopcamp.vercel.app/gallery/index.html";
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static List<WebElement> getBoxes(){
            return Driver.getDriver().findElements(By.cssSelector("div[class='column']"));
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }

    @Test
    public void testText(){
        Driver.getDriver().get(pageURL);

        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);

        WebElement paragraph = Driver.getDriver().findElement(By.xpath("//div[@class='example']//p"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);
    }

    @Test
    public void testDisappearingElements(){
        Driver.getDriver().get(pageURL);

        List<WebElement> buttons = Driver.getDriver().findElements(By.xpath("//li[@class='random-item']//a"));

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
}
