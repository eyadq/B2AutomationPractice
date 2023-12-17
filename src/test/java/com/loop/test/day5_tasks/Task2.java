package com.loop.test.day5_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2 {

    WebDriver driver;

    private static final String HEADER_TEXT = "Dropdown List";
    private static final String OPTION_DEFAULT = "Please select an option";
    private static final String OPTION_ONE = "Option 1";
    private static final String OPTION_TWO = "Option 2";

    @Test
    public void test(){
        driver.get("https://www.etsy.com/");

        //Website wont allow automation

        WebElement input = driver.findElement(By.xpath("//input[placeholder='Search for anything']"));
        input.sendKeys("rings" + Keys.ENTER);

        WebElement timeButton = driver.findElement(By.xpath("//span[contains(text(), 'Estimated Arrival ')]/parent::span/parent::button"));
        timeButton.click();

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }
}
