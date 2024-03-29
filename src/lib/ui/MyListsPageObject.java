package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLE_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']";
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace( "{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace( "{TITLE}", article_title);
    }

    private static String getSavedArticleXpathBySubstring (String article_substring)
    {
        return ARTICLE_BY_SUBSTRING_TPL.replace( "{SUBSTRING}", article_substring);
    }

        public MyListsPageObject(AppiumDriver driver){
            super(driver);
        }

        public void openFolderByName(String name_of_folder)
        {
            String folder_name_xpath = getFolderXpathByName(name_of_folder);

            this.waitForElementToRender(By.xpath(folder_name_xpath),
                    "Cannot find folder by name " + name_of_folder,
                    5);

            this.waitForElementAndClick(
                    By.xpath(folder_name_xpath),
                    "Cannot find folder by name " + name_of_folder,
                    5
            );
        }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article by title", 15);

    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article is still presented", 15);

    }
        public void swipeByArticleToDelete(String article_title)
        {
            this.waitForArticleToAppearByTitle(article_title);

            String article_xpath = getFolderXpathByName(article_title);
            this.swipeElementToLeft(
                    By.xpath(article_xpath),
                    "Cannot find saved article"
            );
            this.waitForArticleToDisappearByTitle(article_title);
        }

        public void openArticleInMyList(String article_substring)
    {
        String article_substring_xpath = getSavedArticleXpathBySubstring(article_substring);
        this.waitForElementAndClick(By.xpath(article_substring_xpath), "Cannot find article with title " + article_substring, 5);
    }
}
