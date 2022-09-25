package sirketadi.Tests;




import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import sirketadi.pageObject.CartPage;
import sirketadi.pageObject.CheckoutPage;
import sirketadi.pageObject.ConfirmaitonPage;
import sirketadi.pageObject.LandingPage;
import sirketadi.pageObject.ProductCatalogue;

public class SubmitOrderTest {
	static String email = "example28@gmail.com";
	static String password = "123456Hc.";
	static String productName="ZARA COAT 3";
	static String cvvCode="148";
    static String cardName="Ziraat kartı";
    static String countryName="Turkey";

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		LandingPage landingpage= new LandingPage(driver);
		//landingpage.goToURL(driver);
		landingpage.loginApplication(email, password);
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.getMassage();
		
//		List<WebElement> products=productCatalogue.getProductList();
		
		productCatalogue.getProductByName(productName);
	
	    
		/* ne kadar zara coat 3 ürünü varsa bile ilkini döndür diyoruz.
		 * ürün değişkeninde yakalıyoruz. eğer hiç bir ürün döndüremezse ürün değişkeni null döner.
		 * orElse(null) budur.  */
		
		productCatalogue.addProductToCart(productName);
		
		productCatalogue.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay(productName);
		
		Assert.assertTrue(match);// assert gibi koşulları metotların içinde yazmamaya özen göster.
		//metotlara sadece eylemleri yazmalısın.Doğrlamaları burada tut.
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
        cartPage.goToCheckoutButton();
		
        CheckoutPage checkoutPage =new CheckoutPage(driver);
	    
	    checkoutPage.checkInformation(countryName, cvvCode, cardName);
	   
	    checkoutPage.goToPlaceOrder();
		js.executeScript("window.scrollBy(0,-1000)");
		
		ConfirmaitonPage confirmaitonPage = new ConfirmaitonPage(driver);
		String Message=confirmaitonPage.confirmationMessage();
        
        Assert.assertEquals(Message,"THANKYOU FOR THE ORDER.");
        driver.quit();
		

	}



}
