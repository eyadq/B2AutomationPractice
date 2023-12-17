package app.vercel.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class DragAndDropCircles extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/drag-and-drop-circles.html";
    private static final String BEFORE_ACTION = "Drag the small circle here.";
    private static final String DURING_ACTION_OFF_TARGET = "Drop here.";
    private static final String DURING_ACTION_ON_TARGET = "Now drop...";
    private static final String AFTER_ACTION = "You did great!";
    private static final String WRONG_ACTION = "Try again!";
    @Test
    public void test(){
        driver.get(pageURL);

        WebElement target = driver.findElement(By.id("droptarget"));
        WebElement draggable = driver.findElement(By.id("draggable"));
        Assert.assertEquals(target.getText(), BEFORE_ACTION, "Target text before action" + MESSAGE_MATCH);
        Assert.assertEquals(target.getAttribute("class"), "k-header", "Class name of target when not blue" + MESSAGE_MATCH);

        Actions actions = new Actions(driver);

        WebElement footer = driver.findElement(By.id("page-footer"));
        actions.dragAndDrop(draggable, footer).perform();
        Assert.assertEquals(target.getText(), WRONG_ACTION, "Target text after wrong action" + MESSAGE_MATCH);

        actions.clickAndHold(draggable).moveToLocation(200, 200).perform();
        Assert.assertEquals(target.getText(), DURING_ACTION_OFF_TARGET, "Target text during action" + MESSAGE_MATCH);

        actions.clickAndHold(draggable).moveToElement(target).perform();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assert.assertEquals(target.getText(), DURING_ACTION_ON_TARGET, "Target text during action" + MESSAGE_MATCH);
        Assert.assertEquals(target.getAttribute("class"), "k-header painted", "Class name of target when blue" + MESSAGE_MATCH);

        actions.dragAndDrop(draggable, target).perform();
        Assert.assertEquals(target.getText(), AFTER_ACTION, "Target text after action" + MESSAGE_MATCH);
        Assert.assertEquals(target.getAttribute("class"), "k-header painted", "Class name of target when blue" + MESSAGE_MATCH);
    }
}
