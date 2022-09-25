package sirketadi.Tests;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import sirketadi.TestComponent.BaseTest;
import sirketadi.pageObject.CartPage;
import sirketadi.pageObject.CheckoutPage;
import sirketadi.pageObject.ConfirmaitonPage;
import sirketadi.pageObject.LandingPage;
import sirketadi.pageObject.OrdersPage;
import sirketadi.pageObject.ProductCatalogue;


public class SubmitOrderTest2 extends BaseTest {
	String email = "example28@gmail.com";
    String password = "123456Hc.";
	String productName="ZARA COAT 3";
	String cvvCode="148";
    String cardName="Ziraat kartı";
    String countryName="Turkey";
    

	@Test(dataProvider="getData",groups= {"Purchase"})
    public void submitOrder(String email,String password,String productName) throws IOException, InterruptedException {
    	//purchase= satın almak
		
	    
	   
		
		ProductCatalogue productCatalogue= landingpage.loginApplication(email, password);
		productCatalogue.getMassage();
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);// assert gibi koşulları metotların içinde yazmamaya özen göster.
		//metotlara sadece eylemleri yazmalısın.Doğrlamaları burada tut.
	    JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		CheckoutPage checkoutPage =  cartPage.goToCheckoutButton();
	    checkoutPage.checkInformation(countryName, cvvCode, cardName);
	    ConfirmaitonPage confirmaitonPage=checkoutPage.goToPlaceOrder();
		js.executeScript("window.scrollBy(0,-1000)");
		String Message=confirmaitonPage.confirmationMessage();
        Assert.assertEquals(Message,"THANKYOU FOR THE ORDER.");
        
        

	}
	@Test(dependsOnMethods= {"submitOrder"})  // yukarıdaki teste bağımlı yaptım
	public void OrderHistoryTest() throws InterruptedException  {
		/* @Test(dependsOnMethods= {"submitOrder"}) ile bu testi yukarıdaki teste submitOrder a bağımlı 
		 * hale getirdim.Eğer bu test başarılı bir şekilde koşturulursa bu teste geçilir.
		 * Üürünün eklendiğini gördük diyelim submitOrder testinde e ürün eklenmeseydi
		 * ben sipariş geçmişimden göremezdimki. O yüzden submitOrder testinin başarılı olması
		 * gewrekiyorki OrderHistoryTest testi koşturulabilsin.Mesela login ekranına giriş sağlayabilirsem ürün ekleyebilme
		 * testimi koşturabilirim gibi düşünülebilir. 
		 *   */
	    
		ProductCatalogue productCatalogue= landingpage.loginApplication(email, password);
		OrdersPage orderspage =productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderspage.verifyProductDisplay(productName));
	}
	@DataProvider
	public Object[][] getData() {
	/*verileri kendi belirlediğim sekilde oluşturuyorum.
	 sonrasında belirlenen datalar hangi test senaryosunda koşacaksa @Test(dataProvider="getData") yazıyorum 
	 Sonrasında submitOrderTest() parantez içine neyi tanımladıysam sırasıyla yazıyorum
	 object[][] içinde ilk email sonra password en sonda productName var 
	 Stringle beraber yazdım ;
	 public void submitOrder(String email,String password,String productName) şeklinde
	 ilk süslü parantez içine kaç tane değerle test yapmak istiyorsam o kadar süslü parantez açıyorum.
	 Örneğin { {"a","b"} {"a","c"} {"z","b"} {"s","s"} }--> burada 2 değişkenle 4 farklı senaryo kuşulacak.
	 diyelimki ilk değer email ikincisi ise password olabilir. */
	return	new Object[][]  {{"example28@gmail.com","123456Hc.","ZARA COAT 3" } , {"example28@gmail.com","123456Hc.","ADIDAS ORIGINAL"}}; 
	}


	
}
