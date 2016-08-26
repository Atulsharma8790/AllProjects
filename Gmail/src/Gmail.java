import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Gmail {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static HtmlUnitDriver driver;
	public static void main(String[] args) throws InterruptedException {
		try{

			String email = args[0];
			String passwd = args[1];

			if(email.equals(""))
			{
				System.out.println("Email can't be Empty");
				System.out.println("Please enter the Valid Email address with the space");
				System.out.println("Quitting Now! Run the file again");
				Thread.sleep(2000);
				driver.quit();
			}
			else if (passwd.equals(""))
			{
				System.out.println("Password can't be Empty");
				System.out.println("Please enter the Valid Password with the space");
				System.out.println("Quitting Now! Run the file again");
				Thread.sleep(2000);
				driver.quit();
			}
			
			System.out.println("Running the script now...");

			driver = new HtmlUnitDriver(true);
			driver.setJavascriptEnabled(true);
			System.out.println("Opened Firefox browser");
			driver.get("http://gmail.com/");
			System.out.println("Opened Gmail Website");

			{
				driver.findElement(By.name("Email")).sendKeys(email);
				System.out.println("Entered the Email");
				driver.findElement(By.name("signIn")).click();
				Thread.sleep(2000);
				if(driver.findElement(By.id("errormsg_0_Email")).isDisplayed())
				{
					System.out.println("Sorry, Google doesn't recognize that email. Please enter Valid Email ID");
					System.out.println("Quitting Now! Run the file again");
					Thread.sleep(2000);
					driver.quit();
				}
				else
				{

					driver.findElement(By.name("Passwd")).sendKeys(passwd);
					System.out.println("Entered the Password");
					Thread.sleep(1000);

					driver.findElement(By.id("signIn")).click();
					
						
					Thread.sleep(20000);
					
					//if(driver.findElement(By.id("errormsg_0_Passwd")).isDisplayed())
					WebDriverWait wait = new WebDriverWait(driver, 5);
					try{
					if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=':2x']"))) != null)
					{
						
						/* SOCIAL TAB IN GMAIL */
						System.out.println("Successfully Logged In Gmail Account");

						driver.findElement(By.xpath("//*[@id=':2x']")).click();
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("//*[@id=':2i']/div[1]/div[2]/div/div/div")).isDisplayed())
						{
							System.out.println("Social Tab is empty");
						}
						else
						{
							driver.findElement(By.xpath("//*[@id=':32']/div[1]/span/div")).click();
							Thread.sleep(2000);
							if(driver.findElement(By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div")).isDisplayed())
							{
								driver.findElement(By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div")).click();
								System.out.println("Social Mails deleted");
							}
							else
							{
								System.out.println("Social Delete Element not displayed");
							}
							Thread.sleep(5000);
						}
						/* PROMOTION TAB IN GMAIL */
						driver.findElement(By.xpath("//*[@id=':2y']")).click();
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("//*[@id=':2j']/div[1]/div[2]/div/div/div")).isDisplayed())
						{
							System.out.println("Promotion Tab is empty");
						}
						else
						{
							if(driver.findElement(By.xpath("//*[@id=':2']/div/div/div[6]/div[1]/table/tbody/tr[1]/td[8]/span/img")).isDisplayed())
							{

								List<WebElement> list = driver.findElements(By.xpath("//*[@id=':2']/div/div/div[6]/div[1]/table/tbody/tr/td[8]/span/img"));
								System.out.println("Total promotions = "+list.size());
								for(int i=0;i<list.size();i++)
								{
									driver.findElement(By.xpath("//*[@id=':2']/div/div/div[6]/div[1]/table/tbody/tr[1]/td[8]/span/img")).click();
									System.out.println("Promotion Removed");
								}


							}
							Thread.sleep(4000);
							driver.findElement(By.xpath("//*[@id=':32']/div[1]/span/div")).click();
							Thread.sleep(2000);
							if(driver.findElement(By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div")).isDisplayed())
							{
								driver.findElement(By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div")).click();
								System.out.println("Promotion Mails deleted");
							}
							else
							{
								System.out.println("Promotion Delete Element not displayed");
							}
						}
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@id=':2w']")).click();
						System.out.println("At inbox Tab now");
						Thread.sleep(2000);
						driver.quit();
						System.out.println("Quit from the browser done");
					}
				}
					catch(Exception e)
					{
						System.out.println("Sorry, Email & Password doesn't match. Please enter Valid Password.");
						System.out.println("Quitting Now! Run the file again");
						Thread.sleep(1000);
						driver.quit();
					}
					
				}
				
			}
		}
		catch(Exception e){
			System.out.println("Runtime Exception Occurs due to invalid Input");
			System.out.println("Please Enter the Valid Email ID & Password.");

		}
	}
	
}
