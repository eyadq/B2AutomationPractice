package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.loop.test.vercel.SimpleSwing.showImage;

public class _5_BrokenImages {

    public static void run(WebDriver driver){
        driver.manage().window().maximize();
        //     //img[@src='img/avatar-blank.jpg']
        driver.get("https://loopcamp.vercel.app/broken-images.html");

        WebElement imageContainer = driver.findElement(By.xpath("//div[@class='example']"));
        List<WebElement> images = imageContainer.findElements(By.tagName("img"));

        int count = 0;
        for (WebElement element : images){
            String link = element.getAttribute("src");
            String fileExtension = link.substring(link.lastIndexOf('.') + 1);
            if(!fileExtension.equals("jpg")){
                HelperMethods.logPrintMatch("image " + count + " file type", fileExtension, "jpg");;
            } else {
                SimpleSwing.showImage(link, VercelConstants.BROKEN_IMAGES_ACTUAL_FILE_PATH);
                HelperMethods.logPrintFileMatch("checksum of image " + count, link, VercelConstants.BROKEN_IMAGES_ACTUAL_FILE_PATH);
            }
            count++;
        }


    }
    public static void main(String[] args) throws MalformedURLException {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        run(driver);

    }
}