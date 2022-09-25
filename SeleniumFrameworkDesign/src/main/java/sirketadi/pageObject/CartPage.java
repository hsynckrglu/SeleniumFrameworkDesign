package sirketadi.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sirketadi.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{ // ders154

	WebDriver driver;
	

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")
	WebElement CheckoutButton ;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> sepettekiÜrünlerinIsmi ;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryBox;
	
	
	By waitSelectCountry=By.cssSelector("input[placeholder='Select Country']");
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match=sepettekiÜrünlerinIsmi.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckoutButton() throws InterruptedException {
		Thread.sleep(2000);
		CheckoutButton.click();
		waitForElementToAppear(waitSelectCountry);
		CheckoutPage checkoutPage =new CheckoutPage(driver);
		return checkoutPage;
		
		
	}
	public void getAction(String country) {
		Actions a=new Actions(driver);
		a.sendKeys(countryBox, country).build().perform();
		
	}
	
	

	
}
