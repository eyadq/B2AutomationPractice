package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DragAndDrop extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/drag-and-drop.html";
    private static final String HEADER_TEXT = "Drag and Drop";
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(id = "column-a")
        WebElement boxA;
        @FindBy(id = "column-b")
        WebElement boxB;
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
    public void testHeader(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Drag and drop header text" + MESSAGE_MATCH);
    }

    @Test
    public void testDraggableBoxes() {
        List<WebElement> boxes = factory.getBoxes();
        Assert.assertEquals(boxes.get(0).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);

        new Actions(Driver.getDriver()).dragAndDrop(factory.boxA, factory.boxB).perform();

        boxes = factory.getBoxes();
        Assert.assertEquals(boxes.get(0).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
    }
}
