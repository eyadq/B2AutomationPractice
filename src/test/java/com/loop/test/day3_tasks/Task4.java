package com.loop.test.day3_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task4 {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://etsy.com");

        WebElement searchBox = driver.findElement(By.id("global-enhancements-search-query"));
        searchBox.sendKeys("wooden spoon");


        WebElement searchButton = driver.findElement(By.cssSelector("button[aria-label=\"Search\"]"));
        searchButton.click();

        String searchTitle = driver.getTitle();
        if(searchTitle.equals("Wooden spoon - Etsy")){
            System.out.println("As expected the actual title: \"" + searchTitle +"\" matches \"Wooden spoon - Etsy\" -> TEST PASSED");
        } else {
            System.out.println("Unlike as expected the actual title: \"" + searchTitle +"\" does not match  \"Wooden spoon - Etsy\" -> TEST FAILED");
        }



    }
}
