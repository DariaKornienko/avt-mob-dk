import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.events.SocketReadEvent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest
{
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Nexus5X");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        //capabilities.setCapability("app","C://Users//new-user//Desktop//DK-JAA-master//JavaAppiumAutomation//apks//org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void ex3CancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Поиск')]"),
                "Java",
                "Не удалось ввести текст поиска",
                5
        );

        List<WebElement> listOfElements = waitForElementPresentCount(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Статей не найдено",
                15
        );

        boolean bCount = false;
        int aCount = listOfElements.size();
        if (aCount > 1) {
            bCount = true;
        }

        Assert.assertTrue(
                "Найдена одна статья",
                bCount
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Не найдено поле поиска",
                5
        );

        WebElement titel_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Не найдено поле поиска",
                15
        );

        String article_titel = titel_element.getAttribute("text");

        Assert.assertEquals(
                "Поле ввода не пустое",
                "Поиск",
                //"Search…",
                article_titel
        );
    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Не удалось ввести текст поиска",
                5
        );

       waitForElementAndClick(
               By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
               "Текст ошибки",
               5
       );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Тест не пройден",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Не удалось ввести текст поиска",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Текст ошибки",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']" ),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                //By.name("Add to reading list"),
                //By.xpath("//*[@text='Add to reading list']" ),
                By.xpath("//android.widget.TextView[@instance='2']" ),
                "Не найдена кнопка добавления",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button" ),
                "Не найдена кнопка подтверждения",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Не удалось очистить поле ввода",
                5
        );

        String name_of_folder = "Test";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Не удалось ввести название списка",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1" ),
                "Не найдена кнопка ОК",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']" ),
                "Не удалось закрыть статью",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']" ),
                "Не удалось нажать на кнопку списков",
                5
        );

        waitForElementAndClick(
                By.xpath("//org.wikipedia:id/item_container/*[@text='" + name_of_folder + "']" ),
                "Нет статтьи в списке",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']" ),
                "Статья не найдена"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']" ),
                "Избранное не удалено",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Не найдены статьи по запросу " + search_line,
                15
        );

        int amount_of_search_result = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "Найдено несколько статей",
                amount_of_search_result > 0
        );




    }

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        String search_line = "adhbakdjfnalf";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String ampty_result_label = "org.wikipedia:id/search_empty_text";

        waitForElementPresent(
                By.id(ampty_result_label),
                "Нет плашки отсутствия результатов",
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "Найдено " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Текст ошибки",
                5
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Заголовок не найден",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Заголовок не найден",
                15
        );

        Assert.assertEquals(
                "Заголовок был изменен",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Заголовок не найден",
                15
        );

        Assert.assertEquals(
                "Заголовок был изменен после второго поворота",
                title_after_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Статья не найдена",
                5
        );

        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Статья не найдена после разворота",
                5
        );

    }

    @Test
    public void ex5SavingTwoArticles()
    {
        //Первая статья
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        String search_result_locator =  "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']";
        waitForElementAndClick(
                By.xpath(search_result_locator),
                "Статья не найдена",
                5
        );

        String title_before = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Заголовок не найден",
                15
        );

        waitForElementPresent(
                By.xpath("//android.widget.ImageView[@content-desc='More options']" ),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']" ),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='Add to reading list']" ),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']" ),
                //By.xpath("//*[@instance=2]"),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button" ),
                "Не найдена кнопка подтверждения",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Не удалось очистить поле ввода",
                5
        );

        String name_of_folder = "Test";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Не удалось ввести название списка",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1" ),
                "Не найдена кнопка ОК",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']" ),
                "Не удалось закрыть статью",
                5
        );

        //Вторая статья
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдено поле поиска или не сработал клик",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Не удалось ввести текст поиска",
                5
        );

        search_result_locator =  "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Island of Indonesia']";
        waitForElementAndClick(
                By.xpath(search_result_locator),
                "Статья не найдена",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']" ),
                By.xpath("//*[@instance=2]"),
                "Не найдена кнопка дополнительных опций",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Папка не найдена",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось закрыть статью",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Не удалось нажать на кнопку списков",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Нет списков для чтения",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Нет списков для чтения",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='island of Indonesia']"),
               "Не удалось удалить статью"
        );

        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Не найдена вторая статья после удаления первой",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Не сработал клик по статье",
                15
        );

        String title_after = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Заголовок не найден",
                15
        );

        Assert.assertEquals(
                "Заголовок оставлейся статьи не совпадает сзаголовком первой стаьи",
                title_after,
                title_before
        );
    }


//    @Test
//    public void ex4WordCheckInTheSearch()
//    {
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Не найдено поле поиска или не сработал клик",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Поиск')]"),
//                "Java",
//                "Не удалось ввести текст поиска",
//                5
//        );
//
//        List<WebElement> listOfElements = waitForElementPresentCount(
//                By.id("org.wikipedia:id/page_list_item_container"),
//                "Статей не найдено",
//                15
//        );
//
//        int countElement = listOfElements.size();
//
//        for (int i = 0; i < countElement; i++)
//        {
//            WebElement elements = listOfElements.get(i);
//            WebElement element = elements.findElement(By.id("org.wikipedia:id/page_list_item_title"));
//            System.out.println(element.getText());
//        }
//    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private List<WebElement> waitForElementPresentCount(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(by, 0)
        );
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int alredy_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if (alredy_swiped > max_swipes){
                waitForElementPresent(by, "Не удалось найти элемент \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++alredy_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middel_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middel_y)
                .waitAction(300)
                .moveTo(left_x, middel_y)
                .release().perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "Элемент '" + by.toString() + "' отсутствует";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
