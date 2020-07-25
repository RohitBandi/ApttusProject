package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductDetailsPage extends TestPage {

  @FindBy(name = "Submit")
  WebElement AddToCart;

  @FindBy(id = "layer_cart")
  public WebElement productAddedPopUp;

  @FindBy(css = "#layer_cart [class*='layer_cart_product'] h2")
  WebElement productAddedtext;

  @FindBy(id = "layer_cart_product_title")
  WebElement cartProductTitle;

  @FindBy(id = "layer_cart_product_attributes")
  WebElement cartProductAttributes;

  @FindBy(id = "layer_cart_product_quantity")
  WebElement cartProductQuanity;

  @FindBy(id = "layer_cart_product_price")
  WebElement cartProductPrice;

  @FindBy(css = "[title='Proceed to checkout']")
  WebElement proceedToCheckout;

  public ProductDetailsPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 60);
    PageFactory.initElements(driver, this);
  }

  public void addtoCart() {
    AddToCart.click();
  }

  public void validateDetailsAndClickProceedCheckout() {
    Assert.assertEquals(productAddedtext.getText().trim(), "Product successfully added to your shopping cart");
    Assert.assertEquals(cartProductTitle.getText().trim(), "Faded Short Sleeve T-shirts");
    Assert.assertEquals(cartProductAttributes.getText().trim(), "Orange, S");
    Assert.assertEquals(cartProductQuanity.getText().trim(), "1");
    Double total = Double.parseDouble(cartProductQuanity.getText().trim()) * 16.51;
    Assert.assertEquals(cartProductPrice.getText().trim(), "$" + Double.toString(total));

    proceedToCheckout.click();

  }
}
