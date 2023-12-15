package com.loop.test.vercel;

import com.loop.test.utilities.FileUtil;
import com.loop.test.utilities.LogUtil;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;

import static org.testng.internal.Utils.log;

public class BrokenImages extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/broken-images.html";
    String filePathActual; //Determined in code

    @Test
    public void testImages() {
        driver.get(pageURL);
        WebElement imageContainer = driver.findElement(By.xpath("//div[@class='example']"));
        List<WebElement> images = imageContainer.findElements(By.tagName("img"));

        int count = 0;
        for (WebElement element : images) {
            String link = element.getAttribute("src");
            String fileExtension = link.substring(link.lastIndexOf('.'));
            if (!fileExtension.equals(".jpg")) {
                LogUtil.logPrintMatch(fileExtension, "jpg", "image " + count + " file type");
                //Assert.assertNotEquals(fileExtension, "jpg", "image " + count + " file type");
            } else {


                filePathActual = FileUtil.downloadFile(link);
                String checksumActual = FileUtil.getChecksum(filePathActual);

                String filePathExpected = FileUtil.getExpectedFilePathFromURL(link);
                String checksumExpected = FileUtil.getChecksum(filePathExpected);

                SimpleSwing.showImage(filePathActual, filePathExpected);

                Assert.assertEquals(checksumActual, checksumExpected, "checksum of image");
            }

            count++;
        }

    }


}