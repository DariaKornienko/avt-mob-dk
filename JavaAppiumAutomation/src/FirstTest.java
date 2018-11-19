import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    public void testCompareText()
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
}
