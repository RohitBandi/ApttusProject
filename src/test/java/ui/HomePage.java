package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestPage {

  @FindBy(css = "a.login")
  WebElement signIn;

  @FindBy(css = "li:nth-of-type(3) a[title='T-shirts']")
  WebElement categoryTshirt;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver,30);
    PageFactory.initElements(driver, this);
  }

  public void clickOnSignIn(){
    signIn.click();
  }

  public void clickOnTshirtCategory(){
    categoryTshirt.click();
  }
}
