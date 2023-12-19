package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.Constants;

import java.awt.*;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static automation.utils.Constants.NUMERO_PRODOTTI_CARRELLO;

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

    @FindBy(css="#inventory_container > div")
    private WebElement productContainer;

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

    @FindBy(id = "item_4_img_link")
    private WebElement detailImageProduct;

    @FindBy(css = "#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_name.large_size")
    private WebElement productName;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeProductButton;

    @FindBy(css = "#item_4_title_link > div")
    private WebElement titleProductName;

    private List<WebElement> addToCartButtons;

    private List<WebElement> productImages;

    public List<String> getProductsTitleList() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : productsList) {
            String productName = product.findElement(By.className("inventory_item_name")).getText();
            productNames.add(productName);
        }
        return productNames;
    }

    public List<String> getProductNames(List<WebElement> productsList, By nameSelector) {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : productsList) {
            String productName = product.findElement(nameSelector).getText();
            productNames.add(productName);
        }
        return productNames;
    }


    public void selectSortingType(String orderType) {
        new Select(sortButton).selectByVisibleText(orderType);
    }

    public List<String> createListProductsByName() {
        List<String> originalProductNames = driver.findElements(By.cssSelector(".inventory_item_name"))
                .stream()
                .map((WebElement::getText))
                .collect(Collectors.toList());

        return originalProductNames;
    }

    public List<Double> createListProductsByPrice() {
        return driver.findElements(By.cssSelector(".inventory_item_price"))
                .stream()
                .map(element -> element.getText().replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public List<String> productAscendingSorting() {
        List<String> sortedProductNames = driver.findElements(By.cssSelector(".inventory_item_name"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return sortedProductNames;
    }

    public List<String> productDescendingSorting() {
        List<String> sortedProductNames = driver.findElements(By.cssSelector(".inventory_item_name"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return sortedProductNames;
    }

    public void sortProductNames(List<String> productNames) {
        Collections.sort(productNames);
    }

    public void reverseSortProductNames(List<String> productNames) {
        Collections.sort(productNames, Collections.reverseOrder());
    }



    public void addToCart() {
        addToCartButton.click();

        if (numberOfProducts.getText().contains(NUMERO_PRODOTTI_CARRELLO)) {
            System.out.println("Il carrello è stato aggiornato");
        } else {
            System.out.println("Il carrello non è stato aggiornato");
        }
    }

    //Metodo che crea una lista con tutti gli Id di add to cart

    public List<String> randomAddToCart() {
        addToCartButtons = driver.findElements(By.xpath("//button[contains(@id, 'add-to-cart-')]"));
        List<String> addTocartButtonIds = new ArrayList<>();

        for (WebElement button : addToCartButtons) {
            String buttonId = button.getAttribute("id");
            addTocartButtonIds.add(buttonId);
        }
        return addTocartButtonIds;
    }

    //Metodo che randomizza e clicca un add to cart

    public void randomSelectAddToCartButton() {
        Random random = new Random();
        int randomIndex = random.nextInt(addToCartButtons.size());
        WebElement randomSelect = addToCartButtons.get(randomIndex);
        randomSelect.click();
    }

    public List<String> randomProductImage() {
         productImages = productContainer.findElements(By.className("inventory_item_img"));
        List<String> imageIds = new ArrayList<>();

        for (WebElement image : productImages) {
            String imageId = image.getAttribute("src");
            imageIds.add(imageId);
        }
        return imageIds;
    }

    public void randomSelectImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(productImages.size());
        WebElement randomSelect = productImages.get(randomIndex);
        randomSelect.click();
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

    public void goToDetailPageFromTitle() {
        titleProductName.click();
    }
    public void removeProduct() {
        removeProductButton.click();
    }
    public void goToDetailProductImage() {
        detailImageProduct.click();
    }
    public String getProductNameText() {
        return productName.getText();
    }
    public String getNumberProductsCartText() { return numberOfProducts.getText(); }
    public String getRemoveProductButtonText() {return  removeProductButton.getText(); }
    public String getAddToCartButtonText() { return addToCartButton.getText(); }
    public String getTitle() {
        return titoloProdotti.getText();
    }

    public Integer getNumberOfItems() {
        String textNumber = numberOfProducts.getText();
        int number = Integer.parseInt(textNumber);
        return number;
    }

}
