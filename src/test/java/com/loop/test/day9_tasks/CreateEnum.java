package com.loop.test.day9_tasks;

import com.loop.test.base.TestBase;
import com.loop.test.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CreateEnum extends TestBase {

    @Test
    public void test() throws InterruptedException {
        driver.get("https://app.docuport.app/company-formation/resident");
        WebElement input = driver.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append'])[1]"));
        input.click();

        Actions actions = new Actions(driver);
        List<WebElement> options = driver.findElements(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active v-autocomplete__content']"));


        String last = options.get(options.size()-1).getText();
        String currentLast = last;

        do {
            actions.scrollToElement(options.get(options.size()-1)).perform();
            options = driver.findElements(By.xpath("//div[@class='v-list-item__title']"));
            last = currentLast;
            currentLast = options.get(options.size()-1).getText();
        } while(!last.equals(currentLast));

        System.out.println("enum Jurisdiction {");
        for (int i = 0; i < options.size(); i++) {
            String endOfLine = (i != options.size()-1) ? "," : ";";

            System.out.println(options.get(i).getText().toUpperCase().replace(" ", "_") + "(\"" + options.get(i).getText() + "\")" + endOfLine);
        }
        System.out.println("private String jurisdictionSetting;");
        System.out.println("private  Jurisdiction(String optionName) {this.jurisdictionSetting = jurisdictionSetting;}");
        System.out.println("public String jurisdictionSetting() {return jurisdictionSetting;}\n}");


    }
}
