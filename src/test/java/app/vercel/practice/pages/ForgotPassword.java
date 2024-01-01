package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPassword extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/forgot-password.html";
    private static final String HEADER_TEXT = "Forgot Password";
    private static final String EMAIL_TEXT = "E-mail";
    private static final String EMAIL_INPUT_ID = "email";
    private static final String RETRIEVE_PASSWORD_BUTTON_TEXT = "Retrieve password";
    private static final String ERROR_MESSAGE = "Invalid email";


    @Test
    public void testHeader(){
        Driver.getDriver().get(pageURL);

        WebElement forgotPasswordHeader = Driver.getDriver().findElement(By.tagName("h2"));
        Assert.assertEquals(forgotPasswordHeader.getText(), HEADER_TEXT, "Forgot password header text" + MESSAGE_MATCH);
    }

    @Test
    public void testParagraph(){
        Driver.getDriver().get(pageURL);

        WebElement emailLabel = Driver.getDriver().findElement(By.cssSelector("label[for='email']"));
        Assert.assertEquals(emailLabel.getText(), EMAIL_TEXT, "Email label text" + MESSAGE_MATCH);
    }
    @Test
    public void testFieldAndButton(){
        Driver.getDriver().get(pageURL);

        WebElement emailInput = Driver.getDriver().findElement(By.cssSelector("input[name='email']"));
        Assert.assertEquals(emailInput.getAttribute("id"), EMAIL_INPUT_ID, "Email input attribute" + MESSAGE_MATCH);

        WebElement button = Driver.getDriver().findElement(By.cssSelector("button[type='submit']"));
        Assert.assertEquals(button.getText(), RETRIEVE_PASSWORD_BUTTON_TEXT, "Text of retrieve password button" + MESSAGE_MATCH);

        emailInput.sendKeys(EMAIL_INVALID);
        button.click();

        WebElement badEmailAlert = Driver.getDriver().findElement(By.cssSelector("div[class='alert alert-danger'"));
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
}
