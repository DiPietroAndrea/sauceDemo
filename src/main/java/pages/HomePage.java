package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    public HomePage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#header_container > div.header_secondary_container > span")
    private WebElement titoloProdotti;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;


    @FindBy(css = "#shopping_cart_container > a > span")
    private WebElement numberOfProducts;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    public String getTitle() {
        return titoloProdotti.getText();
    }

    public void addToCart() {
        addToCartButton.click();

        if (numberOfProducts.getText().contains(Constants.NUMERO_PRODOTTI_CARRELLO)) {
            System.out.println("Il carrello è stato aggiornato");
        } else {
            System.out.println("Il carrello non è stato aggiornato");
        }
    }

    public void proceedToCheckOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        cartButton.click();
    }
}
