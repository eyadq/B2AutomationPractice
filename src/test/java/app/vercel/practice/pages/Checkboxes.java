package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Checkboxes extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/checkboxes.html";
    private static final String HEADER_TEXT = "Checkboxes";
    private static final String[] CHECKBOX_LABELS = {"Checkbox 1", "Checkbox 2"};
    private static final String[] CHECKBOX_OPTIONS = {"checkbox1", "checkbox2"};

    @Test
    public void testHeader(){
        Driver.getDriver().get(pageURL);
        WebElement header = Driver.getDriver().findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Checkboxes header text" + MESSAGE_MATCH);
    }

    @Test
    public void testCheckboxesText(){
        Driver.getDriver().get(pageURL);
        List<WebElement> text = Driver.getDriver().findElements(By.cssSelector("span[class='checktext"));
        for (int i = 0; i < text.size(); i++) {
            Assert.assertEquals(text.get(i).getText(), CHECKBOX_LABELS[i], "Checkbox label " + i + " " + MESSAGE_MATCH);
        }
        List<WebElement> checkboxes = Driver.getDriver().findElements(By.xpath("//input[@type='checkboxes']"));
        for (int i = 0; i < checkboxes.size(); i++) {
            Assert.assertEquals(checkboxes.get(i).getAttribute("name"), CHECKBOX_OPTIONS[i], "checkbox options for " + i + " " + MESSAGE_MATCH);
        }
    }

    @Test
    public void testCheckboxesFunctionality(){
        Driver.getDriver().get(pageURL);
        List<WebElement> checkboxes = Driver.getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        for (int i = 0; i < checkboxes.size(); i++){
            if(checkboxes.get(i).getAttribute("name").equals(CHECKBOX_OPTIONS[1])){
                Assert.assertEquals(checkboxes.get(i).isSelected(), true, "checkbox 2 selected by default" + MESSAGE_MATCH);
            } else {
                Assert.assertEquals(checkboxes.get(i).isSelected(), false, "checkbox " + (i + 1) + "selected by default" + MESSAGE_MATCH);
            }
        }

        checkboxes.get(0).click();
        Assert.assertEquals(checkboxes.get(0).isSelected(), true, "checkbox 1 is selected after click command" + MESSAGE_MATCH);

        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(checkboxes.get(1)).perform();
        action.click().perform();
        Assert.assertEquals(checkboxes.get(1).isSelected(), false, "checkbox 2 is deselected after emulated mouse click" + MESSAGE_MATCH);


    }
}
