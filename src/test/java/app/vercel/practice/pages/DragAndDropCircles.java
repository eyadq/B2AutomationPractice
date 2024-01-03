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
import java.util.concurrent.TimeUnit;

public class DragAndDropCircles extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/drag-and-drop-circles.html";
    private static final String BEFORE_ACTION = "Drag the small circle here.";
    private static final String DURING_ACTION_OFF_TARGET = "Drop here.";
    private static final String DURING_ACTION_ON_TARGET = "Now drop...";
    private static final String AFTER_ACTION = "You did great!";
    private static final String WRONG_ACTION = "Try again!";
    InnerPageFactory factory;
    Actions actions;
    class InnerPageFactory{
        @FindBy(id = "page-footer")
        WebElement footer;
        @FindBy(id = "droptarget")
        WebElement droptarget;
        @FindBy(id = "draggable")
        WebElement draggable;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
        actions = new Actions(Driver.getDriver());
    }
    @Test
    public void test(){
        Assert.assertEquals(factory.droptarget.getText(), BEFORE_ACTION, "Target text before action" + MESSAGE_MATCH);
        Assert.assertEquals(factory.droptarget.getAttribute("class"), "k-header", "Class name of target when not blue" + MESSAGE_MATCH);

        actions.dragAndDrop(factory.draggable, factory.footer).perform();
        Assert.assertEquals(factory.droptarget.getText(), WRONG_ACTION, "Target text after wrong action" + MESSAGE_MATCH);

        actions.clickAndHold(factory.draggable).moveToLocation(200, 200).perform();
        Assert.assertEquals(factory.droptarget.getText(), DURING_ACTION_OFF_TARGET, "Target text during action" + MESSAGE_MATCH);

        actions.clickAndHold(factory.draggable).moveToElement(factory.droptarget).perform();
        Driver.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assert.assertEquals(factory.droptarget.getText(), DURING_ACTION_ON_TARGET, "Target text during action" + MESSAGE_MATCH);
        Assert.assertEquals(factory.droptarget.getAttribute("class"), "k-header painted", "Class name of target when blue" + MESSAGE_MATCH);

        actions.dragAndDrop(factory.draggable, factory.droptarget).perform();
        Assert.assertEquals(factory.droptarget.getText(), AFTER_ACTION, "Target text after action" + MESSAGE_MATCH);
        Assert.assertEquals(factory.droptarget.getAttribute("class"), "k-header painted", "Class name of target when blue" + MESSAGE_MATCH);
    }
}
