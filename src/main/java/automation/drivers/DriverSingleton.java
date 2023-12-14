package automation.drivers;

import automation.drivers.strategies.DriverStategy;
import automation.drivers.strategies.DriverStrategyImplementer;
import automation.utils.Constants;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton(String driver) {
        instantiate(driver);
    }

    public WebDriver instantiate(String strategy) {
        DriverStategy driverStategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static DriverSingleton getInstance(String driver){
        if (instance == null) {
            instance = new DriverSingleton(driver);
        }
        return instance;
    }


    //Metodo per chiudere il driver quando abbiamo finito con i nostri test
    public static void closeObjectInstance() {
        instance = null;
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
