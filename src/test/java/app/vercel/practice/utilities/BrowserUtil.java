package app.vercel.practice.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserUtil {

    /**
     * Method to run Thread.sleep. For taking screenshots and troubleshooting ONLY.
     * @param durationInSeconds Seconds to keep thread asleep
     */
    public static void makeThreadSleep(int durationInSeconds) {
        try {
            Thread.sleep(Duration.ofSeconds(durationInSeconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void implicitWait(int durationInSeconds){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(durationInSeconds));
    }
    public static boolean doesElementExist(WebElement element){
        boolean doesElementExist = true;
        try{
            implicitWait(5);
            element.getText();
        } catch (NoSuchElementException e){
            doesElementExist = false;
        }
        return doesElementExist;
    }

    /**
     * Checks to see if URL is valid, taken from Oracle Java tutorial
     * @param url String for URL
     * @return boolean true if URL is valid; false if not valid
     */
    public static boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
