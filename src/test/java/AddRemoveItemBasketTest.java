import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;

public class AddRemoveItemBasketTest extends BasePage {

    public AddRemoveItemBasketTest() throws IOException {
        super();
    }

    @BeforeTest
    public void setUp() throws IOException {
        driver = getDriver();
        driver.get(getUrl());
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver = null;
    }

    @Test
    public void addRemoveItem() throws  InterruptedException{
        // creating an object of the automationtesting.co.uk webpage
        HomePage home = new HomePage(driver);


        home.getToggle().click();
        Thread.sleep(3000);
        home.getTestStoreLink().click();

        // creating an object of the test store homepage
        ShopHomePage shopHome = new ShopHomePage(driver);
        shopHome.getProdOne().click();

        // creating an object of the shop products page (when a product has been selected)
        // Handle Dropdown menu to select size
        ShopProductPage shopProd = new ShopProductPage(driver);
        Select option  = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("M");
        shopProd.getQuantIncrease().click();
        shopProd.getAddToCartBtn().click();
        Thread.sleep(5000);

        // creating an object of the cart content panel (once an item was added)
        ShopContentPanel cPanel = new ShopContentPanel(driver);
        cPanel.getContinueShopBtn().click();
        shopProd.getHomepageLink().click();
        shopHome.getProdTwo().click();
        shopProd.getAddToCartBtn().click();
        cPanel.getCheckoutBtn().click();


        ShoppingCart cart = new ShoppingCart(driver);
        Thread.sleep(3000);
        cart.getDeleteItemTwo().click();
        cart.getTotalAmount().getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));

        Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");

    }

}
