package com.loop.test.day7_tasks;

import com.loop.test.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class iframePractice extends TestBase {

    @Test
    public void left(){

        driver.get("https://loopcamp.vercel.app/nested-frames.html");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement left = driver.findElement(By.xpath("//*[contains(text(), 'LEFT')]"));
        String actualLeft = left.getText();

        Assert.assertEquals(actualLeft, "LEFT", "text on left iframe");

    }

    @Test
    public void right(){
        driver.get("https://loopcamp.vercel.app/nested-frames.html");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        WebElement right = driver.findElement(By.xpath("//*[contains(text(), 'RIGHT')]"));
        String actualright = right.getText();

        Assert.assertEquals(actualright, "RIGHT", "text on right iframe");

    }

    @Test
    public void middle(){

        driver.get("https://loopcamp.vercel.app/nested-frames.html");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        WebElement right = driver.findElement(By.xpath("//*[contains(text(), 'MIDDLE')]"));
        String actualmiddle = right.getText();

        Assert.assertEquals(actualmiddle, "MIDDLE", "text on middle iframe");

    }

    @Test
    public void bottom(){

        driver.get("https://loopcamp.vercel.app/nested-frames.html");

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-bottom");
        WebElement bottom = driver.findElement(By.xpath("//*[contains(text(), 'BOTTOM')]"));
        String actualBottom = bottom.getText();
        Assert.assertEquals(actualBottom, "BOTTOM", "text of bottom frame");

    }
}
