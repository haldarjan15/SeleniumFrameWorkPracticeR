package FrameworkDesign.SeleniumFrameWorkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FrameworkDesign.SeleniumFrameWorkPractice.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
//		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products ;
	
	@FindBy(css=".ng-animating")
	WebElement spinner ;
	//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");

	
	public List<WebElement> getProduct() 
	{
		waitforElementToAppear(productBy);
		return products;
	}
	
	
	public WebElement getProductByName(String productName)
	{
	
	WebElement prod=getProduct().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
	return prod;
	
	}
	
	public void addProductToCart(String productName)
	{	WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
		
	}
}
