package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class _10_DragAndDrop extends _$_VercelTestBase {

    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/drag-and-drop.html";
    public static final String HEADER_TEXT = "Drag and Drop";

    @Test
    public void testHeader(){
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Drag and drop header text" + MESSAGE_MATCH);
    }

    @Test
    public void testDraggableBoxes() {
        List<WebElement> boxes = driver.findElements(By.cssSelector("div[class='column']"));
        Assert.assertEquals(boxes.get(0).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(boxes.get(0), boxes.get(1)).build().perform();

        boxes = driver.findElements(By.cssSelector("div[class='column']"));
        Assert.assertEquals(boxes.get(0).getText(), "B", "Placement of B box after drag and drop" + MESSAGE_MATCH);
        Assert.assertEquals(boxes.get(1).getText(), "A", "Placement of A box after drag and drop" + MESSAGE_MATCH);
    }

    @BeforeMethod
    public void setUpMethod() {
        driver = WebDriverFactory.getDriver(BROWSER);
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod() {
        //driver.quit();
    }
}
