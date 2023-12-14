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

public class CheckOutCompletePage {
    private WebDriver driver;
    public CheckOutCompletePage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy (css = "#checkout_complete_container > h2")
    private WebElement titoloOrderRecieved;

    @FindBy(id = "back-to-products")
    private WebElement backHomePageButton;

    public void returnHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(backHomePageButton));

        backHomePageButton.click();
    }

    public String getTitoloOrderRecieved() { return titoloOrderRecieved.getText(); }
}
