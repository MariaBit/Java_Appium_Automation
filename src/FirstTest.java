import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;



public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCompareTextInSearchField(){

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Text in the Search field does not equal to 'Search…'");
    }

    @Test
    public void testFindWordsInSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[1][contains(@text,'Java')]"),
                "First result on the page does not contain word 'Java'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.TextView[1][contains(@text,'Java')]"),
                "Second result on the page does not contain word 'Java'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView[1][contains(@text,'Java')]"),
                "Third result on the page does not contain word 'Java'",
                5
        );
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOne() {

//        adding first article

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_text = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find Search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_text,
                5
        );

        MainPageObject.waitForElementToRender(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot render Java article page with title 'Java (programming language' to further pressing 'More options' button",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button",
                10
        );

        MainPageObject.waitForElementToRender(
                By.xpath("//android.widget.FrameLayout"),
                "Cannot render menu with 'Add to reading list' option",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' option",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter new name for article folder",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button to save name of this list ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );

//        adding second article

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_text_2 = "Appium";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text_2,
                "Cannot find Search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Automation for Apps']"),
                "Cannot find 'Automation for Apps' topic searching by " + search_text_2,
                5
        );


        MainPageObject.waitForElementToRender(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot render Appium article title for further pressing 'More options' button",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button",
                5
        );

        MainPageObject.waitForElementToRender(
                By.xpath("//android.widget.FrameLayout"),
                "Cannot render menu with 'Add to reading list' option",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' option",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find '" + name_of_folder + "' folder",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, X button is still there",
                5
        );

//        deleting first article

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists button",
                5
        );

        MainPageObject.waitForElementToRender(
                By.id("org.wikipedia:id/item_container"),
                "Cannot render container with " + name_of_folder + " list",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find " + name_of_folder + " list",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='object-oriented programming language']"),
                "Cannot find 'object-oriented programming language' article in 'Learning programming' list"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot delete saved 'Object-oriented programming language' article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Automation for Apps']"),
                "Cannot find 'Automation for Apps' article in " + name_of_folder + "list",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Automation for Apps']"),
                "Cannot open 'Automation for Apps' article, cannot find it's title",
                15

        );
    }

    @Test
    public void testOpenArticleAndFindTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_text = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find Search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_text,
                5
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Page do not have article title"
        );
    }

}
