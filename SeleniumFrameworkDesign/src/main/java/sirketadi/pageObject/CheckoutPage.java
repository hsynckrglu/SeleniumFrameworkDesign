package sirketadi.pageObject;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sirketadi.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{ 

// ders154

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryBox ;
	
	@FindBy(css="span[class='ng-star-inserted']")
	WebElement selectCountryTr ;
	
	@FindBy(xpath="//*[@class='form__cc']/div[2]/div[2]/input")
	WebElement cvvCodeBox ;
	
	@FindBy(xpath="//*[@class='form__cc']/div[3]/div[1]/input")
	WebElement nameCardBox;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrderButton;
	
	
	
	
	public void checkInformation(String countryName,String cvvCode, String cardName) throws InterruptedException {
		Actions a=new Actions(driver);
		a.sendKeys(countryBox, countryName).build().perform();
		Thread.sleep(1000);
		a.moveToElement(selectCountryTr).click().build().perform();
		a.sendKeys(cvvCodeBox, cvvCode).build().perform();
		a.sendKeys(nameCardBox, cardName).build().perform();
	}
	
	public ConfirmaitonPage goToPlaceOrder() throws InterruptedException {
		Actions a= new Actions(driver);
		a.click(placeOrderButton).build().perform();
		Thread.sleep(1000);
		ConfirmaitonPage confirmaitonPage = new ConfirmaitonPage(driver);
		return confirmaitonPage;
		
		
		
	}
	
	
	
	
}
