package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListPage extends TestPage{

  @FindBy(css = ".product_list.grid.row a.product-name")
  public WebElement productShadedTshirt;

  public ProductListPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver,30);
    PageFactory.initElements(driver, this);
  }

}
