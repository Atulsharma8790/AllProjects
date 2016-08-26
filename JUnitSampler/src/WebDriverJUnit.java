import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverJUnit {

	public static WebDriver driver;
	
	@Before
	public void setUp(){
		 driver = new FirefoxDriver();
		
	}
	
	@Test
	public void testWebdriverJUnit() throws InterruptedException{
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("Jmeter Testing");
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown(){
		driver.quit();
		
	}
}
