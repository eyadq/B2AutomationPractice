package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class _3_Autocomplete extends _$_VercelTestBase {

    //Autocomplete
    public static final String AUTOCOMPLETE_HEADER = "Autocomplete";
    public static final String AUTOCOMPLETE_PARAGRAPH = "Start typing:";
    public static final String AUTOCOMPLETE_PLACEHOLDER_COUNTRY = "Country";
    public static final String AUTOCOMPLETE_OPTIONS_SINGLE = "Palestine";
    public static final String AUTOCOMPLETE_LOGGED_RESULT = "You selected: Palestine";
    public static final String[] AUTOCOMPLETE_OPTIONS_MULTIPLE = {"United Arab Emirates", "United Kingdom", "United States of America"};
    WebDriver driver;
    static String pageURL = "https://loopcamp.vercel.app/autocomplete.html";

    @Test
    public void testHeader(){
        WebElement header = driver.findElement(By.tagName("h3"));
        //HelperMethods.logPrintMatch(header.getText(), VercelConstants.AUTOCOMPLETE_HEADER, "Autocomplete header text");
        Assert.assertEquals(header.getText(), AUTOCOMPLETE_HEADER, "Autocomplete header text");
    }

    @Test
    public void testParagraph (){
        WebElement paragraph = driver.findElement(By.xpath("//p[text()='Start typing:']"));
        //HelperMethods.logPrintMatch(paragraph.getText(), VercelConstants.AUTOCOMPLETE_PARAGRAPH, "Autocomplete parapgraph text");
        Assert.assertEquals(paragraph.getText(), AUTOCOMPLETE_PARAGRAPH, "Autocomplete parapgraph text");
    }
    @Test
    public void run(){


        WebElement input = driver.findElement(By.id("myCountry"));
        //HelperMethods.logPrintMatch(input.getAttribute("placeholder"), VercelConstants.AUTOCOMPLETE_PLACEHOLDER_COUNTRY, "placeholder text for input");
        Assert.assertEquals(input.getAttribute("placeholder"), AUTOCOMPLETE_PLACEHOLDER_COUNTRY, "placeholder text for input");

        WebElement autocompleteList;
        autocompleteList = refreshAutoCompleteList(driver);

        List<WebElement> listItems;

        listItems = sendText("Palest", input, driver);
        //HelperMethods.logPrintMatch(listItems.size(), 1, "Invalid country search returned results");
        Assert.assertEquals(listItems.size(), 1, "Invalid country search returned results");
        if(listItems.size() == 1){
            //HelperMethods.logPrintMatch(listItems.get(0).getText(), VercelConstants.AUTOCOMPLETE_OPTIONS_SINGLE, "Autocomplete options");
            Assert.assertEquals(listItems.get(0).getText(), AUTOCOMPLETE_OPTIONS_SINGLE, "Autocomplete options");
        } else { //TODO: Get rid of print statement outright
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

        //HelperMethods.logPrintMatch(result.getText(), VercelConstants.AUTOCOMPLETE_LOGGED_RESULT, "Log after clicking submit");
        Assert.assertEquals(result.getText(), AUTOCOMPLETE_LOGGED_RESULT, "Log after clicking submit");


        listItems = sendText("united", input, driver);
        String[] actualItems = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            actualItems[i] = listItems.get(i).getText();
        }
        //HelperMethods.logPrintMatch(actualItems, VercelConstants.AUTOCOMPLETE_OPTIONS_MULTIPLE, "Autocomplete options");
        Assert.assertEquals(actualItems, AUTOCOMPLETE_OPTIONS_MULTIPLE, "Autocomplete options");

        driver.quit();

    }

    public WebElement refreshAutoCompleteList(WebDriver driver){
        WebElement autocompleteList = null;
        try{
            autocompleteList = driver.findElement(By.id("myCountryautocomplete-list"));
        } catch (Exception e){
            //HelperMethods.logPrintMatch(false, false, "Test if autocomplete exists by default");
            Assert.assertEquals(false, false, "Test if autocomplete exists by default");
        }
        return autocompleteList;
    }

    public List<WebElement> sendText(String str, WebElement inputField, WebDriver driver) {
        inputField.clear();
        inputField.sendKeys(str);
        //Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement autocompleteList = refreshAutoCompleteList(driver);
        return autocompleteList.findElements(By.tagName("div"));
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