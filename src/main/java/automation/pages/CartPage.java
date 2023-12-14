package automation.pages;

import automation.utils.Constants;
import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    //Costruttore per il driver in cui gli specifichiamo di inizializzarsi all'interno di questa pagina

    public CartPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    //Locators per il bottone del checkout

    @FindBy(id = "checkout")
    private WebElement checkOutButton;

    //Locators per il testo del titolo del carrello

    @FindBy(css = "#header_container > div.header_secondary_container > span")
    private WebElement yourCartTitle;

    //Metodo per proseguire dalla pagina del carrello alla prima pagina di checkout

    public void proceedToCheckOut() {

        //Creazione di un wait per attendere le informazioni del sito

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));

        //Creazione di un if per continuare nella pagina di checkout se trova
        //una comparazione positiva tra 'yourCartTitle' e il contenuto di 'Constants.TITOLO_CARRELLO'
        //Altrimenti lancerà un'eccezione

        try {
            if (yourCartTitle.getText().equals(Constants.TITOLO_CARRELLO)){
                System.out.println("Puoi procedere con il checkout");
                checkOutButton.click();
            } else {
                System.out.println("Non sei nel carrello");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
