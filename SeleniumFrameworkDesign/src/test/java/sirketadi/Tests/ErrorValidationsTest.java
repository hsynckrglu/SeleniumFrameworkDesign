package sirketadi.Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import sirketadi.TestComponent.BaseTest;
import sirketadi.pageObject.CartPage;
import sirketadi.pageObject.CheckoutPage;
import sirketadi.pageObject.ConfirmaitonPage;
import sirketadi.pageObject.LandingPage;
import sirketadi.pageObject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidations() throws IOException, InterruptedException {

		String email = "example28@gmail.com";
		String password = "123456Hc05484848541.";

		/*
		 * hatalı passwordle girmeye çalıştım. getErrorMessage() metodu ile hata mesajını aldım.
		 * bu metodu LandingPage classına tanımladım
		 */
		
		/*	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	    }  
	    
	    AbstractComponent classına yukarıdaki wait metodunu tanımladım.
	    Tanımlamasaydım  selenium bulamıyor metodu.   */

		landingpage.loginApplication(email, password);
		Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.");
		System.out.println(landingpage.getErrorMessage());

	}
	@Test
    public void ProductErrorValidations() throws IOException, InterruptedException {
    	
		String email = "example28@gmail.com";
	    String password = "123456Hc.";
		String productName="ZARA COAT 3";
		String incorrectProductName= "Zara Coat 3333";
		
	    
	   
		LandingPage landingpage=launchApplication();
		ProductCatalogue productCatalogue= landingpage.loginApplication(email, password);
		productCatalogue.getMassage();
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(incorrectProductName);
		Assert.assertFalse(match);
	
        

	}

	

}
