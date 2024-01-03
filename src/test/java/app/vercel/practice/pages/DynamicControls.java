package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.BrowserUtil;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DynamicControls extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/dynamic-controls.html";
    private static final String HEADER_TEXT = "Dynamic Controls";
    private static final String PARAGRAPH_TEXT = "This example demonstrates when elements (e.g., checkbox, input field, etc.) are changed asynchronously.";
    private static final String HEADER_REMOVE_ADD = "Remove/add";
    private static final String CHECKBOX_MESSAGE_ENABLED = "It's back!";
    private static final String CHECKBOX_MESSAGE_DISABLED = "It's gone!";
    private static final String HEADER_ENABLE_DISABLE = "Enable/disable";
    private static final String INPUT_MESSAGE_ENABLED = "It's enabled!";
    private static final String INPUT_MESSAGE_DISABLED = "It's disabled!";
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(xpath = "//h4[contains(text(), 'Dynamic')]")
        WebElement header;
        @FindBy(tagName = "p")
        WebElement paragraph;
        @FindBy(xpath = "//h4[contains(text(), 'Remove')]")
        WebElement checkboxExample;
        @FindBy(tagName = "checkbox")
        WebElement checkbox;
        @FindBy(xpath = "//button[text()='Remove']")
        WebElement removeButton;
        @FindBy(xpath = "//button[text()='Add']")
        WebElement addButton;
        @FindBy(xpath = "//h4[contains(text(), 'Enable')]")
        WebElement inputExample;
        @FindBy(id = "message")
        WebElement message;
        @FindBy(xpath = "//button[text()='Enable']")
        WebElement enableButton;
        @FindBy(xpath = "//button[text()='Disable']")
        WebElement disableButton;
        @FindBy(xpath = "//input[@type='text']")
        WebElement input;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static String getMessageText(){
            return Driver.getDriver().findElement(By.xpath("//p[@id='message']")).getText();
        }
        static List<WebElement> getLoadingBars(){
            return Driver.getDriver().findElements(By.xpath("//div[@id='loading']"));
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }


    @Test
    public void testText(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Text of message after disabling checkbox" + MESSAGE_MATCH);
        Assert.assertEquals(factory.paragraph.getText(), PARAGRAPH_TEXT, "Text of message after disabling checkbox" + MESSAGE_MATCH);
        Assert.assertEquals(factory.checkboxExample.getText(), HEADER_REMOVE_ADD, "Text of message after disabling checkbox" + MESSAGE_MATCH);
        Assert.assertEquals(factory.inputExample.getText(), HEADER_ENABLE_DISABLE, "Text of message after disabling checkbox" + MESSAGE_MATCH);
    }
    @Test
    public void testCheckBoxExample(){
        Driver.getDriver().get(pageURL);

         factory.removeButton.click();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        Assert.assertFalse(BrowserUtil.doesElementExist(factory.checkbox), "New checkbox should not appear yet");

        Assert.assertEquals(factory.getMessageText(), CHECKBOX_MESSAGE_DISABLED, "Text of message after disabling checkbox" + MESSAGE_MATCH);

        factory.addButton.click();
        factory.checkbox = wait.until(d-> Driver.getDriver().findElement(By.xpath("//input[@type='checkbox']")));
        Assert.assertTrue(BrowserUtil.doesElementExist(factory.checkbox), "Checkbox should be back so method should return true");

        Assert.assertEquals(factory.message.getText(), CHECKBOX_MESSAGE_ENABLED, "Text of message after re-enabling checkbox" + MESSAGE_MATCH);

        List<WebElement> loadingBars = factory.getLoadingBars();
        for (WebElement loadingBar : loadingBars){
            Assert.assertFalse(loadingBar.isDisplayed(), "All loading bars should be hidden at this point yet exist in the DOM");
        }
    }

    @Test
    public void testInputExample(){
        Assert.assertFalse(factory.input.isEnabled(), "Input should be disabled before clicking enable");

        factory.enableButton.click();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(d-> factory.input.isEnabled());
        Assert.assertTrue(factory.input.isEnabled(), "Input should be enabled after clicking enable");
        Assert.assertEquals(factory.getMessageText(), INPUT_MESSAGE_ENABLED, "Text of message after enabling input" + MESSAGE_MATCH);

        factory.disableButton.click();
        wait.until(d-> !factory.input.isEnabled());
        Assert.assertEquals(factory.getMessageText(), INPUT_MESSAGE_DISABLED, "Text of message after disabling input" + MESSAGE_MATCH);

        List<WebElement> loadingBars = factory.getLoadingBars();
        for (WebElement loadingBar : loadingBars){
            Assert.assertFalse(loadingBar.isDisplayed(), "All loading bars should be hidden at this point yet exist in the DOM");
        }
    }
}
