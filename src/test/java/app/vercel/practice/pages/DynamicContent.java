package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicContent extends VercelTestBase {

    private static final String pageURL = ("https://loopcamp.vercel.app/dynamic-content.html");
    private static final String HEADER_TEXT = "Dynamic Content";
    private static final String PARAGRAPH_ABOVE_TEXT = "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.";
    private static final String PARAGRAPH_BELOW_TEXT = "To make some of the content static append ?with_content=static or click here.";
    private static final String IMAGE_SOURCE = "https://picsum.photos/200/300?random=";
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(xpath = "//p[contains(text(), 'This example')]")
        WebElement aboveParagraph;
        @FindBy(xpath = "//p[contains(text(), 'To make')]")
        WebElement belowParagraph;
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
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        Assert.assertEquals(factory.aboveParagraph.getText(), PARAGRAPH_ABOVE_TEXT, "Text of above paragraph" + MESSAGE_MATCH);
        Assert.assertEquals(factory.belowParagraph.getText(), PARAGRAPH_BELOW_TEXT, "Text of below paragraph" + MESSAGE_MATCH);
    }

    @Test
    public void testContent(){
        List<WebElement> rowsOfContent = Driver.getDriver().findElements(By.xpath("//div[@class='example']//child::div[@id='content']//child::div[@class='row']"));

        List<WebElement> images = new ArrayList<>();
        List<WebElement> paragraphs = Driver.getDriver().findElements(By.xpath("//img[@src]//parent::div//following-sibling::div"));
        for (int i = 0; i < rowsOfContent.size(); i++) {

            images.add(rowsOfContent.get(i).findElement(By.tagName("img")));

            Assert.assertEquals(images.get(i).getAttribute("src"), IMAGE_SOURCE+(i+1), "Image source " + i + " source link" + MESSAGE_MATCH);
            Assert.assertTrue(paragraphs.get(i).getText().length()>10, "Whole sentence loaded as paragraph");
        }


    }


}
