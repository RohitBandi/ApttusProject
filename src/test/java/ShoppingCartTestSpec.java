
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.HomePage;
import ui.LoginPage;
import ui.ProductDetailsPage;
import ui.ProductListPage;

import java.util.concurrent.TimeUnit;

public class ShoppingCartTestSpec {

  String driverPath = "chromedriver.exe";

  WebDriver driver;

  WebDriverWait wait;

  LoginPage loginPage;

  HomePage homePage;

  ProductListPage productListPage;

  ProductDetailsPage productDetailsPage;

  @BeforeTest
  public void setup() {
    System.setProperty("webdriver.chrome.driver", driverPath);
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 30);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("http://Automationpractice.com/");
    homePage = new HomePage(driver);
    loginPage = new LoginPage(driver);
    productListPage = new ProductListPage(driver);
    productDetailsPage = new ProductDetailsPage(driver);
  }

  @Test
  public void ShoppingCartValidationTest() {

    homePage.clickOnSignIn();

    Assert.assertTrue(loginPage.username.isDisplayed(), "User is not on the login screen");

    loginPage.enterCredentialsAndLogin("jetblue@grr.la", "jetblue");

    homePage.clickOnTshirtCategory();

    productListPage.productShadedTshirt.click();

    productDetailsPage.addtoCart();

    wait.until(ExpectedConditions.visibilityOf(productDetailsPage.productAddedPopUp));

    productDetailsPage.validateDetailsAndClickProceedCheckout();
  }

  @AfterTest
  public void teardown() {
    driver.quit();
  }

}
