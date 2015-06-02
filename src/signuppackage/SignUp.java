package signuppackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class SignUp {
	public String baseUrl="http://www.iheart.com";
	public WebDriver driver;
	public WebDriverWait wait;
	
	
  @Test (description="Open the www.iheart.com")
  public void GoToHomePage() {
	  System.setProperty ("webdriver.chrome.driver", "C:\\Program Files (x86)\\selenium-java-2.45.0\\selenium-2.45.0\\chromedriver_win32\\chromedriver.exe");
	 driver = new ChromeDriver();
	 //driver = new FirefoxDriver();
	  driver.get(baseUrl);
	  driver.manage().window().maximize();
	  
  }
  
  @Test (description="Select the first two generes and get the station play")
  public void SelectGenres() throws InterruptedException{
	    Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div[1]/ul/li[1]/div/div[1]")).click();
		driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div[1]/ul/li[2]/div/div[1]")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/div[2]/div[2]/div/div[1]/div/button")).click();
		Thread.sleep(4000);
		WebDriverWait myWaitVar = new WebDriverWait(driver, 10);
		myWaitVar.until (ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='player']/div[2]/div/button[3]")));
		driver.findElement(By.xpath("//*[@id='player']/div[2]/div/button[3]")).click();
		Thread.sleep(30000);
		
	
  }
  
  @Test(description="Sign up with a e-mail account")
  public void SignUp() throws InterruptedException{
	 
	  //click on the sign up button
	 driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/button[2]")).click();
	 
	 //Get the current time and incorporate into the E-mail address
	 
	Date date = new Date();
	Long NewTime = date.getTime();
	
	 
	 //fill the information for email, password, zip code, birth year, gender selection, aree term, sign up button
	 
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/section[1]/input")).sendKeys("DowneyTest_"+NewTime+"@example.com");
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/section[2]/input")).sendKeys("test4321");
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/div[1]/section/input")).sendKeys("10012");
	
	 new Select(driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/div[1]/div/div/select"))).selectByVisibleText("1989");
	 
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/div[2]/div/label[1]/span[1]/input")).click();
	 
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/div[3]/label/span[1]/input")).click();
	 
	 driver.findElement(By.xpath("//*[@id='dialog']/div/div[2]/div[2]/div/div/form/button")).click();
	 Thread.sleep(3000);
  }
  
  @Test (priority = 1) // play 106.7 lite fm and make sure it is show up in My Stations
  public void MyStations() throws InterruptedException {


	//Go to My Station
	  // driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button"html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button"")).click();
	 //driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/button[2]")).click();
	  Thread.sleep(15000);
	 
	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button")).click();
	// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Log Out")));
	  Thread.sleep(3000);
	  Actions action = new Actions(driver);
	 // WebElement buttonup = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button"));
	  WebElement MyStation = driver.findElement(By.partialLinkText("My Stations"));
	 // action.moveToElement(buttonup).click().perform();
	  action.moveToElement(MyStation).perform();
	  Thread.sleep(3000);
	  action.moveToElement(MyStation).click().perform();
	  Thread.sleep(3000);
	  
	  //Get the title of the station I played/saved
	  
	  String StationTitle_01=driver.findElement(By.xpath("//*[@id='main']/div/section/ul/li[1]/div/div[2]/a")).getText();
	  System.out.println(StationTitle_01);
	  Assert.assertEquals(StationTitle_01, "106.7 Lite fm");

 
  }
	
  
  @AfterTest(description="Logout the email account")
  
  public void logout() throws InterruptedException{
	  
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button")).click();
	// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Log Out")));
	  Thread.sleep(3000);
	  Actions action = new Actions(driver);
	  WebElement we = driver.findElement(By.partialLinkText("Log Out"));
	  action.moveToElement(we).perform();
	  Thread.sleep (3000);
	  action.moveToElement(we).click().perform();// click logout button if clickable
	  Thread.sleep(3000);
	 
	  // assert if this back to main page after logging out
	String	Titileoflogoutpage = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div[2]/div/button[1]")).getText();
	Assert.assertEquals(Titileoflogoutpage, "Log In");
	
	Thread.sleep(3000);
	driver.close();
	 
  }
}