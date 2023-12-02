package com.loop.test.vercel;

import com.loop.test.utilities.HelperMethods;
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

import static com.loop.test.vercel.SimpleSwing.showImage;

public class _5_BrokenImages {

    public static void run(WebDriver driver){
        driver.manage().window().maximize();
        //     //img[@src='img/avatar-blank.jpg']
        driver.get("https://loopcamp.vercel.app/broken-images.html");
        WebElement nonBrokenImage = driver.findElement(By.xpath("//img[@src='img/avatar-blank.jpg']"));

        String urlImage = nonBrokenImage.getAttribute("src");

        SimpleSwing.showImage(urlImage, "src/com/loop/test/vercel/data/avatar-blank.jpg");
        HelperMethods.logPrintFileMatch("checksum of image", urlImage, "src/com/loop/test/vercel/data/avatar-blank.jpg");


    }
    public static void main(String[] args) throws MalformedURLException {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        run(driver);

    }
}