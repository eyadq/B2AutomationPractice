package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class _7_Checkboxes extends _$_VercelTestBase {

    //Checkboxes
    public static final String CHECKBOXES_HEADER = "Checkboxes";
    public static final String[] CHECKBOXES_LABELS = {"Checkbox 1", "Checkbox 2"};
    public static final String[] CHECKBOXES_OPTIONS= {"checkbox1", "checkbox2"};
    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/checkboxes.html";

    @Test
    public void testHeader(){
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), CHECKBOXES_HEADER, "Checkboxes header text" + _$_VercelTestBase.MESSAGE_MATCH);
    }

    @Test
    public void testCheckboxesText(){
        List<WebElement> text = driver.findElements(By.cssSelector("span[class='checktext"));
        for (int i = 0; i < text.size(); i++) {
            Assert.assertEquals(text.get(i).getText(), CHECKBOXES_LABELS[i], "Checkbox label " + i + " " + _$_VercelTestBase.MESSAGE_MATCH);
        }
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkboxes']"));
        for (int i = 0; i < checkboxes.size(); i++) {
            Assert.assertEquals(checkboxes.get(i).getAttribute("name"), CHECKBOXES_OPTIONS[i], "checkbox options for " + i + " " + _$_VercelTestBase.MESSAGE_MATCH);
        }
    }

    @Test
    public void testCheckboxesFunctionality(){
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (int i = 0; i < checkboxes.size(); i++){
            if(checkboxes.get(i).getAttribute("name").equals(CHECKBOXES_OPTIONS[1])){
                Assert.assertEquals(checkboxes.get(i).isSelected(), true, "checkbox 2 selected by default" + _$_VercelTestBase.MESSAGE_MATCH);
            } else {
                Assert.assertEquals(checkboxes.get(i).isSelected(), false, "checkbox " + (i + 1) + "selected by default" + _$_VercelTestBase.MESSAGE_MATCH);
            }
        }

        checkboxes.get(0).click();
        Assert.assertEquals(checkboxes.get(0).isSelected(), true, "checkbox 1 is selected after click command" + _$_VercelTestBase.MESSAGE_MATCH);

        Actions action = new Actions(driver);
        action.moveToElement(checkboxes.get(1)).perform();
        action.click().perform();
        Assert.assertEquals(checkboxes.get(1).isSelected(), false, "checkbox 2 is deselected after emulated mouse click" + _$_VercelTestBase.MESSAGE_MATCH);


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
        //driver.quit();
    }

    @AfterClass
    public void tearDown(){
    }
}
