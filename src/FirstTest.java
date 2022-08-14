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
