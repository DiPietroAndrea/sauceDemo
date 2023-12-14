import automation.drivers.DriverSingleton;
import automation.pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import automation.utils.Constants;
import automation.utils.FrameworkProperties;

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

    @BeforeAll
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
    Assertions.assertEquals(frameworkProperties.getProperty(Constants.TITOLO_HOME_PAGE), homePage.getTitle());
    }

    @Test
    public void authenticationTestBLockedUser() {
        driver.get(Constants.URL);
        loginInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        Assertions.assertEquals(frameworkProperties.getProperty(Constants.UTENTE_BLOCCATO_ERROR), loginInPage.getBlockedError());
    }

    @Test
    public void authenticationTestErrorCredential() {
        driver.get(Constants.URL);
        loginInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        Assertions.assertEquals(frameworkProperties.getProperty(Constants.CREDENZIALI_ERROR), loginInPage.getCredentialError());
    }

    @Test
    public void addElementToCartTest(){
        driver.get(Constants.URL);
        loginInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        homePage.addToCart();
    }

    @Test
    public void fullBuyElementTest() {
        driver.get(Constants.URL);
        loginInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        homePage.addToCart();
        homePage.proceedToCheckOut();
        cartPage.proceedToCheckOut();
        checkOutYourInformationPage.InsertYourInformation();
        checkOutOverviewPage.completeCheckOut();
        Assertions.assertEquals("Thank you for your order!", checkOutCompletePage.getTitoloOrderRecieved());
        checkOutCompletePage.returnHomePage();

    }

    @AfterAll
    public static void closeTests() { driver.quit(); }

}
