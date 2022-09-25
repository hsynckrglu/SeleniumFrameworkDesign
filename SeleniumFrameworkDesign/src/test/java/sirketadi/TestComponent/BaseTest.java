package sirketadi.TestComponent;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import sirketadi.pageObject.LandingPage;

public class BaseTest {

	/*
	 * buraya teste baslamadan önce sayfanın ekranının max min olması gibi şeyler
	 * sonra webDriver driver= new ChromeDriver(); gibi şeyleri koyacağım.
	 */

	public WebDriver driver;
	
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException { // başlatıcı

		// properties class
		Properties prop = new Properties();
		// FileInputStream fis = new
		// FileInputStream("C:\\Users\\huseyin.cakiroglu\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\sirketadi\\Resources\\GlobalData.properties");

		/*
		 * FileInputStream fis = new FileInputStream(
		 * "C:\\Users\\huseyin.cakiroglu\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\sirketadi\\Resources\\GlobalData.properties"
		 * ); bu yol mainin içindeki GlobalData.properties dosyasının yolu bu testi ben
		 * birine verirsem o dosya onda olmayacağı için hata verecek bu yolu dinamik bir
		 * hale getirmek istersem şunu yapmalıyım. new
		 * FileInputStream(System.getProperty("user.dir")+"dosyanın yolu")
		 */

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//sirketadi//Resources//GlobalData.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox --> WebDriverManager.firefoxdriver().setup();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge --> WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\huseyin.cakiroglu\\Documents\\BrowserDriver\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		/*
		 * burayada ekranı büyütüp küçültmek istiyorsam yazabilirim.
		 * 
		 * driver.manage().window().maximize();
		 * 
		 */

		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToURL(driver);
		return landingpage;
		
		/*@BeforeMethod ile bu metodu hangi class miras alıyorsa, o classta ilk bu metod 
		 * kullanılıyor. 
		 * LandingPage landingpage=launchApplication(); bunu yazmama gerek kalmıyor diğer classta*/
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		
		/* @AfterMethod 
		 *  ile bu metodu hangi class miras alıyorsa, o classta son bu metod 
		 * kullanılıyor.
		 * landingpage.tearDown() yazmama gerek kalmıyor.
		 * */
		
	}

}
