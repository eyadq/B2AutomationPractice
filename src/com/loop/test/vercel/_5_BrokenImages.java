package com.loop.test.vercel;

import com.loop.test.utilities.FileUtil;
import com.loop.test.utilities.LogUtil;
import com.loop.test.utilities.constants.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.internal.Utils.log;

public class _5_BrokenImages {

    WebDriver driver;
    public String pageURL = "https://loopcamp.vercel.app/broken-images.html";

    @Test
    public void testImages() {
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
                SimpleSwing.showImage(link, VercelConstants.BROKEN_IMAGES_ACTUAL_FILE_PATH);

                String filePathActual = FileUtil.downloadFile(link);
                String checksumActual = FileUtil.getChecksum(filePathActual);

                String filePathExpected = FileUtil.getExpectedFilePathFromURL(link);
                String checksumExpected = FileUtil.getChecksum(filePathExpected);

                Assert.assertEquals(checksumActual, checksumExpected, "checksum of image");
            }

            count++;
        }

    }

        @BeforeClass
        public void setUp () {

        }

        @BeforeMethod
        public void setUpMethod () {
            driver = WebDriverFactory.getDriver("chrome");
            driver.manage().window().maximize();
            driver.get(pageURL);
        }

        @AfterMethod
        public void tearDownMethod () {
        driver.quit();
        }

        @AfterClass
        public void tearDown () {
        }


}