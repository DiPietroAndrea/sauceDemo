package config.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.After;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
    public class StepDefinition {
    private WebDriver driver;
    private LoginInPage loginInPage;
    private HomePage homePage;
    private CartPage cartPage;


    //Liste di supporto per il sort test

    private List<String> originalProductsNames;
    private List<Double> originalProductsPrice;

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


        @Given("I am on the home page")
        public void iAmOnTheHomePage(String username, String password) {
            loginInPage.logIn(username, password);
            loginInPage.clickLogInButton();
        }

        //TEST ORDINAMENTO PRODOTTI HOME PAGE

        //TEST ORDINAMENTO DALLA A ALLA Z


        @When("I sort the products from A to Z")
        public void iSortTheProductsFromAToZ() {

            List<String> originalNames = homePage.createListProductsByName();
            this.originalProductsNames = originalNames;
            homePage.selectSortingType("Name (A to Z)");
        }

        @Then("The products are sorted by ascending name")
        public void theProductsAreSortedForTitle() {
            List<String> sortedProductNames = homePage.productAscendingSorting();

            assertEquals("Products are not sorted in ascending order by name,",
                    this.originalProductsNames.stream().sorted().collect(Collectors.toList()),
                    sortedProductNames);
        }

        //TEST ORDINAMENTO DALLA Z ALLA A

        @When("I sort the products from Z to A")
        public void iSortTheProductsFromZToA() {
                List<String> originalNames = homePage.createListProductsByName();
                this.originalProductsNames = originalNames;
                homePage.selectSortingType("Name (Z to A)");

        }

        @Then("The products are sorted by descending name")
        public void the_Products_Are_Sorted_By_Descending_Name() {
            List<String> sortedProductNames = homePage.productDescendingSorting();

            assertEquals("Products are not sorted in descending order by name",
                    this.originalProductsNames.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()),
                    sortedProductNames);
        }

        //TEST ORDINAMENTO PER PREZZO PIù BASSO

        @When("I sort the products from low to high price")
        public void i_Sort_The_Products_From_Low_To_High_Price() {
        List<Double> originalPrice = homePage.createListProductsByPrice();
        homePage.selectSortingType("Price (low to high)");
        this.originalProductsPrice = originalPrice;
        }

        @Then("The products are sorted by lowest price")
        public void the_Products_Are_Sorted_By_Lowest_Price() {
        List<Double> expectedPrices = new ArrayList<>(this.originalProductsPrice);
        Collections.sort(expectedPrices);

        List<Double> sortedPrices = homePage.createListProductsByPrice();
        assertEquals("Prices no tosrted from the lowest", expectedPrices, sortedPrices);
        }


        //TEST ORDINAMENTO DEL PREZZO DAL PIù ALTO AL PIù BASSO

        @When("I sort the products from high to low price")
        public void i_Sort_The_Products_From_High_To_Low_Price() {
            List<Double> originalPrice = homePage.createListProductsByPrice();
            this.originalProductsPrice = originalPrice;
            homePage.selectSortingType("Price (high to low)");
        }

        @Then("The products are sorted by the highest price")
        public void the_Products_Are_Sorted_By_The_Highest_Price() {
        List<Double> expectedPrices = new ArrayList<>(this.originalProductsPrice);
        Collections.sort(expectedPrices, Collections.reverseOrder());

        List<Double> sortedPrices = homePage.createListProductsByPrice();
        assertEquals("Prices not sorted from the highest price", expectedPrices, sortedPrices);
        }


        //TEST DETTAGLIO

        //TEST DEL DETTAGLIO TRAMITE TITOLO

        @When("I click on the image of a product")
        public void iClickOnTheImageOfAProduct() {
            homePage.randomSelectImage();
            //homePage.goToDetailProductImage();
            System.out.print("Sono entrato in una pagina di dettaglio random");
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

        @Then("The cart will be updated")
        public void theCartWillBeUpdated() {
        assertTrue(homePage.getNumberOfItems() > 0);

        //assertEquals(number, homePage.getNumberProductsCartText());
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



        @After
        public void closedObject() {
            DriverSingleton.closeObjectInstance();
        }


        @When("I click on a button of a product to add it")
        public void iClickOnAButtonOfAProductToAddIt() {
            homePage.randomAddToCart();
            homePage.randomSelectAddToCartButton();
        }
}
