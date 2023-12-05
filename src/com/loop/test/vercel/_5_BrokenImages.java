package com.loop.test.vercel;

import com.loop.test.utilities.Enums;
import com.loop.test.utilities.HelperMethods;
import com.loop.test.utilities.VercelConstants;
import com.loop.test.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
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
                HelperMethods.logPrintMatch(fileExtension, "jpg", "image " + count + " file type");
                //Assert.assertNotEquals(fileExtension, "jpg", "image " + count + " file type");
            } else {
                SimpleSwing.showImage(link, VercelConstants.BROKEN_IMAGES_ACTUAL_FILE_PATH);

                String fileNameActual = link.substring(link.lastIndexOf('/')+1 , link.lastIndexOf('.')) + "-actual";
                String filePath = "src\\com\\loop\\test\\vercel\\data\\" + fileNameActual + fileExtension;

                String checksumActual = HelperMethods.checksum(Enums.ImageType.URL, link);
                String checksumExpected = HelperMethods.checksum(Enums.ImageType.FILE, filePath);
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