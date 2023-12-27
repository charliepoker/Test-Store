package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopContentPanel {

    public WebDriver driver;

    By continueShoppingBtn = By.xpath("//button[contains(text(), 'Continue')]");
    By checkoutBtn = By.xpath("//div[@id='blockcart-modal']/div[@role='document']/div[@class='modal-content']//a[@href='//teststore.automationtesting.co.uk/cart?action=show']");

    public ShopContentPanel(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getContinueShopBtn() {
        return driver.findElement(continueShoppingBtn);
    }

    public WebElement getCheckoutBtn() {
        return driver.findElement(checkoutBtn);
    }
}
