package com.loop.test.day5_tasks;

import com.loop.test.utilities.DocuportConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task3 {

    WebDriver driver;

    @Test
    public void test() {
        driver.get(DocuportConstants.URL_DOCUPORT_BETA);

        WebElement inputUser = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputUser.sendKeys(DocuportConstants.USERNAME_ADVISOR);
        inputPassword.sendKeys(DocuportConstants.PASSWORD_ADVISOR);
        button.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> driver.findElement(By.cssSelector("a[href='/received-docs']")).isDisplayed());

        WebElement recieved = driver.findElement(By.cssSelector("a[href='/received-docs']"));
        recieved.click();

        WebElement input = driver.findElement(By.xpath("//button[@class='mr-3 mb-1 mb-sm-0 v-btn v-btn--has-bg theme--light v-size--large']"));
        input.click();

        WebElement statusLabel = driver.findElement(By.xpath("//label[text()='Status']"));
        WebElement statusInput = driver.findElement(By.id(statusLabel.getAttribute("for")));
        statusInput.click();

        WebElement inProgressOption = driver.findElement(By.xpath("//div[@role='listbox']/child::div/following-sibling::div"));
        inProgressOption.click();
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
