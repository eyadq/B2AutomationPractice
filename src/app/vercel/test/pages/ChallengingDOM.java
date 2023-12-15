package app.vercel.test.pages;

import org.testng.annotations.*;

public class ChallengingDOM extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/challenging-dom.html";

    @Test
    public void run(){
        driver.get(pageURL);
        //TODO: Dont forget to implement
    }
}
