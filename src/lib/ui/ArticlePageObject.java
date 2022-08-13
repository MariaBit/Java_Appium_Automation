package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String

        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON ="//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.TextView[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT ="org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON ="//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON ="//android.widget.ImageButton[@content-desc='Navigate up']",
        FIRST_ARTICLE_TITLE = "org.wikipedia:id/page_list_item_title",
        THIRD_ARTICLE_TITLE = "//android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView[1]";



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find 'More options' button",
                10
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to reading list' option",
                10
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'GOT IT' tip overlay",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot enter new name for article folder",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button to save name of this list ",
                5
        );
    }

    public void closeArticle ()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X button",
                5
        );

        this.waitForElementNotPresent(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, X button is still there",
                5
        );
    }

    public void assertArticleTitleHasTextById (String expected_article_title)
    {
        this.assertElementHasText(
                By.id(FIRST_ARTICLE_TITLE),
                expected_article_title,
                "Article title does not equal to expected title"
        );
    }

    public void assertArticleTitleHasTextByXpath (String expected_article_title)
    {
        this.assertElementHasText(
                By.xpath(THIRD_ARTICLE_TITLE),
                expected_article_title,
                "Third article title does not equal to expected title"
        );
    }

    public void waitForTitleToDisappear()
    {
        this.waitForElementNotPresent(
                By.id(FIRST_ARTICLE_TITLE),
                "Title supposed to be not presented",
                5
                );

    }


}
