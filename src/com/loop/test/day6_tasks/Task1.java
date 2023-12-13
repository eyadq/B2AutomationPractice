package com.loop.test.day6_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Task1 {

    WebDriver driver;

    private static final String CONFIRM_MESSAGE_ACCEPT = "You selected Ok";
    private static final String CONFIRM_MESSAGE_DISMISS = "You selected Cancel";
    private static final String EXPECTED_OUTPUT_PROMPT_ALERT = "You entered Loop Academy";

    @Test
    public void testAlertButton() {
        driver.get("https://demoqa.com/alerts");

        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();
        driver.switchTo().alert().accept();

    }

    @Test
    public void testTimerAlertButton() {
        driver.get("https://demoqa.com/alerts");

        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(d -> driver.switchTo().alert());
        driver.switchTo().alert().accept();

    }

    @Test
    public void testConfirmButton(){
        driver.get("https://demoqa.com/alerts");

        WebElement confirmButton = driver.findElement(By.id("confirmButton"));;
        confirmButton.click();
        driver.switchTo().alert().accept();
        WebElement confirmResult = driver.findElement(By.id("confirmResult"));
        Assert.assertEquals(confirmResult.getText(), CONFIRM_MESSAGE_ACCEPT, "message after accepting alert");

        confirmButton.click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals(confirmResult.getText(), CONFIRM_MESSAGE_DISMISS, "message after dismissing alert");
    }

    @Test
    public void testPromptButton(){
        driver.get("https://demoqa.com/alerts");
        WebElement promptButton = driver.findElement(By.id("promtButton"));
        promptButton.click();
        String text = "Loop Academy";
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        WebElement promptResult = driver.findElement(By.id("promptResult"));
        Assert.assertEquals(promptResult.getText(), EXPECTED_OUTPUT_PROMPT_ALERT, "message after entering message and accepting prompt alert");
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
