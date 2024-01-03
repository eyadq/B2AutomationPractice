package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Autocomplete extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/autocomplete.html";
    private static final String HEADER_TEXT = "Autocomplete";
    private static final String PARAGRAPH_TEXT = "Start typing:";
    private static final String PLACEHOLDER_TEXT = "Country";
    private static final String[] AUTOCOMPLETE_OPTIONS_SINGLE = {"Palestine"};
    private static final String[] AUTOCOMPLETE_OPTIONS_MULTIPLE = {"United Arab Emirates", "United Kingdom", "United States of America"};
    private static final String LOGGED_RESULT = "You selected: Palestine";
    InnerPageFactory factory;
    List<WebElement> listItems;

    class InnerPageFactory{

        @FindBy(tagName = "h3")
        WebElement header;

        @FindBy(xpath = "//p[text()='Start typing:']")
        WebElement paragraph;

        @FindBy(id = "myCountry")
        WebElement input;

        @FindBy(css = "input[type='button']")
        WebElement submitButton;
        InnerPageFactory(){PageFactory.initElements(Driver.getDriver(), this);}

        public List<WebElement> getAutoCompleteList(){
            WebElement autocompleteListElement = null;
            try{
                autocompleteListElement = Driver.getDriver().findElement(By.id("myCountryautocomplete-list"));
            } catch (Exception e){
                e.printStackTrace();
            }
            if(autocompleteListElement==null)
                return new ArrayList<WebElement>();
            return autocompleteListElement.findElements(By.tagName("div"));
        }

        public WebElement getLog(){
            return Driver.getDriver().findElement(By.cssSelector("p[id='result']"));
        }
    }

    @BeforeMethod
    public void setupMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }
    @Test
    public void testText(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Autocomplete header text");
        Assert.assertEquals(factory.paragraph.getText(), PARAGRAPH_TEXT, "Autocomplete paragraph text");
    }

    @Test
    public void testAutocomplete(){
        Assert.assertEquals(factory.input.getAttribute("placeholder"), PLACEHOLDER_TEXT, "placeholder text for input");

        //sendText("Palest");
        factory.input.sendKeys("Palest");
        Assert.assertEquals(factory.getAutoCompleteList().stream().map(each -> each.getText()).toArray(), AUTOCOMPLETE_OPTIONS_SINGLE, "Invalid country search returned results");

        factory.input.sendKeys(Keys.ARROW_DOWN);
        factory.input.sendKeys(Keys.ENTER);
        factory.submitButton.click();
        Assert.assertEquals(factory.getLog().getText(), LOGGED_RESULT, "Log after clicking submit");

        //sendText("united");
        factory.input.sendKeys("united");
        Assert.assertEquals(factory.getAutoCompleteList().stream().map(each -> each.getText()).toArray(), AUTOCOMPLETE_OPTIONS_MULTIPLE, "Autocomplete options");

    }





}