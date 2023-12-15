package com.loop.test.vercel;

import org.openqa.selenium.WebDriver;

public class BasicAuth extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/index.html/basic-auth.html";

    public  void run() {
        driver.get(pageURL);
        //TODO: Dont forget to implement
    }
}
