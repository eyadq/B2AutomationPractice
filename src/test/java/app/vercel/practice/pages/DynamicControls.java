package app.vercel.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

    @Test
    public void testText(){
        driver.get(pageURL);

        WebElement header = driver.findElement(By.xpath("//h4[contains(text(), 'Dynamic')]"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Text of message after disabling checkbox" + MESSAGE_MATCH);

        //WebElement paragraph = driver.findElement(By.tagName("//p[contains(text(), 'This')]"));
        WebElement paragraph = driver.findElement(By.tagName("p"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Text of message after disabling checkbox" + MESSAGE_MATCH);

        WebElement checkboxExample = driver.findElement(By.xpath("//h4[contains(text(), 'Remove')]"));
        Assert.assertEquals(checkboxExample.getText(), HEADER_REMOVE_ADD, "Text of message after disabling checkbox" + MESSAGE_MATCH);

        WebElement inputExample = driver.findElement(By.xpath("//h4[contains(text(), 'Enable')]"));
        Assert.assertEquals(inputExample.getText(), HEADER_ENABLE_DISABLE, "Text of message after disabling checkbox" + MESSAGE_MATCH);




    }
    @Test
    public void testCheckBoxExample(){
        driver.get(pageURL);

        WebElement checkbox = driver.findElement(By.tagName("input"));
        WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(checkbox));

        boolean newCheckBoxExists = false;
        WebElement newCheckbox = null;
        try{
            newCheckbox = driver.findElement(By.xpath("input[@type='checkbox']"));
            if(newCheckBoxExists)
                newCheckBoxExists = true;
        } catch (NoSuchElementException e){
            //the next assertFalse handles the new checkbox existing at this time
        }
        Assert.assertFalse(newCheckBoxExists, "New checkbox should not appear yet");

        WebElement message = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertEquals(message.getText(), CHECKBOX_MESSAGE_DISABLED, "Text of message after disabling checkbox" + MESSAGE_MATCH);

        button.click();
        try{
            wait.until(d-> driver.findElement(By.xpath("//input[@type='checkbox']")));
            newCheckbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        } catch (TimeoutException e){
            System.out.println("Timed out");
        } catch (NoSuchElementException e){
            System.out.println("Did not find the checkbox");
        }
        String textActual = newCheckbox.getText();
        Assert.assertEquals(newCheckbox.getAttribute("id"), "checkbox");

        message = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertEquals(message.getText(), CHECKBOX_MESSAGE_ENABLED, "Text of message after re-enabling checkbox" + MESSAGE_MATCH);

        List<WebElement> loadingBars = driver.findElements(By.xpath("//div[@id='loading']"));
        for (WebElement loadingBar : loadingBars){
            Assert.assertFalse(loadingBar.isDisplayed(), "All loading bars should be hidden at this point yet exist in the DOM");
        }
    }

    @Test
    public void testInputExample(){
        driver.get(pageURL);

        WebElement button = driver.findElement(By.xpath("//button[text()='Enable']"));
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(input.isEnabled(), "Input should be disabled before clicking enable");


        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d-> input.isEnabled());
        Assert.assertTrue(input.isEnabled(), "Input should be enabled after clicking enable");

        WebElement message = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertEquals(message.getText(), INPUT_MESSAGE_ENABLED, "Text of message after enabling input" + MESSAGE_MATCH);

        button.click();
        wait.until(d-> !input.isEnabled());
        message = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertEquals(message.getText(), INPUT_MESSAGE_DISABLED, "Text of message after disabling input" + MESSAGE_MATCH);

        List<WebElement> loadingBars = driver.findElements(By.xpath("//div[@id='loading']"));
        for (WebElement loadingBar : loadingBars){
            Assert.assertFalse(loadingBar.isDisplayed(), "All loading bars should be hidden at this point yet exist in the DOM");
        }
    }
}
