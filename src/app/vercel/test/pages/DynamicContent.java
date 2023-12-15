package app.vercel.test.pages;

import org.testng.annotations.Test;

public class DynamicContent extends VercelTestBase{

    private static final String pageURL = ("https://loopcamp.vercel.app/ab-test.html");
    private static final String HEADER_TEXT = "Dynamic Content";
    private static final String PARAGRAPH_ABOVE_TEXT = "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.";
    private static final String PARAGRAPH_BELOW_TEXT = "Dynamic Content";


    @Test
    public void test(){
        driver.get(pageURL);
        //TODO: Implement
    }


}
