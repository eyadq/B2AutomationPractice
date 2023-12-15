package app.vercel.test.pages;

public class BasicAuth extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/index.html/basic-auth.html";

    public  void run() {
        driver.get(pageURL);
        //TODO: Dont forget to implement
    }
}
