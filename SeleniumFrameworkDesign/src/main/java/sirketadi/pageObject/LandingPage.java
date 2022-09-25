package sirketadi.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sirketadi.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent { // ders154

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement emailBox=driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement emailBox;     /*
							 * yandaki ifade WebElement emailBox=driver.findElement(By.id("userEmail")); ile
							 * aynı şeydir
							 */
	@FindBy(id = "userPassword")
	WebElement passwordBox;

	@FindBy(id = "login")
	WebElement submitButton;
	
	@FindBy(css="dasfdadef")
	WebElement isim;
			
	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;		
			
					
	public ProductCatalogue loginApplication(String userEmail,String Userpassword) {
		emailBox.sendKeys(userEmail);
		passwordBox.sendKeys(Userpassword);
		submitButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		

	}
	
	public void goToURL(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
}
