package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(css = "div[id='hot-spot']")
        WebElement bigBox;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static List<WebElement> getParagraphs(){
            return Driver.getDriver().findElements(By.xpath("//div[@class='example']//following-sibling::p"));
        }
    }
    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }
    @Test
    public void testText(){
        Assert.assertEquals(factory.header.getText(), CONTEXT_MENU_HEADER, "Contextmenu paragraph text " + MESSAGE_MATCH);

        for (int i = 0; i < factory.getParagraphs().size(); i++)
            Assert.assertEquals(factory.getParagraphs().get(i).getText(), CONTEXT_MENU_PARAGRAPHS[i], "paragraph text " + i + MESSAGE_MATCH);

    }

    @Test
    public void testContextMenu() {
        new Actions(Driver.getDriver()).contextClick(factory.bigBox).perform();
        Driver.getDriver().switchTo().alert().accept();
    }
}
