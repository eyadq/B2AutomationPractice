package com.loop.test.day3_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://google.com");

        WebElement gmailLink = driver.findElement(By.cssSelector("a[href='https://mail.google.com/mail/&ogbl']"));
        gmailLink.click();

        Thread.sleep(5000);
        String gmailTitle = driver.getTitle();
        if(gmailTitle.contains("Gmail")){
            System.out.println("As expected the actual title: \"" + gmailTitle +"\" contains \"Gmail\" -> TEST PASSED");
        } else {
            System.out.println("Unlike as expected the actual title: \"" + gmailTitle +"\" does not contain  \"Gmail\" -> TEST FAILED");
        }

        driver.navigate().back();
        Thread.sleep(5000);
        String googleTitle = driver.getTitle();
        if(googleTitle.contains("Google")){
            System.out.println("As expected the actual title: \"" + googleTitle +"\" contains \"Google\" -> TEST PASSED");
        } else {
            System.out.println("Unlike as expected the actual title: \"" + googleTitle +"\" does not contain  \"Google\" -> TEST FAILED");
        }

        driver.quit();
    }
}
