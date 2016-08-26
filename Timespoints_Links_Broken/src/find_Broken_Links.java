import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class find_Broken_Links {

	/**
	 * @param args
	 */
	static int invalidLinks;
	static int invalidImageUrl;
	static WebDriver driver;
	static int JavascriptLinksCount;

	public static void main(String[] args) throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		WebsitePageLinks("http://www.timespoints.com/");
		System.out.println(driver.getCurrentUrl());
		Login("9953893369","times@123");
		Thread.sleep(5000L);
		pageURLs();
		ProductCount();

	}
	public static void pageURLs(){
		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://www.timespoints.com/#/index");
		urls.add("http://www.timespoints.com/#/myPoints");
		urls.add("http://www.timespoints.com/#/myBadges");
		urls.add("http://www.timespoints.com/#/redeem");
		urls.add("http://www.timespoints.com/#/redemptionHistory");
		urls.add("http://www.timespoints.com/#/contest");

		for (int i=0;i<urls.size();i++)
		{
			System.out.println("Currently Checking URL: "+urls.get(i));
			WebsitePageLinks(urls.get(i).toString());

		}


	}
	public static void ProductCount() throws InterruptedException{
		driver.navigate().to("http://www.timespoints.com/#/redeem");
		Thread.sleep(4000L);
		List<WebElement> list = driver.findElements(By.linkText("View Details"));
		System.out.println("Total Products: "+list.size());


		for(int j=1;j<=list.size();j++)
		{
			String URL = driver.findElement(By.xpath("//*[@id='gc-outer-div']/div[3]/div/div[2]/div/div/div[2]/div[5]/div[3]/ul["+j+"]/li/a[3]")).getAttribute("href").toString();
			System.out.println("URL is : "+URL);
			//listProductURLs.add(URL);
			WebsitePageLinks(URL);
			Thread.sleep(2000L);
			driver.get("http://www.timespoints.com/#/redeem");
			Thread.sleep(4000L);

		}
		Select select = new Select(driver.findElement(By.id("selectRangeId")));
		List<WebElement> dropValues = select.getOptions();
		for(int i=1;i<dropValues.size();i++)
		{
			System.out.println("Dropdown values: "+dropValues.get(i).getText());
			select.selectByVisibleText(dropValues.get(i).getText());
			Thread.sleep(1000L);
			List<WebElement> listProducts = driver.findElements(By.linkText("View Details"));

			System.out.println("Total Products for Range : "+dropValues.get(i).getText()+" : "+listProducts.size());
		}


		/*			for(int j=1;j<=listProducts.size();j++)
			{
				String URL = driver.findElement(By.xpath("//*[@id='gc-outer-div']/div[3]/div/div[2]/div/div/div[2]/div[5]/div[3]/ul["+j+"]/li/a[3]")).getAttribute("href").toString();
				System.out.println("URL is : "+URL);
				listProductURLs.add(URL);

				WebsitePageLinks(URL);
				Thread.sleep(2000L);
				driver.get("http://www.timespoints.com/#/redeem");
				Thread.sleep(4000L);
				Select select1 = new Select(driver.findElement(By.id("selectRangeId")));
				List<WebElement> dropValues1 = select1.getOptions();
				System.out.println("index value: "+i);		
				select1.selectByIndex(i);

				//select.selectByVisibleText(dropValues.get(i).getText());
			}
		 */

	}


	public static void Login(String id, String Passwd) throws InterruptedException{
		driver.findElement(By.xpath("//a[@class='sign-btn']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("emailId")).sendKeys(id);
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys(Passwd);
		driver.findElement(By.xpath("//input[@class='btn']")).click();
	}
	public static void WebsitePageLinks(String WebURL){
		try {

			driver.get(WebURL);
			//driver.get("http://www.timespoints.com/");

			Thread.sleep(1000L);
			invalidLinks = 0;
			JavascriptLinksCount=0;
			List<WebElement> allLinks  = driver.findElements(By.tagName("a"));
			List<WebElement> allImages = driver.findElements(By.tagName("img"));

			for (int i = 0; i < allLinks.size(); i++) {


				WebElement links = allLinks.get(i);
				if (links != null) {
					String url = (links.getAttribute("href"));
					boolean urlValue = url.contains("javascript");
					if(urlValue){
						JavascriptLinksCount++;
					}
					/*					System.out.println("Boolean Value : "+urlValue);*/
					if (url != null && !urlValue) {
						verifyLinksActive(url);
					} else {

						if(!url.contains("javascript")){
							invalidLinks++;
							System.out.println(url);
						}
					}
				}


				for(int j=0;j<allImages.size();j++){

					WebElement images = allImages.get(j);
					if(images != null)
					{
						String imageUrl = (images.getAttribute("src"));
						if (imageUrl != null && !imageUrl.contains("javascript")) {
							verifyLinksActive(imageUrl);
						} else {
							invalidImageUrl++;
							System.out.println("Invalid URL: "+ imageUrl);
						}
					}
				}

			}
			System.out.println("Total invalid Links are : " + invalidLinks);
			System.out.println("Total invalid Images are : "+invalidImageUrl);
			System.out.println("Total  Links are " + (allLinks.size()-JavascriptLinksCount));
		} 

		catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public static void verifyLinksActive(String URL) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(URL);
			HttpResponse response = client.execute(request);
			//HttpResponse response = new DefaultHttpClient().execute(new HttpGet(links.getAttribute("href")));
			if (response.getStatusLine().getStatusCode() != 200)
			{
				//invalidLinks++;
				System.out.println("URL which is invalid: "+URL);
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}





