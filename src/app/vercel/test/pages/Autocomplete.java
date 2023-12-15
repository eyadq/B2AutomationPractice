package app.vercel.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
        driver.get(pageURL);
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Autocomplete header text");
    }

    @Test
    public void testParagraph (){
        driver.get(pageURL);
        WebElement paragraph = driver.findElement(By.xpath("//p[text()='Start typing:']"));
        Assert.assertEquals(paragraph.getText(), PARAGRAPH_TEXT, "Autocomplete parapgraph text");
    }
    @Test
    public void testAutocomplete(){
        driver.get(pageURL);
        WebElement input = driver.findElement(By.id("myCountry"));
        Assert.assertEquals(input.getAttribute("placeholder"), PLACEHOLDER_TEXT, "placeholder text for input");

        WebElement autocompleteList;
        autocompleteList = refreshAutoCompleteList(driver);

        List<WebElement> listItems;

        listItems = sendText("Palest", input, driver);
        String[] actualOutputFromAutoComplete = new String[listItems.size()];
        for (int i = 0; i < actualOutputFromAutoComplete.length; i++) {
            actualOutputFromAutoComplete[i] = listItems.get(i).getText();
        }
        Assert.assertEquals(actualOutputFromAutoComplete, AUTOCOMPLETE_OPTIONS_SINGLE, "Invalid country search returned results");

        //Test to click on search result
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='button']"));
        submitButton.click();

        WebElement result = driver.findElement(By.cssSelector("p[id='result']"));

        Assert.assertEquals(result.getText(), LOGGED_RESULT, "Log after clicking submit");


        listItems = sendText("united", input, driver);
        String[] actualItems = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            actualItems[i] = listItems.get(i).getText();
        }
         Assert.assertEquals(actualItems, AUTOCOMPLETE_OPTIONS_MULTIPLE, "Autocomplete options");

        driver.quit();

    }

    public WebElement refreshAutoCompleteList(WebDriver driver){
        WebElement autocompleteList = null;
        try{
            autocompleteList = driver.findElement(By.id("myCountryautocomplete-list"));
        } catch (Exception e){
            Assert.assertEquals(false, false, "Test if autocomplete exists by default");
        }
        return autocompleteList;
    }

    public List<WebElement> sendText(String str, WebElement inputField, WebDriver driver) {
        inputField.clear();
        inputField.sendKeys(str);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement autocompleteList = refreshAutoCompleteList(driver);
        return autocompleteList.findElements(By.tagName("div"));
    }

}