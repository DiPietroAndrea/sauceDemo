import automation.drivers.DriverSingleton;
import automation.pages.*;
import org.openqa.selenium.WebDriver;
import automation.utils.FrameworkProperties;

public class Main {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton = DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://saucedemo.com/");

        LoginInPage loginInPage = new LoginInPage();

        HomePage homePage = new HomePage();

        CartPage cartPage = new CartPage();

        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage();

        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage();

        CheckOutCompletePage checkOutCompletePage = new CheckOutCompletePage();

        loginInPage.logIn("standard_user", "secret_sauce");

        if(homePage.getTitle().equals("Products")){
            System.out.println("Il test è passato");
        } else {
            System.out.println("Il test non è passato");
        }

        homePage.addToCart();

        homePage.proceedToCheckOut();

        cartPage.proceedToCheckOut();

        checkOutYourInformationPage.InsertYourInformation();

        checkOutOverviewPage.completeCheckOut();

        checkOutCompletePage.returnHomePage();

        //DriverSingleton.closeObjectInstance();

    }
}
