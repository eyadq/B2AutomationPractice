package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class _3_Autocomplete {

    public static void run(WebDriver driver){

       driver.get("https://loopcamp.vercel.app/autocomplete.html");

        WebElement header = driver.findElement(By.tagName("h3"));
        HelperMethods.logPrintMatch("Autocomplete header text", header.getText(), VercelConstants.AUTOCOMPLETE_HEADER);

        WebElement paragraph = driver.findElement(By.xpath("//p[text()='Start typing:']"));
        HelperMethods.logPrintMatch("Autocomplete parapgraph text", paragraph.getText(), VercelConstants.AUTOCOMPLETE_PARAGRAPH);

        WebElement input = driver.findElement(By.id("myCountry"));
        HelperMethods.logPrintMatch("placeholder text for input", input.getAttribute("placeholder"), VercelConstants.AUTOCOMPLETE_PLACEHOLDER_COUNTRY);

        WebElement autocompleteList;
        autocompleteList = refreshAutoCompleteList(driver);

        List<WebElement> listItems;

        listItems = sendText("Not_a_real_country_name", input, driver);
        HelperMethods.logPrintMatch("Invalid country search returned results", "" + listItems.size(), "0");

        listItems = sendText("Palest", input, driver);
        if(listItems.size() == 1){
            HelperMethods.logPrintMatch("Autocomplete options", listItems.get(0).getText(), VercelConstants.AUTOCOMPLETE_OPTIONS_SINGLE);
        } else {
            System.err.println("TEST FAILED => Instead of single autocomplete option \"Palestine\", there are " + listItems.size() + " options");
            if(listItems.size() !=0){
                for (WebElement element : listItems){
                    System.out.println("\t" + element.getText());
                }
            }
        }

        //Test to click on search result
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='button']"));
        submitButton.click();

        ////p[text()='You selected: Palestine']
        WebElement result = driver.findElement(By.cssSelector("p[id='result']"));

        HelperMethods.logPrintMatch("Log after clicking submit", result.getText(), VercelConstants.AUTOCOMPLETE_LOGGED_RESULT);


        listItems = sendText("united", input, driver);
        String[] actualItems = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            actualItems[i] = listItems.get(i).getText();
        }
        HelperMethods.logPrintMatch("Autocomplete options", actualItems, VercelConstants.AUTOCOMPLETE_OPTIONS_MULTIPLE);

        driver.quit();

    }

    public static WebElement refreshAutoCompleteList(WebDriver driver){
        WebElement autocompleteList = null;
        try{
            autocompleteList = driver.findElement(By.id("myCountryautocomplete-list"));
        } catch (Exception e){
            HelperMethods.logPrintMatch("Test if autocomplete exists by default", false, false);
        }
        return autocompleteList;
    }

    public static List<WebElement> sendText(String str, WebElement inputField, WebDriver driver) {
        inputField.clear();
        inputField.sendKeys(str);
        //Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement autocompleteList = refreshAutoCompleteList(driver);
        return autocompleteList.findElements(By.tagName("div"));
    }

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        run(driver);
    }
}
