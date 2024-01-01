package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicContent extends VercelTestBase {

    private static final String pageURL = ("https://loopcamp.vercel.app/dynamic-content.html");
    private static final String HEADER_TEXT = "Dynamic Content";
    private static final String PARAGRAPH_ABOVE_TEXT = "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.";
    private static final String PARAGRAPH_BELOW_TEXT = "To make some of the content static append ?with_content=static or click here.";
    private static final String IMAGE_SOURCE = "https://picsum.photos/200/300?random=";



    @Test
    public void testText(){
        Driver.getDriver().get(pageURL);

        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        WebElement aboveParagraph = Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'This example')]"));
        Assert.assertEquals(aboveParagraph.getText(), PARAGRAPH_ABOVE_TEXT, "Text of above paragraph" + MESSAGE_MATCH);
        WebElement belowParagraph = Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'To make')]"));
        Assert.assertEquals(belowParagraph.getText(), PARAGRAPH_BELOW_TEXT, "Text of below paragraph" + MESSAGE_MATCH);
    }

    @Test
    public void testContent(){
        Driver.getDriver().get(pageURL);

        List<WebElement> rowsOfContent = Driver.getDriver().findElements(By.xpath("//div[@class='example']//child::div[@id='content']//child::div[@class='row']"));

        List<WebElement> images = new ArrayList<>();
        List<WebElement> paragraphs = Driver.getDriver().findElements(By.xpath("//img[@src]//parent::div//following-sibling::div"));
        for (int i = 0; i < rowsOfContent.size(); i++) {

            images.add(rowsOfContent.get(i).findElement(By.tagName("img")));

            Assert.assertEquals(images.get(i).getAttribute("src"), IMAGE_SOURCE+(i+1), "Image source " + i + " source link" + MESSAGE_MATCH);
            Assert.assertTrue(paragraphs.get(i).getText().length()>10, "Whole sentence loaded as parapgraph");
        }


    }


}
