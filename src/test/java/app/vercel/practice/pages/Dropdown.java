package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.*;
import app.vercel.practice.enums.States;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
    InnerPageFactory factory;


    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(id = "dropdown")
        WebElement simple;
        @FindBy(id = "year")
        WebElement year;
        @FindBy(id = "month")
        WebElement month;
        @FindBy(id = "day")
        WebElement day;
        @FindBy(id = "state")
        WebElement state;
        @FindBy(id = "dropdownMenuLink")
        WebElement dropdownLinks;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static List<WebElement> getSmallHeaders(){
            return Driver.getDriver().findElements(By.tagName("h6"));
        }
        static List<WebElement> getListLinks(){ return Driver.getDriver().findElements(By.xpath("//a[@class='dropdown-item']")); }

        void setDate(LocalDate date){
            new Select(year).selectByValue("" + date.getYear());
            new Select(month).selectByValue("" + (date.getMonth().getValue()-1)); //months in java.time.Month start at 1
            new Select(day).selectByValue("" + date.getDayOfMonth());
        }

        LocalDate getDate(){
            String yearCurrent = new Select(year).getFirstSelectedOption().getText();
            String monthCurrent = new Select(month).getFirstSelectedOption().getAttribute("value");
            String dayCurrent = new Select(day).getFirstSelectedOption().getText();

            return LocalDate.of(
                    Integer.parseInt(yearCurrent),
                    Integer.parseInt(monthCurrent) +1, //months in java.time.Month start at 1
                    Integer.parseInt(dayCurrent)
            );
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }

    @Test
    public void testHeaders(){
        Assert.assertEquals(factory.header.getText(), MAIN_HEADER_TEXT, "Text of main header" + MESSAGE_MATCH);
        for (int i = 0; i < factory.getSmallHeaders().size(); i++) {
            Assert.assertEquals(factory.getSmallHeaders().get(i).getText(), SMALLER_HEADER_TEXTS[i], "Smaller header " + i + " text" + MESSAGE_MATCH);
        }
    }

    @Test
    public void testDropdownSimple(){
        List<WebElement> simpleOptions = new Select(factory.simple).getOptions();
        for (int i=0; i<simpleOptions.size(); i++)
            Assert.assertEquals(simpleOptions.get(i).getText(), SIMPLE_OPTIONS[i], "Text of option " + i + MESSAGE_MATCH );
    }

    @Test
    public void testDropDownBirth(){
        Select dropdownYear = new Select(factory.year);
        Assert.assertEquals(Arrays.toString(dropdownYear.getOptions().stream().map(each-> each.getText()).toArray()), Arrays.toString(ArrayUtil.getIntArray(LocalDate.now().getYear()-99, LocalDate.now().getYear())), "Options in year dropdown" + MESSAGE_MATCH);

        Select dropdownMonth = new Select(factory.month);
        Assert.assertEquals(Arrays.toString(dropdownMonth.getOptions().stream().map(each-> each.getText()).toArray()), Arrays.toString(Arrays.stream(Month.values()).map(each-> StringUtil.toNameCase(each.toString())).toArray()), "Options in month dropdown" + MESSAGE_MATCH);

        Select dropdownDay = new Select(factory.day);
        Assert.assertEquals(dropdownDay.getOptions().size(), Month.of(Integer.parseInt(dropdownMonth.getFirstSelectedOption().getAttribute("value")) + 1).length(DateUtil.isLeapYear()), "Options in days dropdown matches expected value of current month" + MESSAGE_MATCH);

        Assert.assertEquals(factory.getDate(), LocalDate.now() , "Default birth date while looking at all three birth date dropdowns together is expected to be today's date" + MESSAGE_MATCH);

        LocalDate randomDate = DateUtil.getRandomDate();
        factory.setDate(randomDate);
        Assert.assertEquals(factory.getDate(), randomDate, "Date dropdowns were set to that random date " + MESSAGE_MATCH);
    }

    @Test
    public void testDropDownState(){
        Select dropDownStates = new Select(factory.state);

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
        List<WebElement> options = new Select(Driver.getDriver().findElement(By.name("Languages"))).getOptions();

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
        factory.getListLinks().forEach(each-> Assert.assertTrue(BrowserUtil.verifyUrl(each.getAttribute("href")), "URL is not valid"));
    }
}
