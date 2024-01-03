package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;


public class BasicAuth extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/basic-auth.html";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String HEADER_TEXT = "Basic Auth";
    private static final String PARAGRAPH_TEXT = "Congratulations! You must have the proper credentials.";
    static InnerPageFactory factory;

    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(xpath = "//p[contains(text(), 'Congratulations')]")
        WebElement paragraph;
        InnerPageFactory(){PageFactory.initElements(Driver.getDriver(), this);}

        public void login(){
            Alert alert = Driver.getDriver().switchTo().alert();
            alert.sendKeys(USERNAME);
            alert.accept();
            alert.sendKeys(PASSWORD);
            alert.accept();
        }
    }
    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }
    @Test
    public  void loginAndTestText() {
        factory.login();
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        Assert.assertEquals(factory.paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);
    }
}
