package config.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Driver;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
    public class StepDefinition {
    private WebDriver driver;
    private LoginInPage loginInPage;
    private HomePage homePage;

        @Autowired
        ConfigurationProperties configurationProperties;

        @Before
        public void initializeObjects() {
            DriverSingleton.getInstance(configurationProperties.getBrowser());
            loginInPage = new LoginInPage();
            homePage = new HomePage();

        }

        @Given("I am on the login page")
        public void I_am_on_the_login_page() {
            driver = DriverSingleton.getDriver();
            driver.get(Constants.URL);
        }

        @When("I enter username as {string} and password as {string}")
        public void iEnterUsernameAsUsernameAndPasswordAsPassword(String username, String password) {
            loginInPage.logIn(username, password);
        }
        @Then("I land on the Products page")
        public void I_land_on_the_Products_page() {
        loginInPage.clickLogInButton();
        Assertions.assertEquals(configurationProperties.getTitoloHomePage(), homePage.getTitle());
        }


    }
