package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class _0_Home{

    public static void run() {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://loopcamp.vercel.app/index.html");

        System.out.println();
        System.err.println("Home Page test");

        WebElement logoLoop = driver.findElement(By.xpath("//img[@src='./img/logo.svg']"));
        WebElement logoAcademy= driver.findElement(By.xpath("//img[@src='./img/logo-text.svg']"));

        String imagePathLoopActual = logoLoop.getAttribute("src");;

        String imagePathAcademyActual = logoAcademy.getAttribute("src");

        HelperMethods.logPrintMatch("loop image link", imagePathLoopActual, VercelConstants.IMAGE_URL_LOGO_LOOP);

        HelperMethods.logPrintMatch("academy image link", imagePathAcademyActual, VercelConstants.IMAGE_URL_LOGO_ACADEMY);

        WebElement testAutomationPractice = driver.findElement(By.cssSelector("span[class='h1y']"));
        HelperMethods.logPrintMatch("test automation practice header text", testAutomationPractice.getText(), VercelConstants.MAIN_HEADER_TEST_AUTOMATION_PRACTICE);

        WebElement availableExamples = driver.findElement(By.cssSelector("span[class='h2 mb-4']"));
        HelperMethods.logPrintMatch("available practice header text", availableExamples.getText(), VercelConstants.MAIN_HEADER_AVAILABLE_EXAMPLES);

        WebElement poweredByLoopcamp = driver.findElement(By.cssSelector("div[style='text-align: center;margin-bottom: 40px']"));
        HelperMethods.logPrintMatch("powered by loopcamp footer text", poweredByLoopcamp.getText(), VercelConstants.MAIN_FOOTER_POWERED_BY_LOOPCAMP);

        WebElement unorderedList = driver.findElement(By.cssSelector("ul[class='list-group list-group-flush']"));
        System.out.println("");
        System.out.println("Here is the text for the unordered list element that makes the bulk of the page (it's just one long String)");
        System.out.println(unorderedList.getText());

        List<WebElement> listItems = driver.findElements(By.className("list-group-item"));

        List<WebElement> itemLinks = driver.findElements(By.cssSelector("li[class='list-group-item']>a"));

        System.out.println("");
        System.out.println("Here are the links for the list items");
        for (int i = 0; i < listItems.size(); i++) {
            System.out.println("\t"+ listItems.get(i).getText() + " has link of " + itemLinks.get(i).getAttribute("href"));
        }

        //Time to leave page
        //System.out.println(listItems.size()); //50
        Random random = new Random();
        itemLinks.get(random.nextInt(50)).click();

    }

    public static void main(String[] args) {
        run();
    }
}
