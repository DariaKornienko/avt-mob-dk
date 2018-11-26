package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IosNavigatorUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public IosNavigatorUI(AppiumDriver driver)
    {
        super(driver);
    }
}
