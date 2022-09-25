package sirketadi.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sirketadi.AbstractComponent.AbstractComponent;

public class ConfirmaitonPage extends AbstractComponent{ //onay sayfası

	WebDriver driver ;
	
	public ConfirmaitonPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement messageText;
	
	@FindBy(id="toast-container")
	WebElement messageText2;
	
	
	
	public String confirmationMessage() {
		System.out.println(messageText2.getText());
		String Massege= messageText.getText();
		return Massege;
		
	}
	
	
	
	

}
