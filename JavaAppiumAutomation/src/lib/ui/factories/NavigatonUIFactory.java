package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigatorUI;
import lib.ui.ios.IosNavigatorUI;

public class NavigatonUIFactory
{
    public static NavigationUI get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigatorUI(driver);
        } else {
            return new IosNavigatorUI(driver);
        }
    }
}
