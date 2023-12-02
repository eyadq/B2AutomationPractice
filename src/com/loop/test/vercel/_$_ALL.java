package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class _$_ALL {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebDriverFactory.getDriver("chrome");

        _0_Home.run(driver);

        System.out.println();
        System.err.println("1. No A/B Test test");
        _1_NoABTest.run(driver);

        System.out.println();
        System.err.println("2. Add/Remove Element test");
        _2_AddRemoveElements.run(driver);

        System.out.println();
        System.err.println("3. Autocomplete test");
        _3_Autocomplete.run(driver);

        System.out.println();
        System.err.println("4. Basic Auth test");
        _4_BasicAuth.run(driver);
    }
}
