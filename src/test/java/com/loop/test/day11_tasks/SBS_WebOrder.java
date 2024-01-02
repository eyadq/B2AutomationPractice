package com.loop.test.day11_tasks;

import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SBS_WebOrder {

    public static final String USERNAME = "Tester";
    public static final String PASSWORD = "test";
    public static final String LOGIN_URL = "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx";

    SBSWebOrderLoginPage sbsWebOrderLoginPage;

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(LOGIN_URL);
        sbsWebOrderLoginPage = new SBSWebOrderLoginPage();
    }

    @Test
    public void testLinks(){

        sbsWebOrderLoginPage.username.sendKeys(USERNAME);
        sbsWebOrderLoginPage.password.sendKeys(PASSWORD);
        sbsWebOrderLoginPage.button.click();

        List<WebElement> links = Driver.getDriver().findElements(By.tagName("a"));
        System.out.println("There are " + links.size() + " number of links on this page. Here are their link texts:");
        for (WebElement link : links)
            System.out.println(link.getText());

    }

    class SBSWebOrderLoginPage {

        @FindBy(id = "ctl00_MainContent_username")
        WebElement username;

        @FindBy(id = "ctl00_MainContent_password")
        WebElement password;

        @FindBy(id = "ctl00_MainContent_login_button")
        WebElement button;

        SBSWebOrderLoginPage(){
            PageFactory.initElements(Driver.getDriver(), this);
        }
    }


}
