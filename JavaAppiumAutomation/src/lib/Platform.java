package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform
{
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIos()) {
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        } else {
            throw new Exception("Не удалось определить драйвер для платформы " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Nexus5X");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        //capabilities.setCapability("app","C://Users//new-user//Desktop//DK-JAA-master//JavaAppiumAutomation//apks//org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.4");
        capabilities.setCapability("app","/Users/vitalijzarubin/Projects/avt-mob-dk/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}