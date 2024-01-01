package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Autocomplete extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/autocomplete.html";
    private static final String HEADER_TEXT = "Autocomplete";
    private static final String PARAGRAPH_TEXT = "Start typing:";
    private static final String PLACEHOLDER_TEXT = "Country";
    private static final String[] AUTOCOMPLETE_OPTIONS_SINGLE = {"Palestine"};
    private static final String[] AUTOCOMPLETE_OPTIONS_MULTIPLE = {"United Arab Emirates", "United Kingdom", "United States of America"};
    private static final String LOGGED_RESULT = "You selected: Palestine";

    @Test
    public void testHeader(){
        Driver.getDriver().get(pageURL);
        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Autocomplete header text");
    }

    @Test
    public void testParagraph (){
        Driver.getDriver().get(pageURL);
        WebElement paragraph = Driver.getDriver().findElement(By.xpath("//p[text()='Start typing:']"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Autocomplete parapgraph text");
    }

    @Test
    public void testAutocomplete(){
        Driver.getDriver().get(pageURL);
        WebElement input = Driver.getDriver().findElement(By.id("myCountry"));
        Assert.assertEquals(input.getAttribute("placeholder"), PLACEHOLDER_TEXT, "placeholder text for input");

        WebElement autocompleteList;
        autocompleteList = refreshAutoCompleteList();

        List<WebElement> listItems;

        listItems = sendText("Palest", input);
        String[] actualOutputFromAutoComplete = new String[listItems.size()];
        for (int i = 0; i < actualOutputFromAutoComplete.length; i++) {
            actualOutputFromAutoComplete[i] = listItems.get(i).getText();
        }
        Assert.assertEquals(actualOutputFromAutoComplete, AUTOCOMPLETE_OPTIONS_SINGLE, "Invalid country search returned results");

        //Test to click on search result
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
        WebElement submitButton = Driver.getDriver().findElement(By.cssSelector("input[type='button']"));
        submitButton.click();

        WebElement result = Driver.getDriver().findElement(By.cssSelector("p[id='result']"));

        Assert.assertEquals(result.getText(), LOGGED_RESULT, "Log after clicking submit");


        listItems = sendText("united", input);
        String[] actualItems = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            actualItems[i] = listItems.get(i).getText();
        }
         Assert.assertEquals(actualItems, AUTOCOMPLETE_OPTIONS_MULTIPLE, "Autocomplete options");

    }




    public WebElement refreshAutoCompleteList(){
        WebElement autocompleteList = null;
        try{
            autocompleteList = Driver.getDriver().findElement(By.id("myCountryautocomplete-list"));
        } catch (Exception e){
            Assert.assertEquals(false, false, "Test if autocomplete exists by default");
        }
        return autocompleteList;
    }

    public List<WebElement> sendText(String str, WebElement inputField) {
        inputField.clear();
        inputField.sendKeys(str);
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement autocompleteList = refreshAutoCompleteList();
        return autocompleteList.findElements(By.tagName("div"));
    }

}