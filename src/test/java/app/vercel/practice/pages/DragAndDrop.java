package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DragAndDrop extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/drag-and-drop.html";
    private static final String HEADER_TEXT = "Drag and Drop";

    @Test
    public void testHeader(){
        driver.get(pageURL);
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Drag and drop header text" + MESSAGE_MATCH);
    }

    @Test
    public void testDraggableBoxes() {
        driver.get(pageURL);
        List<WebElement> boxes = driver.findElements(By.cssSelector("div[class='column']"));
        Assert.assertEquals(boxes.get(0).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(boxes.get(0), boxes.get(1)).perform();

        boxes = driver.findElements(By.cssSelector("div[class='column']"));
        Assert.assertEquals(boxes.get(0).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
    }
}
