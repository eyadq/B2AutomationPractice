package com.loop.test.vercel;

import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class _$_VercelTestBase {


    WebDriver driver;
    public String pageURL = "";

    public static final String MESSAGE_MATCH = " actual value did not match the expected";

    public static final String IMAGE_URL_LOGO_LOOP = "https://loopcamp.vercel.app/img/logo.svg";
    public static final String IMAGE_URL_LOGO_ACADEMY = "https://loopcamp.vercel.app/img/logo-text.svg";
    //public static final String MAIN_BINARY_RESOURCE_DIR = "src\\com\\loop\\test\\vercel\\data\\";
    public static final String MAIN_BINARY_RESOURCE_DIR = "resources/";

    public static final String MAIN_FOOTER_POWERED_BY_LOOPCAMP = "Powered by LOOPCAMP";

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(pageURL);
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }
}
