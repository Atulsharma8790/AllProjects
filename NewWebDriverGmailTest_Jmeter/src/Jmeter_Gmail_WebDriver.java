import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Jmeter_Gmail_WebDriver {

	public static WebDriver driver;

	@Before
	public void setUp(){
		driver = new FirefoxDriver();
	}

	@Test
	public void testWebdriverJUnit() throws InterruptedException{
		driver.get("http://gmail.com");
		driver.findElement(By.name("Email")).sendKeys("atulsharma8790@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("signIn")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("Passwd")).sendKeys("urmineforever");
		Thread.sleep(3000);
	}

	@After
	public void tearDown(){
		driver.quit();
	}
}