package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ChallengingDOM extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/challenging-dom.html";

    @Test
    public void run(){
        driver.get(pageURL);
        //TODO: Dont forget to implement
    }
}
