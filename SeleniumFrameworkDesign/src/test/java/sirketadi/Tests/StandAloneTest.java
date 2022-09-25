package sirketadi.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {
	static String email = "example28@gmail.com";
	static String password = "123456Hc.";
	static String productName="ZARA COAT 3";
	static String cvvCode="148";
    static String cardName="Ziraat kartı";
    static String applyCoupon="123456";
    static String countryName="Turkey";

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[id='toast-container']"))));
		String loginMassage =driver.findElement(By.cssSelector("div[id='toast-container']")).getText();
		System.out.println("login mesajı: "+loginMassage);
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".col-lg-4"))));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement ürün = products.stream()
				.filter(s -> s.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst()
				.orElse(null);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".col-lg-4"))));
	    
		/* ne kadar zara coat 3 ürünü varsa bile ilkini döndür diyoruz.
		 * ürün değişkeninde yakalıyoruz. eğer hiç bir ürün döndüremezse ürün değişkeni null döner.
		 * orElse(null) budur.  */
		
		ürün.findElement(By.cssSelector(".btn.w-10.rounded")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".cartSection h3"))));
		List<WebElement> sepettekiÜrünlerinIsmi=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=sepettekiÜrünlerinIsmi.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("input[placeholder='Select Country']"))));
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), countryName).build().perform();
		
		//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Turkey");
		Thread.sleep(1000);
		a.moveToElement(driver.findElement(By.cssSelector("span[class='ng-star-inserted']"))).click().build().perform();
		a.sendKeys(driver.findElement(By.xpath("//*[@class='form__cc']/div[2]/div[2]/input")), cvvCode).build().perform();
		a.sendKeys(driver.findElement(By.xpath("//*[@class='form__cc']/div[3]/div[1]/input")), cardName).build().perform();
		
		a.click(driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"))).build().perform();
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,-1000)");
		System.out.println(driver.findElement(By.id("toast-container")).getText());
        String Massege=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(Massege,"THANKYOU FOR THE ORDER.");
        driver.quit();

	}

}
