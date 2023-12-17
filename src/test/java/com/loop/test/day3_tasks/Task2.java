package com.loop.test.day3_tasks;

import com.loop.test.utilities.DocuportConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task2 {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://beta.docuport.app");

        WebElement userName = driver.findElement(By.cssSelector("label[for='input-14']"));
        String userNamePlaceholder = userName.getText();
        //System.out.println(userName.getText());

        if(DocuportConstants.PLACE_HOLDER_LOGIN_USERNAME.equalsIgnoreCase(userNamePlaceholder)){
            System.out.println("Expected username placeholder text: \"" + DocuportConstants.PLACE_HOLDER_LOGIN_USERNAME+ "\", matches actual text \"" + userNamePlaceholder + "\" => TEST PASSED");
        } else {
            System.out.println("Expected username placeholder text: \"" + DocuportConstants.PLACE_HOLDER_LOGIN_USERNAME + "\", does not match actual text \"" + userNamePlaceholder + "\" => TEST FAILED");
        }
        WebElement userPassword = driver.findElement(By.cssSelector("label[for='input-15']"));
        String userPasswordPlaceholder = userPassword.getText();
        //System.out.println(userPassword.getText());

        if(DocuportConstants.PLACE_HOLDER_LOGIN_PASSWORD.equalsIgnoreCase(userPasswordPlaceholder)){
            System.out.println("Expected password placeholder text: \"" + DocuportConstants.PLACE_HOLDER_LOGIN_PASSWORD+ "\", matches actual text \"" + userPasswordPlaceholder + "\" => TEST PASSED");
        } else {
            System.out.println("Expected password placeholder text: \"" + DocuportConstants.PLACE_HOLDER_LOGIN_PASSWORD + "\", does not match actual text \"" + userPasswordPlaceholder + "\" => TEST FAILED");
        }

        //click on login
        WebElement buttonLogin = driver.findElement(By.cssSelector("span[class='v-btn__content']"));
        buttonLogin.click();

        List<WebElement> errorMessages = driver.findElements(By.cssSelector("div[class='v-messages__message']"));
        String userNameError = errorMessages.get(0).getText();
        String passwordError = errorMessages.get(1).getText();

        if(DocuportConstants.LOGIN_ERROR_ENTER_USERNAME.equalsIgnoreCase(userNameError)){
            System.out.println("Expected username error text: \"" + DocuportConstants.LOGIN_ERROR_ENTER_USERNAME+ "\", matches actual text \"" + userNameError + "\" => TEST PASSED");
        } else {
            System.out.println("Expected username error text: \"" + DocuportConstants.LOGIN_ERROR_ENTER_USERNAME + "\", does not match actual text \"" + userNameError + "\" => TEST FAILED");
        }

        if(DocuportConstants.LOGIN_ERROR_ENTER_PASSWORD.equalsIgnoreCase(passwordError)){
            System.out.println("Expected password error text: \"" + DocuportConstants.LOGIN_ERROR_ENTER_PASSWORD+ "\", matches actual text \"" + passwordError + "\" => TEST PASSED");
        } else {
            System.out.println("Expected password error text: \"" + DocuportConstants.LOGIN_ERROR_ENTER_PASSWORD + "\", does not match actual text \"" + passwordError + "\" => TEST FAILED");
        }


    }
}
