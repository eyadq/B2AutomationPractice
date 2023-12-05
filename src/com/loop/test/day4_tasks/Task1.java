package com.loop.test.day4_tasks;

import com.loop.test.utilities.constants.DocuportConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DocuportConstants.URL_DOCUPORT_BETA);

        WebElement inputUser = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputUser.sendKeys(DocuportConstants.USERNAME_CLIENT);
        inputPassword.sendKeys(DocuportConstants.PASSWORD_CLIENT);
        button.click();

        Thread.sleep(4000);
        WebElement continueButton = driver.findElement(By.xpath("//span[text()=' Continue ']"));
        continueButton.click();
        Thread.sleep(3000);



        List<WebElement> menuItems = driver.findElements(By.xpath("//span[@class='body-1']"));

//        List<WebElement> menuItems = new ArrayList<>();
//        menuItems.add(driver.findElement(By.xpath("//span[text()='Home']")));
//        menuItems.add(driver.findElement(By.xpath("//span[text()='Received docs']")));
//        menuItems.add(driver.findElement(By.xpath("//span[text()='My uploads']")));
//        menuItems.add(driver.findElement(By.xpath("//span[text()='Invitations']")));
        //menuItems.add(driver.findElement(By.xpath("//span[text()='1099 Form']")));

        System.out.println("Directly on login: ");
        for (int i = 0; i < menuItems.size(); i++) {

            try{
               System.out.println("\t\"" + menuItems.get(i).getText() + "\" " + ((menuItems.get(i).isDisplayed()) ? " is displayed" : " is not displayed"));

            } catch (Exception e){
                System.out.println("Element at " + i + " is not reachable");
            }
        }

        WebElement menuButton = driver.findElement(By.xpath("//i[@class='v-icon notranslate mdi mdi-menu theme--light']"));
        menuButton.click();

        Thread.sleep(2000);

         String[] items = {"Home", "Received docs", "My uploads", "Invitations", "1099 Form"};

        System.out.println("After clicking on hamburger menu button" );
        for (int i = 0; i < menuItems.size(); i++) {

            try{
                System.out.println("\t\"" + items[i] + "\" " + ((menuItems.get(i).isDisplayed()) ? " is displayed" : " is not displayed"));

            } catch (Exception e){
                System.out.println("Element at " + i + " is not reachable");
            }
        }







    }
}
