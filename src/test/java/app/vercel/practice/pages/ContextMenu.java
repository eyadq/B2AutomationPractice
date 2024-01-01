package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContextMenu extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/context-menu.html";
    private String BROWSER = "firefox";
    private static final String CONTEXT_MENU_HEADER = "Context Menu";
    private static final String[] CONTEXT_MENU_PARAGRAPHS = {
            "Context menu items are custom additions that appear in the right-click menu.",
            "Right-click in the box below to see one called 'the-internet'. When you click it, it will trigger a JavaScript alert."
            };
    @Test
    public void testText(){
        Driver.getDriver().get(pageURL);

        ////div[@class='example']//following-sibling::p
        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), CONTEXT_MENU_HEADER, "Contextmenu paragraph text " + MESSAGE_MATCH);

        List<WebElement> paragraphs = Driver.getDriver().findElements(By.xpath("//div[@class='example']//following-sibling::p"));

        for (int i = 0; i < paragraphs.size(); i++) {
            WebElement sentence = paragraphs.get(i);
            Assert.assertEquals(sentence.getText(), CONTEXT_MENU_PARAGRAPHS[i], "paragraph text " + i + MESSAGE_MATCH);
        }
    }

    @Test
    public void testContextMenu() throws InterruptedException {
        Driver.getDriver().get(pageURL);

        WebElement theBoxThing = Driver.getDriver().findElement(By.cssSelector("div[id='hot-spot']"));
        Actions action = new Actions(Driver.getDriver());
        action.contextClick(theBoxThing).perform();

        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();


    }
}
