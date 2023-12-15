package config.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
    public class StepDefinition {
    private WebDriver driver;
    private LoginInPage loginInPage;
    private HomePage homePage;
    private CartPage cartPage;

        @Autowired
        ConfigurationProperties configurationProperties;

        @Before
        public void initializeObjects() {
            DriverSingleton.getInstance(configurationProperties.getBrowser());
            loginInPage = new LoginInPage();
            homePage = new HomePage();
            cartPage = new CartPage();
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


        //TEST DETTAGLIO

        //TEST DEL DETTAGLIO TRAMITE TITOLO

        @When("I click on the image of a product")
        public void iClickOnTheImageOfAProduct() {
            homePage.goToDetailProductImage();
        }

        @Then("I see the page of detail of {string}")
        public void iSeeThePageOfDetailOfThatProduct(String title) {
            assertEquals(title, homePage.getProductNameText());
        }

        @When("I click on the title of a product")
        public void iClickOnTheTitleOfAProduct() {
        homePage.goToDetailPageFromTitle();
        }



        //TEST AGGIUNTA PRODOTTO

        @When("I click on the button to add a product")
        public void iClickOnTheButtonToAddAProduct() {
        homePage.addToCart();
        }

        @Then("The cart will be updated to {string}")
        public void theCartWillBeUpdated(String number) {
        assertEquals(number, homePage.getNumberProductsCartText());
        }


        //TEST RIMOZIONE PRODOTTO

        @When("I click on the button to remove a product")
        public void iClickOnTheButtonToRemoveAProduct() {
        homePage.removeProduct();
        }

        @Then("The product can be {string} again")
        public void theProductCanBeAddedAgain(String remove) {
        assertEquals(remove, homePage.getRemoveProductButtonText());
        }

        @Then("The button says {string}")
        public void theButtonSaysAddToCart(String addToCart) {
        assertEquals(addToCart, homePage.getAddToCartButtonText());
        }

        @When("I click on the cart button")
        public void i_Click_On_The_Cart_Button() {
                homePage.proceedToCheckOut();
        }

        @AfterEach
        public void closedObject() {
            driver.quit();
        }



}
