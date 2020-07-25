package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestPage {

  @FindBy(id = "email")
  public WebElement username;

  @FindBy(id = "passwd")
  WebElement password;

  @FindBy(id = "SubmitLogin")
  WebElement loginButton;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver,30);
    PageFactory.initElements(driver, this);
  }

  public void enterCredentialsAndLogin(String uname, String pass){

    username.sendKeys(uname);
    password.sendKeys(pass);
    loginButton.click();

  }
}
