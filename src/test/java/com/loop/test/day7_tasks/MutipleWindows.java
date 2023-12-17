package com.loop.test.day7_tasks;

import com.loop.test.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class MutipleWindows extends TestBase {

    private static final String START_URL = "https://loopcamp.vercel.app/";

    @Test
    public void testWindow(){
        driver.get(START_URL);

        WebElement multipleWindows = driver.findElement(By.xpath("//a[text()='Multiple Windows']"));
        multipleWindows.click();

        WebElement footer = driver.findElement(By.xpath("//div[@style='text-align: center;']"));
        Assert.assertEquals(footer.getText(), "Powered by LOOPCAMP", "Text of footer");

        Assert.assertEquals(driver.getTitle(), "Windows", "Window handle for multiple windows page");
        WebElement link = driver.findElement(By.xpath("//a[text()='Click Here']"));
        link.click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles){
            driver.switchTo().window(handle);
                if (driver.getTitle().equals("New Window"))
                    break;
        }
        Assert.assertEquals(driver.getTitle(), "New Window", "Window handle for after clicking 'Click here'");



    }
}
