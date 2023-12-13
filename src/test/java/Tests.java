import drivers.DriverSingleton;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import static junit.framework.Assert.assertEquals;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static LoginInPage loginInPage;
    static HomePage homePage;
    static CartPage cartPage;
    static CheckOutYourInformationPage checkOutYourInformationPage;
    static CheckOutOverviewPage checkOutOverviewPage;
    static CheckOutCompletePage checkOutCompletePage;

    @BeforeClass
    public static void initializeObject() {
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        loginInPage = new LoginInPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        checkOutYourInformationPage = new CheckOutYourInformationPage();
        checkOutOverviewPage = new CheckOutOverviewPage();
        checkOutCompletePage = new CheckOutCompletePage();
    }

    @Test
    public void authenticationTest() {
    driver.get(Constants.URL);
    loginInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
    assertEquals(frameworkProperties.getProperty(Constants.TITOLO_HOME_PAGE), homePage.getTitle());
    }


}
