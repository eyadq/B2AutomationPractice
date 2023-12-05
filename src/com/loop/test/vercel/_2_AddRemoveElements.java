package com.loop.test.vercel;

import com.loop.test.utilities.constants.Messages;
import com.loop.test.utilities.constants.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class _2_AddRemoveElements {
    WebDriver driver;
    String pageURL = "https://loopcamp.vercel.app/add-remove-elements.html";

    @Test
    public void testHeader(){
        WebElement addRemoveHeader = driver.findElement(By.tagName("h3"));
        //HelperMethods.logPrintMatch(addRemoveHeader.getText(), VercelConstants.ADD_REMOVE_HEADER, "Add Remove Header text");
    }

    @Test
    public void testAddRemove(){
        WebElement addButton = driver.findElement(By.cssSelector("button[class='btn btn-primary'"));
        //HelperMethods.logPrintMatch(addButton.getText(), VercelConstants.ADD_REMOVE_ADD_BUTTON_TEXT, "Add button text");
        Assert.assertEquals(addButton.getText(), VercelConstants.ADD_REMOVE_ADD_BUTTON_TEXT, "Add button text" + Messages.MESSAGE_MATCH);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
        //HelperMethods.logPrintMatch(deleteButtons.size(), 0, "Number of delete buttons by default");
        Assert.assertEquals(deleteButtons.size(), 0, "Number of delete buttons by default" + Messages.MESSAGE_MATCH);

        //want random number up to 10 but not zero going to add a random number of adds and click delete once
        Random random = new Random();
        int numberOfTimesToClickAdd = random.nextInt(10);
        while(numberOfTimesToClickAdd == 0){
            numberOfTimesToClickAdd = random.nextInt(10);
        }
        for (int i = 0; i < numberOfTimesToClickAdd; i++) {
            deleteButtons = clickAddButton(addButton, deleteButtons, driver);
        }

        //HelperMethods.logPrintMatch(deleteButtons.size(), numberOfTimesToClickAdd, "Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)");
        Assert.assertEquals(deleteButtons.size(), numberOfTimesToClickAdd, "Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)" + Messages.MESSAGE_MATCH);

        deleteButtons.get(random.nextInt(numberOfTimesToClickAdd)).click();
        deleteButtons = refreshDeleteButtons(driver);

        int numberOfDeleteButtonsLeft = 0;
        for (int i = 0; i < deleteButtons.size(); i++) {
            if(deleteButtons.get(i).isDisplayed()){
                numberOfDeleteButtonsLeft++;
            }
        }

        //HelperMethods.logPrintMatch(""+deleteButtons.size(), ""+(numberOfTimesToClickAdd - 1), "number of delete buttons left after 1 click");
        Assert.assertEquals(deleteButtons.size(), numberOfTimesToClickAdd - 1, "number of delete buttons left after 1 click" + Messages.MESSAGE_MATCH);
    }


    public static List<WebElement> clickAddButton(WebElement addButton, List<WebElement> deleteButtons, WebDriver driver){
        addButton.click();
        return deleteButtons  = driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }

    public static List<WebElement> refreshDeleteButtons(WebDriver driver){
        return driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }

    @BeforeClass
    public void setUp(){

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }

    @AfterClass
    public void tearDown(){
    }
}
