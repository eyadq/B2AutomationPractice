package com.loop.test.day5_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1 {

    WebDriver driver;

    private static final String HEADER_TEXT = "Dropdown List";
    private static final String OPTION_DEFAULT = "Please select an option";
    private static final String OPTION_ONE = "Option 1";
    private static final String OPTION_TWO = "Option 2";

    @Test
    public void testDropDown(){
        driver.get("http://the-internet.herokuapp.com/dropdown");

        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), HEADER_TEXT, "Header text");

        Select dropDown = new Select(driver.findElement(By.id("dropdown")));
        Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), OPTION_DEFAULT, "Default option");
        dropDown.selectByValue("1");
        Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), OPTION_ONE, "Option 1 text");
        dropDown.selectByValue("2");
        Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), OPTION_TWO, "Option 2 text");

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }
}
