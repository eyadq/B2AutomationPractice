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

public class Checkboxes extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/checkboxes.html";
    private static final String HEADER_TEXT = "Checkboxes";
    private static final String[] CHECKBOX_LABELS = {"Checkbox 1", "Checkbox 2"};
    private static final String[] CHECKBOX_OPTIONS = {"checkbox1", "checkbox2"};
    InnerPageFactory factory;
    Actions action;
    class InnerPageFactory {
        @FindBy(tagName = "h3")
        WebElement header;
        InnerPageFactory(){PageFactory.initElements(Driver.getDriver(), this);}

        static List<WebElement> getCheckboxes(){
            return Driver.getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        }
        static List<WebElement> getCheckboxText(){
            return Driver.getDriver().findElements(By.cssSelector("span[class='checktext"));
        }
    }
    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
        action = new Actions(Driver.getDriver());
    }

    @Test
    public void testText(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Checkboxes header text" + MESSAGE_MATCH);

        for (int i = 0; i < factory.getCheckboxText().size(); i++)
            Assert.assertEquals(factory.getCheckboxText().get(i).getText(), CHECKBOX_LABELS[i], "Checkbox label " + i + " " + MESSAGE_MATCH);

        for (int i = 0; i < factory.getCheckboxes().size(); i++)
            Assert.assertEquals(factory.getCheckboxes().get(i).getAttribute("name"), CHECKBOX_OPTIONS[i], "checkbox options for " + i + " " + MESSAGE_MATCH);

    }

    @Test
    public void testCheckboxesFunctionality(){
        Assert.assertEquals(factory.getCheckboxes().get(0).isSelected(), false, "checkbox 1 selected by default" + MESSAGE_MATCH);
        Assert.assertEquals(factory.getCheckboxes().get(1).isSelected(), true, "checkbox 2 selected by default" + MESSAGE_MATCH);


        factory.getCheckboxes().get(0).click();
        Assert.assertEquals(factory.getCheckboxes().get(0).isSelected(), true, "checkbox 1 is selected after click command" + MESSAGE_MATCH);

        action.moveToElement(factory.getCheckboxes().get(1)).click().perform();
        Assert.assertEquals(factory.getCheckboxes().get(1).isSelected(), false, "checkbox 2 is deselected after emulated mouse click" + MESSAGE_MATCH);


    }
}
