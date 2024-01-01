package com.loop.test.day9_tasks;

import com.github.javafaker.Faker;
import com.loop.test.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.loop.test.day9_tasks.RegistationEnums.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static com.loop.test.day9_tasks.RegistationEnums.creditCardNumbersForTestingSoftware;

public class Registration {

    Faker faker;
    Random random;
    WebDriverWait wait;
    Actions actions;
    public static final String ERROR_KNOWN_TESTING_CARD = "Your card was declined. Your request was in live mode, but used a known test card.";

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get("https://app.docuport.app/company-formation/resident");

        faker = new Faker();
        random = new Random();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        Actions actions = new Actions(Driver.getDriver());
    }
    @Test
    public void testRegistration() {
        //createEnum("//label[.='Jurisdiction *']//following-sibling::input", "//div[@class='v-menu__content theme--light menuable__content__active v-autocomplete__content']");

        //All indexes are here to minimize risk from code changes
        List<String> forms = new ArrayList<>();
        forms.add("(//form[@novalidate='novalidate'])[1]");
        forms.add("(//form[@novalidate='novalidate'])[2]");
        forms.add("(//form[@novalidate='novalidate'])[3]");
        forms.add("((//form[@novalidate='novalidate'])[3]//div)[41]");

        //First page
        String doingBusinessAs =  faker.app().name() + " " + faker.commerce().productName();
        String name = faker.name().firstName() + " " + faker.name().lastName();
        selectRadioButton(EntityType.values()[random.nextInt(EntityType.values().length-1)].getEntityType());
        selectRadioButton(FormationState.values()[random.nextInt(FormationState.values().length - 1)].getStateName());
        fillInput(forms.get(0), "Legal Company Name  *", doingBusinessAs + " Group");
        fillInput(forms.get(0), "DBA (Doing Business As)", doingBusinessAs);
        fillInput(forms.get(0), "Industry *", IndustryType.values()[random.nextInt(IndustryType.values().length)].getOptioName());
        fillInput(forms.get(0), "First name *", name.substring(0, name.indexOf(" ")));
        fillInput(forms.get(0), "Last name *", name.substring(name.indexOf(" ") + 1));
        fillInput(forms.get(0), "Email address *", name.replace(" ", "") + "@" + doingBusinessAs.replace(" ", "") + ".com");
        fillInput(forms.get(0), "Phone number *", "+1" + faker.numerify("###-###-###"));
        fillInput(forms.get(0), "Fax", "+1" + faker.numerify("###-###-###"));
        Driver.getDriver().findElement(By.xpath(forms.get(0))).findElement(By.xpath("//button[@type='submit']")).click();

        //Second page
        selectRadioButton(AgentType.values()[random.nextInt(AgentType.values().length-1)].getAgentType());
        fillInput(forms.get(1), "First name *", faker.name().firstName());
        fillInput(forms.get(1), "Last name *", faker.name().lastName());
        fillInput(forms.get(1), "Middle name", faker.name().firstName());
        fillInput(forms.get(1), "Address line 1 *", faker.address().streetAddress());
        fillInput(forms.get(1), "City *", faker.address().city());
        fillInput(forms.get(1), "Jurisdiction *", whichJurisdiction(forms.get(1)));
        fillInput(forms.get(1), "Zip code *", faker.address().zipCode());
        fillInput(forms.get(1), "Email address", "support" + "@" + doingBusinessAs.replace(" ", "") + ".com");
        fillInput(forms.get(1), "Phone number *", "+1" + faker.numerify("###-###-###"));
        Driver.getDriver().findElement(By.xpath("(//form[@novalidate='novalidate'])[2]//button[@type='submit']")).click();

        //Third page top
        fillInput(forms.get(2), "First name *", faker.name().firstName());
        fillInput(forms.get(2), "Last name *", faker.name().lastName());
        fillInput(forms.get(2), "Share Ownership (%) *", "" + random.nextInt(100));

        //Third page bottom
        fillInput(forms.get(3), "SSN of an Organizing Member *", faker.idNumber().ssnValid());
        fillInput(forms.get(3), "First name *", faker.name().firstName());
        fillInput(forms.get(3), "Last name *", faker.name().lastName());
        Driver.getDriver().findElement(By.xpath("//span[.=' Go to payment ']//parent::button")).click();

        //Payment page
        fillPaymentInput("Secure card number input frame", "Card number", "" + creditCardNumbersForTestingSoftware[random.nextInt(creditCardNumbersForTestingSoftware.length-1)] );
        fillPaymentInput("Secure expiration date input frame", "MM/YY", new SimpleDateFormat("MM/yy").format(faker.date().between(new Date(2025, 1, 1), new Date(2073, 6, 1))) );
        fillPaymentInput("Secure CVC input frame", "CVC", faker.numerify("###"));
        Driver.getDriver().findElement(By.xpath("//button[contains(. , ' Pay ')]")).click();

        WebElement messageBox = wait.until(d -> Driver.getDriver().findElement(By.xpath("//span[contains(., 'known test card')]")));
        Assert.assertEquals(messageBox.getText(), ERROR_KNOWN_TESTING_CARD);


    }

    public void selectRadioButton(String labelText){
        String xpath = "//label[text()='" + labelText + "']//preceding-sibling::div";
        Driver.getDriver().findElement(By.xpath(xpath)).click();
    }

    public void fillInput(String form, String labelText, String inputText){
        String xpath = form + "//label[text()='" + labelText + "']//following-sibling::input";
        Driver.getDriver().findElement(By.xpath(xpath)).sendKeys(inputText);
        if(labelText.equals("Industry *") || labelText.equals("Jurisdiction *")){
            Driver.getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.ARROW_DOWN);
            Driver.getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
        }
    }

    public void fillPaymentInput(String iframeTitle, String inputPlaceHolderText, String data){
        Driver.getDriver().switchTo().frame(Driver.getDriver().findElement(By.xpath("//iframe[@title='" + iframeTitle + "']")));
        Driver.getDriver().findElement(By.xpath("//input[@placeholder='" + inputPlaceHolderText + "']"))
                .sendKeys(data);
        Driver.getDriver().switchTo().parentFrame();
    }

    public void createEnum(String xpathInput, String xpathList) {
        WebElement input = Driver.getDriver().findElement(By.xpath(xpathInput));
        input.click();

        List<WebElement> options = Driver.getDriver().findElements(By.xpath(xpathList));

        String last = options.get(options.size()-1).getText();
        String currentLast = last;

        do {
            actions.scrollToElement(options.get(options.size()-1)).perform();
            options = Driver.getDriver().findElements(By.xpath("//div[@class='v-list-item__title']"));
            last = currentLast;
            currentLast = options.get(options.size()-1).getText();
        } while(!last.equals(currentLast));

        System.out.println("enum Jurisdiction {");
        for (int i = 0; i < options.size(); i++) {
            String endOfLine = (i != options.size()-1) ? "," : ";";

            System.out.println(options.get(i).getText().toUpperCase().replace(" ", "_") + "(\"" + options.get(i).getText() + "\")" + endOfLine);
        }
        System.out.println("private String jurisdictionSetting;");
        System.out.println("private  Jurisdiction(String optionName) {this.jurisdictionSetting = jurisdictionSetting;}");
        System.out.println("public String jurisdictionSetting() {return jurisdictionSetting;}\n}");


    }

    public String whichJurisdiction(String form) {
        WebElement input = Driver.getDriver().findElement(By.xpath("//label[.='Jurisdiction *']//following-sibling::input"));
        input.click();

        Actions actions = new Actions(Driver.getDriver());
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active v-autocomplete__content']"));


        if(options.get(0).getText().isEmpty()){
            String last = options.get(options.size()-1).getText();
            String currentLast = last;
            do {
                actions.scrollToElement(options.get(options.size()-1)).perform();
                options = Driver.getDriver().findElements(By.xpath("//div[@class='v-list-item__title']"));
                last = currentLast;
                currentLast = options.get(options.size()-1).getText();
            } while(!last.equals(currentLast));
        }

        //The options list contains many lines that are "Empty" (only contain spaces). index == FIRST non empty line
        int index = 0;
        for (WebElement option: options){
            if(!option.getText().isEmpty())
                break;
            index++;
        }

        String jurList = options.get(index).getText().trim().substring(0, 3);
        String location = "";


        if(jurList.equals("All")){
            int length = Jurisdiction3.values().length-1;
            int randomNumber = random.nextInt(length);
            location = Jurisdiction3.values()[randomNumber].getCounty();
        } else if(jurList.equals("Acc")){
            int length = Jurisdiction2.values().length-1;
            int randomNumber = random.nextInt(length);
            location = Jurisdiction2.values()[randomNumber].getCounty();
        } else {
            location = Jurisdiction.values()[random.nextInt(Jurisdiction.values().length-1)].getCounty();
        }
        return location;
    }

}
