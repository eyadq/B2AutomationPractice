package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import app.vercel.practice.utilities.FileUtil;
import app.vercel.practice.utilities.LogUtil;
import app.vercel.practice.utilities.SwingUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.internal.Utils.log;

public class BrokenImages extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/broken-images.html";
    String filePathActual; //Determined in code
    InnerPageFactory factory;

    class InnerPageFactory{
        @FindBy(xpath = "//div[@class='example']")
        WebElement imageContainer;
        InnerPageFactory(){PageFactory.initElements(Driver.getDriver(), this);}

        List<WebElement> getImages(){
            return factory.imageContainer.findElements(By.tagName("img"));
        }
    }

    @BeforeMethod
    public void setupMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }
    @Test
    public void testAllImages() {

        int count = 0;
        for (WebElement element : factory.getImages()) {
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

                SwingUtil.showImage(filePathActual, filePathExpected);

                Assert.assertEquals(checksumActual, checksumExpected, "checksum of image");
            }

            count++;
        }

    }




}