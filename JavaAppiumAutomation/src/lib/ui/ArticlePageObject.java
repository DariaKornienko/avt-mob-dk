package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        MY_LIST_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{MY_LIST_NAME}']";

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
        return this.waitForElementPresent(TITLE, "Не найден заголовок на странице", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Не найден конец страницы",20);
    }

    public void addArticleToMyList()
    {
        this.waitForElementPresent(
                OPTIONS_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,"Не найдена кнопка дополнительных опций",15);
    }

    public void newMyListName(String name_of_folder)
    {
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,"Не найдена кнопка подтверждения",15);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,"Не удалось очистить поле ввода",5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,name_of_folder,"Не удалось ввести название списка",15);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Не найдена кнопка ОК",5);
    }

    public void myListName(String name_of_folder)
    {
        String my_list_name = getListXpathByName(name_of_folder);
        this.waitForElementPresent(my_list_name, "Не удалось найти папку '" + my_list_name + "'");
        this.waitForElementAndClick(my_list_name,"Папка не найдена",15 );
    }

    public void closeArticle()
    {
        this.waitForElementPresent(CLOSE_ARTICLE_BUTTON, "Не найден крестик");
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,"Не удалось закрыть статью",15);
    }

    public void assertPageTitle()
    {
        this.assertElementPresent(TITLE,"Заголовок статьи не найден");
    }
}
