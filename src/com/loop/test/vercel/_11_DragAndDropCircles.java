package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.lang.annotation.Inherited;

public class _11_DragAndDropCircles extends _$_VercelTestBase {

    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/drag-and-drop-circles.html";

    @Test
    public void test(){
        WebElement target = driver.findElement(By.id("droptarget"));
        WebElement draggable = driver.findElement(By.id("draggable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, target).perform();
    }

    @BeforeMethod
    public void setUpMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
