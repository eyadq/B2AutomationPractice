package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class _2_AddRemoveElements {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/add-remove-elements.html");

        WebElement addRemoveHeader = driver.findElement(By.tagName("h3"));
        HelperMethods.logPrintMatch("Add Remove Header text", addRemoveHeader.getText(), VercelConstants.ADD_REMOVE_HEADER);

        WebElement addButton = driver.findElement(By.cssSelector("button[class='btn btn-primary'"));
        HelperMethods.logPrintMatch("Add button text", addButton.getText(), VercelConstants.ADD_REMOVE_ADD_BUTTON_TEXT);

        WebElement listOfDeleteButtons = driver.findElement(By.cssSelector("div[id='elements']"));
        



    }
}
