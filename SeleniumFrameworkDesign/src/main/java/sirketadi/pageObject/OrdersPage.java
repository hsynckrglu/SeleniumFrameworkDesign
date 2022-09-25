package sirketadi.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sirketadi.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent{ // ders154

	WebDriver driver;
	

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersTablosuUrunIsmi ;
	
	
	
	By waitSelectCountry=By.cssSelector("input[placeholder='Select Country']");
	
	
	public Boolean verifyProductDisplay(String productName) throws InterruptedException {
		Thread.sleep(1000);
		Boolean match=ordersTablosuUrunIsmi.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
}
	