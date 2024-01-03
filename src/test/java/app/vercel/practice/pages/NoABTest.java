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

public class NoABTest extends VercelTestBase {

    private static final String pageURL = ("https://loopcamp.vercel.app/ab-test.html");
    private static final String HEADER_TEXT = "No A/B Test";
    private static final String PARAGRAPH_TEXT = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn from different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";

    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(css = "div[class='example']>p")
        WebElement paragraph;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }

    @Test
    public void test(){
       Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "NO A/B Test header text" + MESSAGE_MATCH);
       Assert.assertEquals(factory.paragraph.getText(), PARAGRAPH_TEXT, "NO A/B Test body text" + MESSAGE_MATCH);
    }
}
