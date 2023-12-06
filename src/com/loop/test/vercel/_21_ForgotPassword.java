package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class _21_ForgotPassword extends _$_VercelTestBase {

    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/forgot-password.html";
    public static final String FORGOT_PASSWORD_HEADER = "Forgot Password";
    public static final String EMAIL_TEXT = "E-mail";
    public static final String EMAIL_INPUT_ID = "email";
    public static final String RETRIEVE_PASSWORD_BUTTON_TEXT = "Retrieve password";
    public static final String EMAIL_VALID = "jdoe@gmail.com";
    public static final String EMAIL_INVALID = "jdoegmail.com";
    public static final String ERROR_MESSAGE = "Invalid email";


    @Test
    public void testHeader(){
        WebElement forgotPasswordHeader = driver.findElement(By.tagName("h2"));
        Assert.assertEquals(forgotPasswordHeader.getText(), FORGOT_PASSWORD_HEADER, "Forgot password header text" + MESSAGE_MATCH);
    }

    @Test
    public void testParagraph(){
        WebElement emailLabel = driver.findElement(By.cssSelector("label[for='email']"));
        Assert.assertEquals(emailLabel.getText(), EMAIL_TEXT, "Email label text" + MESSAGE_MATCH);
    }
    @Test
    public void testFieldAndButton(){
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        Assert.assertEquals(emailInput.getAttribute("id"), EMAIL_INPUT_ID, "Email input attribute" + MESSAGE_MATCH);

        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertEquals(button.getText(), RETRIEVE_PASSWORD_BUTTON_TEXT, "Text of retrieve password button" + MESSAGE_MATCH);

        emailInput.sendKeys(EMAIL_INVALID);
        button.click();

        WebElement badEmailAlert = driver.findElement(By.cssSelector("div[class='alert alert-danger'"));
        Assert.assertEquals(badEmailAlert.getText(), ERROR_MESSAGE, "Invalid email error message text" + MESSAGE_MATCH);

        emailInput.sendKeys(EMAIL_VALID);
        button.click();

        boolean errorMessageGone = false;
        try{
            badEmailAlert.getText();
        } catch (StaleElementReferenceException e){
            errorMessageGone = true;

        } finally {
            Assert.assertTrue(errorMessageGone, "Error message did not go away after inputting valid text");
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod(){
        //driver.quit();
    }
}
