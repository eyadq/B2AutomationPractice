package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class _3_Autocomplete {

    public static void run() throws InterruptedException {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/autocomplete.html");

        WebElement header = driver.findElement(By.tagName("h3"));
        HelperMethods.logPrintMatch("Autocomplete header text", header.getText(), VercelConstants.AUTOCOMPLETE_HEADER);

        WebElement paragraph = driver.findElement(By.xpath("//p[text()='Start typing:']"));
        HelperMethods.logPrintMatch("Autocomplete parapgraph text", paragraph.getText(), VercelConstants.AUTOCOMPLETE_PARAGRAPH);

        WebElement input = driver.findElement(By.id("myCountry"));
        HelperMethods.logPrintMatch("placeholder text for input", input.getAttribute("placeholder"), VercelConstants.AUTOCOMPLETE_PLACEHOLDER_COUNTRY);

        WebElement autocompleteList;
        autocompleteList = refreshAutoCompletelist(driver);

        List<WebElement> listItems;

        listItems = sendText("Palest", input, driver);
        HelperMethods.logPrintMatch("Autocomplete options", listItems.get(0).getText(), VercelConstants.AUTOCOMPLETE_OPTIONS_SINGLE );

        listItems = sendText("united", input, driver);
        String[] autoItems = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            autoItems[i] = listItems.get(i).getText();
        }
        HelperMethods.logPrintMatch("Autocomplete options", autoItems, VercelConstants.AUTOCOMPLETE_OPTIONS_MULTIPLE);

    }

    public static List<WebElement> sendText(String str, WebElement inputField, WebDriver driver) throws InterruptedException {
        inputField.clear();
        inputField.sendKeys(str);
        Thread.sleep(1000);

        WebElement autocompleteList = refreshAutoCompletelist(driver);
        return autocompleteList.findElements(By.tagName("div"));
    }

    public static WebElement refreshAutoCompletelist(WebDriver driver){
        WebElement webElement = null;
        try{
            webElement = driver.findElement(By.id("myCountryautocomplete-list"));
        } catch (Exception e){
            System.out.println("NOTE: The autocomplete list does not exist at this time");
        }
         return webElement;
    }

    public static void main(String[] args) throws InterruptedException {
        run();
    }
}
