package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.Constants;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(css = "#header_container > div.header_secondary_container > div > span > select")
    private WebElement sortButton;

    @FindBy(css = "#header_container > div.header_secondary_container > div > span > select > option:nth-child(1)")
    private WebElement sortAToZ;

    @FindBy(css = "#header_container > div.header_secondary_container > div > span > select > option:nth-child(2)")
    private WebElement sortZToA;

    @FindBy(css = "#inventory_container > div")
    private List<WebElement> productsList;

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

    public void setSortAToZ() {
        sortButton.click();
        sortAToZ.click();
    }

    public void setSortZToA() {
        sortButton.click();
        sortZToA.click();
    }

    public String getProductName() {
        WebElement firstProduct = productsList.get(0);
        WebElement titleProduct = firstProduct.findElement(By.cssSelector(".inventory_item_name"));
        return titleProduct.getText();
    }

}
