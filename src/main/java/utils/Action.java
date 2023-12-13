package utils;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;

    public abstract class Action {
    private static FrameworkProperties frameworkProperties;
    protected static WebDriver driver;

    public Action() {
        DriverSingleton.getInstance(Constants.BROWSER);
        driver = DriverSingleton.getDriver();
    }

}
