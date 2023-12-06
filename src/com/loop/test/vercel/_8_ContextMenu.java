package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class _8_ContextMenu extends _$_VercelTestBase {

    //ContextMenus
    public static final String CONTEXT_MENU_HEADER = "Context Menu";
    public static final String[] CONTEXT_MENU_PARAGRAPHS = {
            "Context menu items are custom additions that appear in the right-click menu.",
            "Right-click in the box below to see one called 'the-internet'. When you click it, it will trigger a JavaScript alert."
            };
    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/context-menu.html";

    @Test
    public void testText(){
                                ////div[@class='example']//following-sibling::p
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), CONTEXT_MENU_HEADER, "Contextmenu paragraph text " + _$_VercelTestBase.MESSAGE_MATCH);

        List<WebElement> paragraphs = driver.findElements(By.xpath("//div[@class='example']//following-sibling::p"));

        for (int i = 0; i < paragraphs.size(); i++) {
            WebElement sentence = paragraphs.get(i);
            Assert.assertEquals(sentence.getText(), CONTEXT_MENU_PARAGRAPHS[i], "paragraph text " + i + _$_VercelTestBase.MESSAGE_MATCH);
        }
    }

    @Test
    public void testContextMenu() throws InterruptedException {
        WebElement theBoxThing = driver.findElement(By.cssSelector("div[id='hot-spot']"));
        //System.out.println(theBoxThing.getAttribute("contextmenu"));
        Actions action = new Actions(driver);
        action.contextClick(theBoxThing).perform();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();


    }

    @BeforeClass
    public void setUp(){

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("firefox");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod(){
        //driver.quit();
    }

    @AfterClass
    public void tearDown(){
    }
}
