package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ChallengingDOM extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/challenging-dom.html";
    private static final String HEADER_TEXT = "Challenging DOM";
    private static final String PARAGRAPH_TEXT = "The hardest part in automated web testing is finding the best locators (e.g., ones that well named, unique, and unlikely to change). It's more often than not that the application you're testing was not built with this concept in mind. This example demonstrates that with unique IDs, a table with no helpful locators, and a canvas element.";
    private static final String[] BUTTON_TEXT = {"Xyzzy", "Garply", "Xyzzy"};

    private static String[][] TABLE
            = {
            {"Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action"},
            {"Iuvaret0", "Apeirian0", "Adipisci0", "Definiebas0", "Consequuntur0", "Phaedrum0", "edit delete" },
            {"Iuvaret1", "Apeirian1", "Adipisci1", "Definiebas1", "Consequuntur1", "Phaedrum1", "edit delete" },
            {"Iuvaret2", "Apeirian2", "Adipisci2", "Definiebas2", "Consequuntur2", "Phaedrum2", "edit delete" },
            {"Iuvaret3", "Apeirian3", "Adipisci3", "Definiebas3", "Consequuntur3", "Phaedrum3", "edit delete" },
            {"Iuvaret4", "Apeirian4", "Adipisci4", "Definiebas4", "Consequuntur4", "Phaedrum4", "edit delete" },
            {"Iuvaret5", "Apeirian5", "Adipisci5", "Definiebas5", "Consequuntur5", "Phaedrum5", "edit delete" },
            {"Iuvaret6", "Apeirian6", "Adipisci6", "Definiebas6", "Consequuntur6", "Phaedrum6", "edit delete" },
            {"Iuvaret7", "Apeirian7", "Adipisci7", "Definiebas7", "Consequuntur7", "Phaedrum7", "edit delete" },
            {"Iuvaret8", "Apeirian8", "Adipisci8", "Definiebas8", "Consequuntur8", "Phaedrum8", "edit delete" },
            {"Iuvaret9", "Apeirian9", "Adipisci9", "Definiebas9", "Consequuntur9", "Phaedrum9", "edit delete" }
    };
    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(tagName = "h3")
        WebElement header;
        @FindBy(xpath= "//p[contains(text(), 'The hardest')]")
        WebElement paragraph;
        @FindBy(xpath= "//th[text()='Lorem']//parent::tr")
        WebElement rowHeader;
        @FindBy(xpath= "//div[@class='large-2 columns buttons']")
        WebElement buttonContainer;
        @FindBy(id = "canvas")
        WebElement canvas;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static List<WebElement> getTableHeadRows(){
            return Driver.getDriver().findElements(By.xpath("//thead//child::tr//child::th"));
        }
        static List<WebElement> getTableBodyRows(){
            return Driver.getDriver().findElements(By.xpath("//tbody//child::tr"));
        }
        static String[][] getTable(){
            String[][] table = new String[11][7];

            List<WebElement> rowHeaderOptions = getTableHeadRows();
            for (int i = 0; i < table[0].length; i++) {
                table[0][i] = rowHeaderOptions.get(i).getText();
            }

            List<WebElement> tableBodyRows = getTableBodyRows();
            //i starts at 1 because row 0 is header row
            for (int i = 1; i < table.length; i++) {
                List<WebElement> tdList = tableBodyRows.get(i-1).findElements(By.tagName("td"));
                for (int j = 0; j < table[i].length; j++) {
                    table[i][j] = tdList.get(j).getText();
                }
            }
            return table;
        }
        static void printTable(String[][] table){
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }


    @Test
    public void testText(){
        Assert.assertEquals(factory.header.getText(), HEADER_TEXT, "Text of header" + MESSAGE_MATCH);
        Assert.assertEquals(factory.paragraph.getText(), PARAGRAPH_TEXT, "Text of paragraph" + MESSAGE_MATCH);

    }
    @Test
    public void testTable(){
        Assert.assertEquals(factory.getTable(), TABLE, "Entire table" + MESSAGE_MATCH);
    }

    @Test
    public void testCanvas(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        long answer = (long) js.executeScript("return result");
        //System.out.println(answer);

        Assert.assertFalse(factory.canvas == null, "Canvas did load correctly");
        Assert.assertTrue(answer > 0, "Canvas was given result of javascript correctly to paint");
    }

    @Test
    public void testButtons(){
        List<WebElement> buttons = factory.buttonContainer.findElements(By.tagName("a"));
        Assert.assertEquals(buttons.size(), 3, "Amount of buttons expected");
    }


}
