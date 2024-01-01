package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class NoABTest extends VercelTestBase {

    private static final String pageURL = ("https://loopcamp.vercel.app/ab-test.html");
    private static final String HEADER_TEXT = "No A/B Test";
    private static final String PARAGRAPH_TEXT = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn from different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";

    @Test
    public void testHeader(){
        Driver.getDriver().get(pageURL);

        WebElement noABHeader = Driver.getDriver().findElement(By.tagName("h3"));
        //HelperMethods.logPrintMatch(noABHeader.getText(), VercelConstants.NoAB_HEADER, "NO A/B Test header text");
        Assert.assertEquals(noABHeader.getText(), HEADER_TEXT, "NO A/B Test header text" + MESSAGE_MATCH);
    }

    @Test
    public void testBody (){
        Driver.getDriver().get(pageURL);

        WebElement noABBody = Driver.getDriver().findElement(By.cssSelector("div[class='example']>p"));
        //HelperMethods.logPrintMatch(noABBody.getText(), VercelConstants.NoAB_BODY, "No AB Test body text");
        Assert.assertEquals(noABBody.getText(), PARAGRAPH_TEXT, "NO A/B Test body text" + MESSAGE_MATCH);

    }
}
