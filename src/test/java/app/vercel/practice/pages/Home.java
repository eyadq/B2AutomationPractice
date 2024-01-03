package app.vercel.practice.pages;

import app.vercel.practice.base.VercelTestBase;
import app.vercel.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Home extends VercelTestBase {

    private static final String pageURL = "https://loopcamp.vercel.app/index.html";
    public static final String IMAGE_URL_LOGO_LOOP = "https://loopcamp.vercel.app/img/logo.svg";
    public static final String IMAGE_URL_LOGO_ACADEMY = "https://loopcamp.vercel.app/img/logo-text.svg";

    public static final String MAIN_FOOTER_POWERED_BY_LOOPCAMP = "Powered by LOOPCAMP";
    private static final String HEADER_TEST_AUTOMATION_PRACTICE = "Test Automation Practice";
    private static final String HEADER_AVAILABLE_EXAMPLES = "Available Examples";
    private static final String LIST_OF_EXAMPLES =
            "A/B Testing\n" +
            "Add/Remove Elements\n" +
            "Autocomplete\n" +
            "Basic Auth (user and pass: admin)\n" +
            "Broken Images\n" +
            "Challenging DOM\n" +
            "Checkboxes\n" +
            "Context Menu\n" +
            "Disappearing Elements\n" +
            "Drag and Drop\n" +
            "Drag and Drop Circles\n" +
            "Dropdown\n" +
            "Dynamic Content\n" +
            "Dynamic Controls\n" +
            "Dynamic Loading\n" +
            "Entry Ad\n" +
            "Exit Intent\n" +
            "File Download\n" +
            "File Upload\n" +
            "Floating Menu\n" +
            "Forgot Password\n" +
            "Form Authentication\n" +
            "Frames\n" +
            "Geolocation\n" +
            "Horizontal Slider\n" +
            "Hovers\n" +
            "Infinite Scroll\n" +
            "Inputs\n" +
            "JQuery UI Menus\n" +
            "JavaScript Alerts\n" +
            "JavaScript onload event error\n" +
            "Key Presses\n" +
            "Large & Deep DOM\n" +
            "Multiple Buttons\n" +
            "Multiple Windows\n" +
            "Nested Frames\n" +
            "New tab\n" +
            "Notification Messages\n" +
            "Radio Buttons\n" +
            "Redirect Link\n" +
            "Registration Form\n" +
            "Secure File Download\n" +
            "Shifting Content\n" +
            "Sign Up For Mailing List\n" +
            "Slow Resources\n" +
            "Sortable Data Tables\n" +
            "Status Codes\n" +
            "Typos\n" +
            "WYSIWYG Editor\n" +
            "Web Tables";
    private static final String HOME_LINKS =
                    "\n\tA/B Testing has link of https://loopcamp.vercel.app/ab-test.html\n" +
                    "\tAdd/Remove Elements has link of https://loopcamp.vercel.app/add-remove-elements.html\n" +
                    "\tAutocomplete has link of https://loopcamp.vercel.app/autocomplete.html\n" +
                    "\tBasic Auth (user and pass: admin) has link of https://loopcamp.vercel.app/basic-auth.html\n" +
                    "\tBroken Images has link of https://loopcamp.vercel.app/broken-images.html\n" +
                    "\tChallenging DOM has link of https://loopcamp.vercel.app/challenging-dom.html\n" +
                    "\tCheckboxes has link of https://loopcamp.vercel.app/checkboxes.html\n" +
                    "\tContext Menu has link of https://loopcamp.vercel.app/context-menu.html\n" +
                    "\tDisappearing Elements has link of https://loopcamp.vercel.app/disappearing-elements.html\n" +
                    "\tDrag and Drop has link of https://loopcamp.vercel.app/drag-and-drop.html\n" +
                    "\tDrag and Drop Circles has link of https://loopcamp.vercel.app/drag-and-drop-circles.html\n" +
                    "\tDropdown has link of https://loopcamp.vercel.app/dropdown.html\n" +
                    "\tDynamic Content has link of https://loopcamp.vercel.app/dynamic-content.html\n" +
                    "\tDynamic Controls has link of https://loopcamp.vercel.app/dynamic-controls.html\n" +
                    "\tDynamic Loading has link of https://loopcamp.vercel.app/dynamic_loading.html\n" +
                    "\tEntry Ad has link of https://loopcamp.vercel.app/entry-ad.html\n" +
                    "\tExit Intent has link of https://loopcamp.vercel.app/exit-intent.html\n" +
                    "\tFile Download has link of https://loopcamp.vercel.app/download.html\n" +
                    "\tFile Upload has link of https://loopcamp.vercel.app/upload.html\n" +
                    "\tFloating Menu has link of https://loopcamp.vercel.app/floating-menu.html\n" +
                    "\tForgot Password has link of https://loopcamp.vercel.app/forgot-password.html\n" +
                    "\tForm Authentication has link of https://loopcamp.vercel.app/login.html\n" +
                    "\tFrames has link of https://loopcamp.vercel.app/frames.html\n" +
                    "\tGeolocation has link of https://loopcamp.vercel.app/geolocation.html\n" +
                    "\tHorizontal Slider has link of https://loopcamp.vercel.app/horizontal-slider.html\n" +
                    "\tHovers has link of https://loopcamp.vercel.app/hovers.html\n" +
                    "\tInfinite Scroll has link of https://loopcamp.vercel.app/infinite_scroll/index.html\n" +
                    "\tInputs has link of https://loopcamp.vercel.app/inputs.html\n" +
                    "\tJQuery UI Menus has link of https://loopcamp.vercel.app/jqueryui/menu.html\n" +
                    "\tJavaScript Alerts has link of https://loopcamp.vercel.app/javascript-alerts.html\n" +
                    "\tJavaScript onload event error has link of https://loopcamp.vercel.app/javascript-error.html\n" +
                    "\tKey Presses has link of https://loopcamp.vercel.app/key-presses.html\n" +
                    "\tLarge & Deep DOM has link of https://loopcamp.vercel.app/large.html\n" +
                    "\tMultiple Buttons has link of https://loopcamp.vercel.app/multiple-buttons.html\n" +
                    "\tMultiple Windows has link of https://loopcamp.vercel.app/windows.html\n" +
                    "\tNested Frames has link of https://loopcamp.vercel.app/nested-frames.html\n" +
                    "\tNew tab has link of https://loopcamp.vercel.app/open-new-tab.html\n" +
                    "\tNotification Messages has link of https://loopcamp.vercel.app/notification-message-rendered.html\n" +
                    "\tRadio Buttons has link of https://loopcamp.vercel.app/radio-buttons.html\n" +
                    "\tRedirect Link has link of https://loopcamp.vercel.app/redirector.html\n" +
                    "\tRegistration Form has link of https://loopcamp.vercel.app/registration_form.html\n" +
                    "\tSecure File Download has link of https://loopcamp.vercel.app/download_secure.html\n" +
                    "\tShifting Content has link of https://loopcamp.vercel.app/shifting_content.html\n" +
                    "\tSign Up For Mailing List has link of https://loopcamp.vercel.app/sign_up.html\n" +
                    "\tSlow Resources has link of https://loopcamp.vercel.app/slow.html\n" +
                    "\tSortable Data Tables has link of https://loopcamp.vercel.app/tables.html\n" +
                    "\tStatus Codes has link of https://loopcamp.vercel.app/status_codes.html\n" +
                    "\tTypos has link of https://loopcamp.vercel.app/typos.html\n" +
                    "\tWYSIWYG Editor has link of https://loopcamp.vercel.app/tinymce.html\n" +
                    "\tWeb Tables has link of https://loopcamp.vercel.app/web-tables.html";

    InnerPageFactory factory;
    class InnerPageFactory{
        @FindBy(xpath = "//img[@src='./img/logo.svg']")
        WebElement headerImageLoop;
        @FindBy(xpath = "//img[@src='./img/logo-text.svg']")
        WebElement headerImageAcademy;
        @FindBy(xpath = "//span[@class='h1y']")
        WebElement header;
        @FindBy(xpath = "//span[@class='h2 mb-4']")
        WebElement subHeader;
        @FindBy(css = "ul[class='list-group list-group-flush']")
        WebElement exampleList;
        @FindBy(css = "div[style='text-align: center;margin-bottom: 40px']")
        WebElement footer;
        InnerPageFactory(){
            PageFactory.initElements(Driver.getDriver(), this);}
        static List<WebElement> getPracticeExamplesText(){
            return Driver.getDriver().findElements(By.className("list-group-item"));
        }
        static List<WebElement> getPracticeExamplesLinks(){
            return Driver.getDriver().findElements(By.cssSelector("li[class='list-group-item']>a"));
        }
    }

    @BeforeMethod
    public void setUpMethod(){
        Driver.getDriver().get(pageURL);
        factory = new InnerPageFactory();
    }

    @Test
    public void testHeadersAndFooter(){
        Assert.assertEquals(factory.headerImageLoop.getAttribute("src"), IMAGE_URL_LOGO_LOOP, ":oop image link" + MESSAGE_MATCH);
        Assert.assertEquals(factory.headerImageAcademy.getAttribute("src"), IMAGE_URL_LOGO_ACADEMY, "Academy image link" + MESSAGE_MATCH);

        Assert.assertEquals(factory.header.getText(), HEADER_TEST_AUTOMATION_PRACTICE, "Test automation practice header text" + MESSAGE_MATCH);
        Assert.assertEquals(factory.subHeader.getText(), HEADER_AVAILABLE_EXAMPLES,  "Available practice header text" + MESSAGE_MATCH);

        Assert.assertEquals(factory.footer.getText(), MAIN_FOOTER_POWERED_BY_LOOPCAMP, "Powered by loopcamp footer text" + MESSAGE_MATCH);
    }

    @Test
    public void testBodyContent(){
        Assert.assertEquals(factory.exampleList.getText(), LIST_OF_EXAMPLES, "Text of home page content" + MESSAGE_MATCH);
        List<WebElement> listExamples = factory.getPracticeExamplesText();
        List<WebElement> itemLinks = factory.getPracticeExamplesLinks();

        String actual = "";
        for (int i = 0; i < itemLinks.size(); i++)
            actual += "\n\t" + listExamples.get(i).getText() + " has link of " + itemLinks.get(i).getAttribute("href");
        Assert.assertEquals(actual, HOME_LINKS, "Links for practice items" + MESSAGE_MATCH);
    }
}
