package com.loop.test.vercel;

import com.loop.test.utilities.DateUtil;
import com.loop.test.utilities.WebDriverFactory;
import com.loop.test.utilities.enums.States;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class Dropdown extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/dropdown.html";
    private static final String MAIN_HEADER_TEXT = "Dropdown List";
    private static final String[] SMALLER_HEADER_TEXTS = {
            "Simple dropdown",
            "Select your date of birth",
            "State selection",
            "Which programming languages do you know?",
            "Select a website"
            };
    private static final String[] SIMPLE_OPTIONS = {
            "Please select an option",
            "Option 1",
            "Option 2"
            };

    private static final String[] LANGUAGES_OPTIONS = {
            "Java",
            "JavaScript",
            "C#",
            "Python",
            "Ruby",
            "C"
    };

    private static final String[] LANGUAGES_VALUES = {
            "java",
            "js",
            "c#",
            "python",
            "ruby",
            "c"
    };

    private static final String STATE_DEFAULT_VALUE = "";
    private static final String STATE_DEFAULT_TEXT = "Select a State";
    private static String[] birthDateExpected;

    static {
        birthDateExpected = DateUtil.getTodaysDate();
    }

    @Test
    public void testHeaders(){
        driver.get(pageURL);

        WebElement headerMain = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(headerMain.getText(), MAIN_HEADER_TEXT, "Text of main header" + MESSAGE_MATCH);
        List<WebElement> headerSize6 = driver.findElements(By.tagName("h6"));
        for (int i = 0; i < headerSize6.size(); i++) {
            Assert.assertEquals(headerSize6.get(i).getText(), SMALLER_HEADER_TEXTS[i], "Smaller header " + i + " text" + MESSAGE_MATCH);
        }
    }

    @Test
    public void testDropdownSimple(){
        driver.get(pageURL);

        Select simple = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> simpleOptions = simple.getOptions();
        for (int i=0; i<simpleOptions.size(); i++){
            Assert.assertEquals(simpleOptions.get(i).getText(), SIMPLE_OPTIONS[i], "Text of option " + i + MESSAGE_MATCH );
        }
    }

    @Test
    public void testDropDownBirth(){
        driver.get(pageURL);

        String[] todaysDate = DateUtil.getTodaysDate();

        Select dropdownYear = new Select(driver.findElement(By.id("year")));
        String[] yearOptions = new String[dropdownYear.getOptions().size()];
        for (int i=0; i<yearOptions.length; i++){
            yearOptions[i] = dropdownYear.getOptions().get(i).getText();
        }
        Assert.assertEquals(Arrays.toString(yearOptions), Arrays.toString(DateUtil.getIntArray(1924, 2023)), "Options in year dropdwon" + MESSAGE_MATCH);

        Select dropdownMonth = new Select(driver.findElement(By.id("month")));
        String[] monthOptions = new String[dropdownMonth.getOptions().size()];
        for (int i = 0; i < monthOptions.length; i++) {
            monthOptions[i] = dropdownMonth.getOptions().get(i).getText();
        }
        Assert.assertEquals(Arrays.toString(monthOptions), Arrays.toString(DateUtil.getMonths()), "Options in month dropdown" + MESSAGE_MATCH);


        Select dropdownDay = new Select(driver.findElement(By.id("day")));
        int numberOfDaysActual = dropdownDay.getOptions().size();
        int numberOfDaysExpected = DateUtil.getNumberDaysInMonth(todaysDate[1]);
        Assert.assertEquals(numberOfDaysActual, numberOfDaysExpected, "Options in days dropdown" + MESSAGE_MATCH);


        String[] birthDateActual = new String[]{
                dropdownYear.getFirstSelectedOption().getText(),
                dropdownMonth.getFirstSelectedOption().getText(),
                dropdownDay.getFirstSelectedOption().getText()
        };

        Assert.assertEquals(Arrays.toString(birthDateActual), Arrays.toString(birthDateExpected) , "Default birth date while looking at all three birth date dropdwons together" + MESSAGE_MATCH);

        String[] randomDate = DateUtil.getRandomDate();
        dropdownYear.selectByValue(randomDate[0]);
        dropdownMonth.selectByValue("" + DateUtil.getMonths(randomDate[1]));
        dropdownDay.selectByValue(randomDate[2]);

        Assert.assertEquals(dropdownYear.getFirstSelectedOption().getText(), randomDate[0], "Year after setting random" + MESSAGE_MATCH);
        Assert.assertEquals(dropdownMonth.getFirstSelectedOption().getText(), randomDate[1], "Month after setting random" + MESSAGE_MATCH);
        Assert.assertEquals(dropdownDay.getFirstSelectedOption().getText(), randomDate[2], "Day after setting random" + MESSAGE_MATCH);
    }

    @Test
    public void testDropDownState(){
        driver.get(pageURL);

        Select dropDownStates = new Select(driver.findElement(By.id("state")));

        Assert.assertEquals(dropDownStates.getFirstSelectedOption().getAttribute("value"), STATE_DEFAULT_VALUE, "Default option for state" + MESSAGE_MATCH);
        Assert.assertEquals(dropDownStates.getFirstSelectedOption().getText(), STATE_DEFAULT_TEXT, "Default text for state" + MESSAGE_MATCH);

        States[] states = States.values();
        for (int i=1; i< dropDownStates.getOptions().size(); i++){ //index 0 has value of "" and text of "Select a State
            WebElement element = dropDownStates.getOptions().get(i);
            Assert.assertEquals(element.getAttribute("value"), states[i-1].toString());
            Assert.assertEquals(element.getText(), states[i-1].getStateName());
        }

        dropDownStates.selectByValue(States.OH.name());
        Assert.assertEquals(dropDownStates.getFirstSelectedOption().getText(), States.OH.getStateName(), "Option after changing should be Ohio" + MESSAGE_MATCH);
    }

    @Test
    public void testDropdownLanguages(){
        driver.get(pageURL);

        Select languages = new Select(driver.findElement(By.name("Languages")));
        List<WebElement> options = languages.getOptions();

        options.forEach(each -> Assert.assertFalse(each.isSelected(), "Default state for languages option should be false") );

        for (int i = 0; i < options.size(); i++) {
            Assert.assertEquals(options.get(i).getText(), LANGUAGES_OPTIONS[i], "Options in languages drop down" + MESSAGE_MATCH);
            Assert.assertEquals(options.get(i).getAttribute("value"), LANGUAGES_VALUES[i], "Values in languages drop down" + MESSAGE_MATCH);
            options.get(i).click();
        }

        options.forEach(each -> Assert.assertTrue(each.isSelected(), "New state for languages option should be true") );

    }

    @Test
    public void testDropdownLinks(){
        driver.get(pageURL);

        WebElement dropdownLinks = driver.findElement(By.xpath("//a[@id='dropdownMenuLink']"));
        dropdownLinks.click();

        WebElement linksMenu = driver.findElement(By.xpath("//div[@aria-labelledby='dropdownMenuLink']"));
        List<WebElement> options = driver.findElements(By.xpath("//a[@class='dropdown-item']"));

        Random random = new Random();
        int randomNum = random.nextInt(5);
        while(randomNum==0){
            randomNum = random.nextInt(5);
        }
        String text = options.get(randomNum).getText().toLowerCase();
        driver.navigate().to(options.get(randomNum).getAttribute("href"));
        String newTitle = driver.getTitle().toLowerCase();
        Assert.assertTrue(newTitle.contains(text), "Text in tags matches link" + MESSAGE_MATCH);


    }
}
