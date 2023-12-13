package automation.glue;

import drivers.DriverSingleton;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Action;
import utils.Constants;
import utils.FrameworkProperties;

public class StepDefinition {
    private Action action;
    private WebDriver driver;
    private FrameworkProperties frameworkProperties;
    private LoginInPage loginInPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckOutYourInformationPage checkOutYourInformationPage;
    private CheckOutOverviewPage checkOutOverviewPage;
    private CheckOutCompletePage checkOutCompletePage;



    //@Given()

}
