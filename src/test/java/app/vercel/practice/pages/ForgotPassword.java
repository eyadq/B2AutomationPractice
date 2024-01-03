package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.BrowserUtil;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ForgotPassword extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/forgot-password.html";
    private static final String HEADER_TEXT = "Forgot Password";
    private static final String EMAIL_TEXT = "E-mail";
    private static final String RETRIEVE_PASSWORD_BUTTON_TEXT = "Retrieve password";
    private static final String ERROR_MESSAGE = "Invalid email";

    public static final String EMAIL_VALID = "jdoe@gmail.com";
    public static final String EMAIL_INVALID = "jdoegmail.com";
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h2")
        WebElement header;
        @FindBy(css = "label[for='email']")
        WebElement labelEmail;
        @FindBy(id = "email")
        WebElement input;
        @FindBy(id = "form_submit")
        WebElement button;
        @FindBy(className = "alert")
        WebElement message;
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
    public void testText(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Forgot password header text" + MESSAGE_MATCH);
        Assert.assertEquals(factory.labelEmail.getText(), EMAIL_TEXT, "Email label text" + MESSAGE_MATCH);
        Assert.assertEquals(factory.button.getText(), RETRIEVE_PASSWORD_BUTTON_TEXT, "Text of retrieve password button" + MESSAGE_MATCH);
    }
    @Test
    public void testFieldAndButton(){
        factory.input.sendKeys(EMAIL_INVALID);
        factory.button.click();
        Assert.assertEquals(factory.message.getText(), ERROR_MESSAGE, "Invalid email error message text" + MESSAGE_MATCH);

        factory.input.sendKeys(EMAIL_VALID);
        factory.button.click();
        boolean messageStillExists = true;
        try{
            BrowserUtil.implicitWait(0);
            factory.message.getText();
        } catch (Exception e){
            messageStillExists = false;
        }
        Assert.assertFalse(messageStillExists, "Message should not exist with valid email input");
    }
}
