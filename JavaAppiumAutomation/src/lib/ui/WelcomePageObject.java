package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TOEXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT = "id:Learn more about data collected",
        STEP_NEXT_BUTTON = "id:Next",
        STEP_GET_STARTED_BUTTON = "id:Get started",
        SKIP_BUTTON = "id:Skip";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Не удалось найти ссылку на '" + STEP_LEARN_MORE_LINK + "'", 100);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TOEXPLORE_TEXT, "Не удалось найти текст '" + STEP_NEW_WAYS_TOEXPLORE_TEXT +"'", 10);
    }

    public void waitForAddOrEditPreferredLanguagesLink()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT, "Не удалось найти ссылку на 'Add or edit preferred languages'", 10);
    }

    public void waitForLearnMoreAboutDataCollectedLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT, "Не удалось найти ссылку на 'Learn more about data collected'", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(STEP_NEXT_BUTTON, "Не удалось найти или кликнуть по кнопке 'Next'", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(STEP_GET_STARTED_BUTTON, "Не удалось найти или кликнуть по кнопке 'Get started'", 10);
    }

    public void clickSkipButton()
    {
        this.waitForElementAndClick(SKIP_BUTTON, "Не удалось нажать на кнопку Пропустить", 10);
    }
}
