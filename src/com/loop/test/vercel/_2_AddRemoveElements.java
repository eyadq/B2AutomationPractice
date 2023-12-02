package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class _2_AddRemoveElements {

    public static void run(WebDriver driver) throws InterruptedException {

        driver.get("https://loopcamp.vercel.app/add-remove-elements.html");

        WebElement addRemoveHeader = driver.findElement(By.tagName("h3"));
        HelperMethods.logPrintMatch("Add Remove Header text", addRemoveHeader.getText(), VercelConstants.ADD_REMOVE_HEADER);

        WebElement addButton = driver.findElement(By.cssSelector("button[class='btn btn-primary'"));
        HelperMethods.logPrintMatch("Add button text", addButton.getText(), VercelConstants.ADD_REMOVE_ADD_BUTTON_TEXT);

        WebElement listOfDeleteButtons = driver.findElement(By.cssSelector("div[id='elements']"));

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
        HelperMethods.logPrintMatch("Number of delete buttons by default", "" + deleteButtons.size(), "0");

        //want random number up to 10 but not zero
        Random random = new Random();
        int numberOfTimesToClickAdd = random.nextInt(10);
        while(numberOfTimesToClickAdd == 0){
            numberOfTimesToClickAdd = random.nextInt(10);
        }

        for (int i = 0; i < numberOfTimesToClickAdd; i++) {
            deleteButtons = clickAddButton(addButton, deleteButtons, driver);
        }

        HelperMethods.logPrintMatch("Number of delete buttons after clicking add " + numberOfTimesToClickAdd + " time(s)",
                ""+deleteButtons.size(), ""+numberOfTimesToClickAdd);

        deleteButtons.get(random.nextInt(numberOfTimesToClickAdd)).click();
        deleteButtons = refreshDeleteButtons(driver);
        
        int numberOfDeleteButtonsLeft = 0;
        for (int i = 0; i < deleteButtons.size(); i++) {
            if(deleteButtons.get(i).isDisplayed()){
                numberOfDeleteButtonsLeft++;
            }
        }

        HelperMethods.logPrintMatch("number of delete buttons left after 1 click", ""+deleteButtons.size(), ""+(numberOfTimesToClickAdd - 1));

    }

    public static List<WebElement> clickAddButton(WebElement addButton, List<WebElement> deleteButtons, WebDriver driver){
        addButton.click();
        return deleteButtons  = driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }

    public static List<WebElement> refreshDeleteButtons(WebDriver driver){
        return driver.findElements(By.cssSelector("button[class='added-manually btn btn-secondary']"));
    }
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        run(driver);
    }
}
