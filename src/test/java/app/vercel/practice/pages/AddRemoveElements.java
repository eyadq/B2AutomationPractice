package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class AddRemoveElements extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/add-remove-elements.html";
    private static final String HEADER_TEXT = "Add/Remove Elements";
    private static final String BUTTON_TEXT = "Add Element";

    @Test
    public void testHeader(){
        Driver.getDriver().get(pageURL);
        WebElement addRemoveHeader = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(addRemoveHeader.getText(), HEADER_TEXT, "Add Remove Header text");
    }

    @Test
    public void testAddRemove(){
        Driver.getDriver().get(pageURL);
        WebElement addButton = Driver.getDriver().findElement(By.cssSelector("button[class='btn btn-primary'"));
        //HelperMethods.logPrintMatch(addButton.getText(), VercelConstants.ADD_REMOVE_ADD_BUTTON_TEXT, "Add button text");
        Assert.assertEquals(addButton.getText(), BUTTON_TEXT, "Add button text" + MESSAGE_MATCH);

        List<WebElement> deleteButtons = Driver.getDriver().findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
        //HelperMethods.logPrintMatch(deleteButtons.size(), 0, "Number of delete buttons by default");
        Assert.assertEquals(deleteButtons.size(), 0, "Number of delete buttons by default" + MESSAGE_MATCH);

        //want random number up to 10 but not zero going to add a random number of adds and click delete once
        Random random = new Random();
        int numberOfTimesToClickAdd = random.nextInt(10);
        while(numberOfTimesToClickAdd == 0){
            numberOfTimesToClickAdd = random.nextInt(10);
        }
        for (int i = 0; i < numberOfTimesToClickAdd; i++) {
            deleteButtons = clickAddButton(addButton, deleteButtons);
        }

        //HelperMethods.logPrintMatch(deleteButtons.size(), numberOfTimesToClickAdd, "Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)");
        Assert.assertEquals(deleteButtons.size(), numberOfTimesToClickAdd, "Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)" + MESSAGE_MATCH);

        deleteButtons.get(random.nextInt(numberOfTimesToClickAdd)).click();
        deleteButtons = refreshDeleteButtons();

        int numberOfDeleteButtonsLeft = 0;
        for (int i = 0; i < deleteButtons.size(); i++) {
            if(deleteButtons.get(i).isDisplayed()){
                numberOfDeleteButtonsLeft++;
            }
        }

        //HelperMethods.logPrintMatch(""+deleteButtons.size(), ""+(numberOfTimesToClickAdd - 1), "number of delete buttons left after 1 click");
        Assert.assertEquals(deleteButtons.size(), numberOfTimesToClickAdd - 1, "number of delete buttons left after 1 click" + MESSAGE_MATCH);
    }


    public static List<WebElement> clickAddButton(WebElement addButton, List<WebElement> deleteButtons){
        addButton.click();
        return deleteButtons  = Driver.getDriver().findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }

    public static List<WebElement> refreshDeleteButtons(){
        return Driver.getDriver().findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }
}
