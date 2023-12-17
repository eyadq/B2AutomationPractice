package com.loop.test.day6_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2 {

    WebDriver driver;

    private static final String TEXT_IFRAME_PARENT = "Parent frame";
    private static final String TEXT_IFRAME_CHILD = "Child Iframe";

    @Test
    public void test(){
        driver.get("https://demoqa.com/nestedframes");

        //WebElement childFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
        WebElement parentFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(parentFrame);
        WebElement childFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
        driver.switchTo().frame(childFrame);
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), TEXT_IFRAME_CHILD, "Text in p tags in child iframe");

        driver.switchTo().parentFrame();
        WebElement body = driver.findElement(By.tagName("body"));
        Assert.assertEquals(body.getText(), TEXT_IFRAME_PARENT, "Text in body of parent frame");

    }

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDownMethod(){
        //driver.quit();
    }
}
