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

import static org.junit.Assert.assertEquals;

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


        //TEST LOGIN

        @Given("I am on the login page")
        public void I_am_on_the_login_page() {
            driver = DriverSingleton.getDriver();
            driver.get(Constants.URL);
        }


        //TEST DELLA CORRETTA LOGIN

        @When("I enter username as {string} and password as {string}")
        public void iEnterUsernameAsUsernameAndPasswordAsPassword(String username, String password) {
            loginInPage.logIn(username, password);
        }
        @Then("I land on the Products page")
        public void I_land_on_the_Products_page() {
        loginInPage.clickLogInButton();
        assertEquals(configurationProperties.getTitoloHomePage(), homePage.getTitle());
        }


        //TEST DEI FALLIMENTI DELLA LOGIN

        @Then("I have an error message as {string}")
        public void i_Have_An_Error_Message_As_CredentialMessage(String message) {
            loginInPage.clickLogInButton();
            assertEquals(message, loginInPage.getCredentialError());
        }


        //TEST ORDINAMENTO PRODOTTI HOME PAGE

        //TEST ORDINAMENTO DALLA A ALLA Z

        @Given("I am on the home page")
        public void iAmOnTheHomePage(String username, String password) {
        loginInPage.logIn(username, password);
        loginInPage.clickLogInButton();
        }

        @When("I sort the products from A to Z")
        public void iSortTheProductsFromAToZ() {
            homePage.setSortAToZ();
        }

        @Then("The products are sorted for {string}")
        public void theProductsAreSortedForTitle(String title) {
            assertEquals(title, homePage.getProductName());
        }

        //TEST ORDINAMENTO DALLA Z ALLA A

        @When("I sort the products from Z to A")
        public void iSortTheProductsFromZToA() {
        homePage.setSortZToA();
        }



}
