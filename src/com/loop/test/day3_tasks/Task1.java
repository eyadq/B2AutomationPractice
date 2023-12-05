package com.loop.test.day3_tasks;

import com.loop.test.utilities.constants.DocuportConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://beta.docuport.app");

        //locate email input, password input, and login button input

        WebElement userName = driver.findElement(By.cssSelector("input[id='input-14']"));
        WebElement userPassword = driver.findElement(By.cssSelector("input[id='input-15']"));
        WebElement buttonLogin = driver.findElement(By.cssSelector("span[class='v-btn__content']"));

        //login to docuport
        userName.sendKeys(DocuportConstants.USERNAME_CLIENT);
        userPassword.sendKeys(DocuportConstants.PASSWORD_CLIENT);
        buttonLogin.click();

        //need to click on "Continue" button once logged in
        Thread.sleep(5000);
        List<WebElement> buttons = driver.findElements(By.cssSelector("span[class='v-btn__content']"));
        WebElement buttonContinue = driver.findElement(By.cssSelector("span[class='v-btn__content']"));
        for(WebElement element : buttons){
            String buttonText = element.getText();
            if(buttonText.equals("Continue")){
                buttonContinue = element;
            }
        }
        buttonContinue.click();

        //log out
        WebElement userNameMenu = driver.findElement(By.cssSelector("span[class='subtitle-2 text-none pl-2 pr-3 gray--text text--darken-3']"));
        userNameMenu.click();

        WebElement buttonLogout = driver.findElement(By.cssSelector("div[id=\"list-item-152\"]"));

        List<WebElement> menuItems = driver.findElements(By.cssSelector("div[role='menuitem']"));
        buttonLogout = menuItems.get(menuItems.size() - 1);

        if(menuItems.size() > 0){
            System.out.println("We have logged in and even have access to the menu of the login in user with number of items :" + menuItems.size());
        } else {
            System.out.println("We do not have access to user menu, did we log in");
        }
        buttonLogout.click();

        Thread.sleep(2000);
        buttonLogin = driver.findElement(By.cssSelector("span[class='v-btn__content']"));
        String buttonLoginText = buttonLogin.getText();

        if(DocuportConstants.LOGIN_BUTTON_TEXT.equalsIgnoreCase(buttonLoginText)){
            System.out.println("Expected login button of homepage found: \"" + DocuportConstants.LOGIN_BUTTON_TEXT + "\", matches actual type \"" + buttonLoginText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected login button of homepage found: \"" + DocuportConstants.LOGIN_BUTTON_TEXT + "\", does not match actual type \"" + buttonLoginText + "\" => TEST FAILED");
        }

        driver.quit();
    }
}
