package sirketadi.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sirketadi.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{ // ders154

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(css=".col-lg-4")
	List<WebElement> products ;
	
	@FindBy(css=".ng-animating")
	WebElement spinner ;
	
	By toastMessage=By.cssSelector("#toast-container");
	By product1=By.cssSelector(".col-lg-4");
	By addToCart=By.cssSelector(".btn.w-10.rounded");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(product1);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement ürün = products.stream()
				.filter(s -> s.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return ürün;
	
	}
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();	
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}
	
}
