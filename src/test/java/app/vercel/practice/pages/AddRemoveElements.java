package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class AddRemoveElements extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/add-remove-elements.html";
    private static final String HEADER_TEXT = "Add/Remove Elements";
    private static final String BUTTON_TEXT = "Add Element";

    InnerPageFactory factory;
    Random random;

    class InnerPageFactory {
        @FindBy(tagName = "h3")
        static WebElement addRemoveHeader;

        @FindBy(css = "button[class='btn btn-primary")
        static WebElement addButton;

        InnerPageFactory(){PageFactory.initElements(Driver.getDriver(), this);}

        static List<WebElement> getDeleteButtons(){
            return Driver.getDriver().findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
        random = new Random();
    }

    @Test
    public void testHeader(){
        Assert.assertEquals(InnerPageFactory.addRemoveHeader.getText(), HEADER_TEXT, "Add Remove Header text");
    }

    @Test
    public void testAddRemove(){
        Assert.assertEquals(InnerPageFactory.addButton.getText(), BUTTON_TEXT, "Add button text" + MESSAGE_MATCH);

        Assert.assertEquals(InnerPageFactory.getDeleteButtons().size(), 0, "Number of delete buttons by default" + MESSAGE_MATCH);

        int numberOfTimesToClickAdd = random.nextInt(9) + 1; //from 1 to 10
        for (int i = 0; i < numberOfTimesToClickAdd; i++)
            InnerPageFactory.addButton.click();
        Assert.assertEquals(InnerPageFactory.getDeleteButtons().size(), numberOfTimesToClickAdd, "Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)" + MESSAGE_MATCH);

        InnerPageFactory.getDeleteButtons().get(random.nextInt(numberOfTimesToClickAdd)).click(); //clicks single delete button at random
        List<WebElement> deleteButtons = InnerPageFactory.getDeleteButtons();
        int numberOfDeleteButtonsLeft = 0;
        for (int i = 0; i < deleteButtons.size(); i++) {
            if(deleteButtons.get(i).isDisplayed()){
                numberOfDeleteButtonsLeft++;
            }
        }
        Assert.assertEquals(deleteButtons.size(), numberOfTimesToClickAdd - 1, "number of delete buttons left" + MESSAGE_MATCH);
    }
}
