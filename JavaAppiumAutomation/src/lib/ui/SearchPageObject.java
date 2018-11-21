package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "org.wikipedia:id/search_empty_text";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS (ШАБЛОН) */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS (ШАБЛОН) */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Не найдено поле поиска или не сработал клик", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Не удалось найти строку поиска",5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Отстутсвует крестик");
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Присутствует крестик", 5);
    }

    public void checkSearchFieldIsEmpty()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Строка поиска не пуста",5);
    }

    public void clickCancelButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Не удалось кликнуть по крестику", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"Не удалось ввести текст поиска",5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Статья не найдена");
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Статья не найдена или не сработал клик", 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),"Не найдены статьи по запросу ",15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULT_ELEMENT), "Не удается найти элемент", 15);
    }

    public void assertThereIsNotResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"Найдены какие-то результаты поиска");
    }
}