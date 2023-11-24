package com.loop.test.day3_tasks;

import com.loop.test.utilities.DocuportConstants;
import com.loop.test.utilities.VercelForgotPassword;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task0 {

    public static void main(String[] args) {

        //go to url
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/forgot-password.html");

        //1. "Forgot Password" text
        WebElement forgotPasswordText = driver.findElement(By.tagName("h2"));
        String forgotPasswordTextText = forgotPasswordText.getText();
        //System.out.println(forgotPasswordTextText);

        if(VercelForgotPassword.FORGOT_PASSWORD_HEADER.equalsIgnoreCase(forgotPasswordTextText)){
            System.out.println("Expected forgot password header: \"" + VercelForgotPassword.FORGOT_PASSWORD_HEADER + "\", matches actual text \"" + forgotPasswordTextText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected forgot password header: \"" + VercelForgotPassword.FORGOT_PASSWORD_HEADER + "\", does not match actual text \"" + forgotPasswordTextText + "\" => TEST FAILED");
        }

        //2. Email text
        WebElement emailText = driver.findElement(By.cssSelector("label[for='email']"));
        String emailTextText = emailText.getText();
        //System.out.println(emailTextText);

        if(VercelForgotPassword.EMAIL_TEXT.contains(emailTextText)){
            System.out.println("Expected email text: \"" + VercelForgotPassword.EMAIL_TEXT + "\", matches actual text \"" + emailTextText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected email text: \"" + VercelForgotPassword.EMAIL_TEXT + "\", does not match actual text \"" + emailTextText + "\" => TEST FAILED");
        }


        //3. Email input
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        String emailInputId = emailInput.getAttribute("id");
        //System.out.println(emailInputId);

        if(VercelForgotPassword.EMAIL_INPUT_ID.contains(emailInputId)){
            System.out.println("Expected email id: \"" + VercelForgotPassword.EMAIL_INPUT_ID + "\", matches actual id \"" + emailInputId + "\" => TEST PASSED");
        } else {
            System.out.println("Expected email id: \"" + VercelForgotPassword.EMAIL_INPUT_ID + "\", does not match actual id \"" + emailInputId + "\" => TEST FAILED");
        }

        //4. retrieve password button
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        String buttonType = button.getAttribute("type");
        //System.out.println(buttonType);

        if(VercelForgotPassword.RETRIEVE_PASSWORD_BUTTON_TEXT.contains(buttonType)){
            System.out.println("Expected button text: \"" + VercelForgotPassword.RETRIEVE_PASSWORD_BUTTON_TEXT + "\", matches actual text \"" + buttonType + "\" => TEST PASSED");
        } else {
            System.out.println("Expected button text: \"" + VercelForgotPassword.POWERED_BY_LOOPCAMP_TEXT + "\", does not match actual text \"" + buttonType + "\" => TEST FAILED");
        }


        //5. powered by LOOPCAMP
        WebElement poweredByLoopcampDiv = driver.findElement(By.cssSelector("div[style='text-align: center;']"));
        String poweredByLoopcampText = poweredByLoopcampDiv.getText();
        //System.out.println(poweredByLoopcampText);

        if(VercelForgotPassword.POWERED_BY_LOOPCAMP_TEXT.equalsIgnoreCase(poweredByLoopcampText)){
            System.out.println("Expected powered by loopcamp text: \"" + VercelForgotPassword.POWERED_BY_LOOPCAMP_TEXT + "\", matches actual text \"" + poweredByLoopcampText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected powered by loopcamp text: \"" + VercelForgotPassword.POWERED_BY_LOOPCAMP_TEXT + "\", does not match actual text \"" + poweredByLoopcampText + "\" => TEST FAILED");
        }

        driver.quit();
    }
}
