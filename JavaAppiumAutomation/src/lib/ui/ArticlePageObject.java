package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        MY_LIST_BY_NAME_TPL = "//android.widget.TextView[@text='{MY_LIST_NAME}']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static String getListXpathByName(String name_of_list)
    {
        return MY_LIST_BY_NAME_TPL.replace("{MY_LIST_NAME}", name_of_list);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Не найден заголовок на странице", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Не найден конец страницы",20);
    }

    public void addArticleToMyList()
    {
        this.waitForElementPresent(
                By.xpath(OPTIONS_BUTTON),"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),"Не найдена кнопка дополнительных опций",15);

        this.waitForElementPresent(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Не найдена кнопка дополнительных опций",15);
    }

    public void newMyListName(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),"Не найдена кнопка подтверждения",15);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),"Не удалось очистить поле ввода",5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),name_of_folder,"Не удалось ввести название списка",15);

        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),"Не найдена кнопка ОК",5);
    }

    public void myListName(String name_of_folder)
    {
        String my_list_name = getListXpathByName(name_of_folder);
        this.waitForElementPresent(By.xpath(my_list_name), "Не удалось найти папку '" + my_list_name + "'");
        this.waitForElementAndClick(By.xpath(my_list_name),"Папка не найдена",15 );
    }

    public void closeArticle()
    {
        this.waitForElementPresent(By.xpath(CLOSE_ARTICLE_BUTTON), "Не найден крестик");
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),"Не удалось закрыть статью",15);
    }

    public void assertPageTitle()
    {
        this.assertElementPresent(By.id(TITLE),"Заголовок статьи не найден");
    }
}
