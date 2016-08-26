
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Headless_testcase {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {


		HtmlUnitDriver driver = new HtmlUnitDriver(true);
		driver.setJavascriptEnabled(true);
		driver.get("http://gmail.com/");
		System.out.println(driver.getTitle().toString());
		
		String domainName = (String) driver.executeScript("return document.domain");
		System.out.println("Domain name is " + domainName);

	}

}
