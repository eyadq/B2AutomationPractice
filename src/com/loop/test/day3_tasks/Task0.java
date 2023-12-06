package com.loop.test.day3_tasks;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task0 {

    public static final String FORGOT_PASSWORD_HEADER = "Forgot Password";
    public static final String EMAIL_TEXT = "E-mail";
    public static final String EMAIL_INPUT_ID = "email";
    public static final String RETRIEVE_PASSWORD_BUTTON_TEXT = "submit";
    public static final String POWERED_BY_LOOPCAMP_TEXT = "Powered by LOOPCAMP";

    public static void main(String[] args) {

        //go to url
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/forgot-password.html");

        //1. "Forgot Password" text
        WebElement forgotPasswordText = driver.findElement(By.tagName("h2"));
        String forgotPasswordTextText = forgotPasswordText.getText();
        //System.out.println(forgotPasswordTextText);

        if(FORGOT_PASSWORD_HEADER.equalsIgnoreCase(forgotPasswordTextText)){
            System.out.println("Expected forgot password header: \"" + FORGOT_PASSWORD_HEADER + "\", matches actual text \"" + forgotPasswordTextText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected forgot password header: \"" + FORGOT_PASSWORD_HEADER + "\", does not match actual text \"" + forgotPasswordTextText + "\" => TEST FAILED");
        }

        //2. Email text
        WebElement emailText = driver.findElement(By.cssSelector("label[for='email']"));
        String emailTextText = emailText.getText();
        //System.out.println(emailTextText);

        if(EMAIL_TEXT.contains(emailTextText)){
            System.out.println("Expected email text: \"" + EMAIL_TEXT + "\", matches actual text \"" + emailTextText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected email text: \"" + EMAIL_TEXT + "\", does not match actual text \"" + emailTextText + "\" => TEST FAILED");
        }


        //3. Email input
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        String emailInputId = emailInput.getAttribute("id");
        //System.out.println(emailInputId);

        if(EMAIL_INPUT_ID.contains(emailInputId)){
            System.out.println("Expected email id: \"" + EMAIL_INPUT_ID + "\", matches actual id \"" + emailInputId + "\" => TEST PASSED");
        } else {
            System.out.println("Expected email id: \"" + EMAIL_INPUT_ID + "\", does not match actual id \"" + emailInputId + "\" => TEST FAILED");
        }

        //4. retrieve password button
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        String buttonType = button.getAttribute("type");
        //System.out.println(buttonType);

        if(RETRIEVE_PASSWORD_BUTTON_TEXT.contains(buttonType)){
            System.out.println("Expected button text: \"" + RETRIEVE_PASSWORD_BUTTON_TEXT + "\", matches actual text \"" + buttonType + "\" => TEST PASSED");
        } else {
            System.out.println("Expected button text: \"" + POWERED_BY_LOOPCAMP_TEXT + "\", does not match actual text \"" + buttonType + "\" => TEST FAILED");
        }


        //5. powered by LOOPCAMP
        WebElement poweredByLoopcampDiv = driver.findElement(By.cssSelector("div[style='text-align: center;']"));
        String poweredByLoopcampText = poweredByLoopcampDiv.getText();
        //System.out.println(poweredByLoopcampText);

        if(POWERED_BY_LOOPCAMP_TEXT.equalsIgnoreCase(poweredByLoopcampText)){
            System.out.println("Expected powered by loopcamp text: \"" + POWERED_BY_LOOPCAMP_TEXT + "\", matches actual text \"" + poweredByLoopcampText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected powered by loopcamp text: \"" + POWERED_BY_LOOPCAMP_TEXT + "\", does not match actual text \"" + poweredByLoopcampText + "\" => TEST FAILED");
        }

        driver.quit();
    }
}
