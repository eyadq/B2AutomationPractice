package com.loop.test.day8_tasks;

import com.loop.test.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PizzaOrders extends TestBase {

    private static final String JACKSON_CITY = "Starberry, UT";
    private static final String GRAY_EXP = "02/24";

    @Test
    public void testTable(){

        driver.get("https://loopcamp.vercel.app/web-tables.html");

        String jacksonName = "Samuel Jackson";
        String xpathJacksonCity = getPizzaOrderFieldUsingName(jacksonName, PizzaTableField.CITY);
        String jacksonCity = driver.findElement(By.xpath(xpathJacksonCity)).getText();
        Assert.assertEquals(jacksonCity, JACKSON_CITY, "City Jackson lives in");

        Assert.assertEquals(driver.findElement(By.xpath(getPizzaOrderFieldUsingName("Alexandra Gray", PizzaTableField.Exp)))
                .getText() , GRAY_EXP, "Exp of Alexandra Gray");


    }

    enum PizzaTableField{
        PIZZA_TYPE(1),
        AMOUNT(2),
        DATE(3),
        STREET(4),
        CITY(5),
        STATE(6),
        ZIP(7),
        CARD(8),
        CARD_NUMBER(9),
        Exp(10);

        private int fieldOffset;

        private PizzaTableField(int fieldOffset){
            this.fieldOffset = fieldOffset;
        }

        public int getFieldOffset() {
            return fieldOffset;
        }
    }
    public String getPizzaOrderFieldUsingName(String name, PizzaTableField wantedField){
        String xpath = "//td[contains(text(), '" + name + "')]";
        WebElement nameTD = driver.findElement(By.xpath(xpath));

        String additionToXpath = "//following-sibling::td";

        for (int i = 0; i < wantedField.getFieldOffset(); i++) {
            xpath += additionToXpath;
        }

        return xpath;
    }
}
