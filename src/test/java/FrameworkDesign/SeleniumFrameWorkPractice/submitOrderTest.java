package FrameworkDesign.SeleniumFrameWorkPractice;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import FrameworkDesign.SeleniumFrameWorkPractice.pageobjects.CartPage;
import FrameworkDesign.SeleniumFrameWorkPractice.pageobjects.LandingPage;
import FrameworkDesign.SeleniumFrameWorkPractice.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;


public class submitOrderTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriverWait wait;
		String productName= "zara coat 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue=landingPage.LoginApplication("haldarjan15@gmail.com", "Neeloy@1234");
		
		List<WebElement>products=productCatalogue.getProduct();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage= productCatalogue.goToCartPage();
		
	
		Boolean match =cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartpage.goToCheckout();
		
	//	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
	//	List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		//Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
		
	//	driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "india").build().perform();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
	
		String confrimMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confrimMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
