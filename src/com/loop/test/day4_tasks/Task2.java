package com.loop.test.day4_tasks;

import com.loop.test.utilities.constants.DocuportConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DocuportConstants.URL_DOCUPORT_BETA);

        WebElement inputUser = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputUser.sendKeys(DocuportConstants.USERNAME_ADVISOR);
        inputPassword.sendKeys(DocuportConstants.PASSWORD_ADVISOR);
        button.click();

        Thread.sleep(4000);

        List<WebElement> menuLinks = driver.findElements(By.xpath("//a[@class='py-x-2 v-list-item v-list-item--link theme--light']"));
        //adds the home link to the beginning of the menuLinks array
        menuLinks.add(0, driver.findElement(By.xpath("//a[@class='py-x-2 v-item--active v-list-item--active v-list-item v-list-item--link theme--light']")));

        //Need to validate links
        String[] expectedLinks = {
                DocuportConstants.URL_BETA_HOME,
                DocuportConstants.URL_BETA_RECEIVED_DOCS,
                DocuportConstants.URL_BETA_MY_UPLOADS,
                DocuportConstants.URL_BETA_CLIENTS,
                DocuportConstants.URL_BETA_INVITATIONS,
                DocuportConstants.URL_BETA_USERS,
                DocuportConstants.URL_BETA_LEADS,
                DocuportConstants.URL_BETA_BOOKKEEPING,
                DocuportConstants.URL_BETA_1099_FORMS,
                DocuportConstants.URL_BETA_RECONCILIATIONS
        };
        for (int i = 0; i < menuLinks.size(); i++) {
            String actualLink = menuLinks.get(i).getAttribute("href");
            if(actualLink.equals(expectedLinks[i])){
                System.out.println("The actual link for " + menuLinks.get(i).getText() + ": \"" + actualLink + "\" matches expected link: \"" +
                        expectedLinks[i] + "\" => TEST PASSED");
            } else {
                System.out.println("The actual link for " + menuLinks.get(i).getText() + ": \"" + actualLink + "\" does NOT match expected link: \"" +
                        expectedLinks[i] + "\" => TEST FAILED");
            }
        }




    }
}
