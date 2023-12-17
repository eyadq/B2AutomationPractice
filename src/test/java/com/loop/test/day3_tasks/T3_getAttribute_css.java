package com.loop.test.day3_tasks;

import com.loop.test.utilities.DocuportConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class T3_getAttribute_css {

    public static void main(String[] args) throws InterruptedException {

        // go to url
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://beta.docuport.app");

        //identify logo with css
        WebElement logo = driver.findElement(By.cssSelector("img[src=\"/img/logo.d7557277.svg\"]"));
        String docuport =  logo.getAttribute("alt");

        if (DocuportConstants.LOGO_DOCUPORT.equalsIgnoreCase(docuport)){
            System.out.println("Expected logo text: \"" + DocuportConstants.LOGO_DOCUPORT + "\", matches actual logo text: \""  + docuport + "\" => TEST PASSED");
        } else {
            System.out.println("Expected logo text: \"" + DocuportConstants.LOGO_DOCUPORT + "\", DOES NOT MATCH actual logo text: \""  + docuport + "\" => TEST FAILED");
        }

        //locate username with css
        WebElement userName = driver.findElement(By.cssSelector("input[id='input-14']"));
        String usernameType = userName.getAttribute("type");

        if(DocuportConstants.TYPE_OF_INPUT_USERNAME.equalsIgnoreCase(usernameType)){
            System.out.println("Expected username input type: \"" + DocuportConstants.TYPE_OF_INPUT_USERNAME + "\", matches actual type \"" + usernameType + "\" => TEST PASSED");
        } else {
            System.out.println("Expected username input type: \"" + DocuportConstants.TYPE_OF_INPUT_USERNAME + "\", does not match actual type \"" + usernameType + "\" => TEST FAILED");
        }

        //locate password with css
        WebElement userPassword = driver.findElement(By.cssSelector("input[id='input-15']"));
        String passwordType = userPassword.getAttribute("type");

        if(DocuportConstants.TYPE_OF_INPUT_PASSWORD.equalsIgnoreCase(passwordType)){
            System.out.println("Expected password input type: \"" + DocuportConstants.TYPE_OF_INPUT_PASSWORD + "\", matches actual type \"" + passwordType + "\" => TEST PASSED");
        } else {
            System.out.println("Expected password input type: \"" + DocuportConstants.TYPE_OF_INPUT_PASSWORD + "\", does not match actual type \"" + passwordType + "\" => TEST FAILED");
        }

        //locate login with css
        WebElement buttonLogin = driver.findElement(By.cssSelector("span[class='v-btn__content']"));
        String buttonLoginText = buttonLogin.getText();

        if(DocuportConstants.LOGIN_BUTTON_TEXT.equalsIgnoreCase(buttonLoginText)){
            System.out.println("Expected login button text: \"" + DocuportConstants.LOGIN_BUTTON_TEXT + "\", matches actual type \"" + buttonLoginText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected login button text: \"" + DocuportConstants.LOGIN_BUTTON_TEXT + "\", does not match actual type \"" + buttonLoginText + "\" => TEST FAILED");
        }

        //login to docuport
        userName.sendKeys(DocuportConstants.USERNAME_CLIENT);
        userPassword.sendKeys(DocuportConstants.PASSWORD_CLIENT);
        buttonLogin.click();

        //need to click on "Continue" button once logged in
        Thread.sleep(3000);
        List<WebElement> buttons = driver.findElements(By.cssSelector("span[class='v-btn__content']"));
        WebElement buttonContinue = driver.findElement(By.cssSelector("span[class='v-btn__content']"));
        for(WebElement element : buttons){
            String buttonText = element.getText();
            if(buttonText.equals("Continue")){
                buttonContinue = element;
            }
        }
        buttonContinue.click();

        //home icon
        WebElement homeIcon = driver.findElement(By.cssSelector("img[src='/img/logo.d7557277.svg']"));
        String homeIconText = homeIcon.getAttribute("alt");

        if(DocuportConstants.LOGO_DOCUPORT.equalsIgnoreCase(homeIconText)){
            System.out.println("Expected home logo text: \"" + DocuportConstants.LOGO_DOCUPORT + "\", matches actual type \"" + homeIconText + "\" => TEST PASSED");
        } else {
            System.out.println("Expected home logo text: \"" + DocuportConstants.LOGO_DOCUPORT + "\", does not match actual type \"" + homeIconText + "\" => TEST FAILED");
        }
        driver.quit();
    }

    }
