package portal.testauto.scripts;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutWithNewCard {

	public WebDriver driver;
	public BuyMinutesFunction cart1; 
	public WebElement paymentMethods;
	public WebDriverWait wait;
	public WaitLoopFunction waits;
	public String paymentList;
	public List<WebElement> paymentLists;
	public WebElement checkout;
	public WebElement pay;
	public WebElement checkTransactionButton;
	
	 @Before
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();   
		driver.get("https://portal-staging.rcrsv.io/login");
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  }

	@Test
	public void buyMinutesCheckout() throws Exception {
		waits = new WaitLoopFunction();
		cart1 = new BuyMinutesFunction();
		cart1.BuyMinutesFunction(driver,1);		
		waits.waitLoop(driver, By.id("payment-method-select-btn"));
		clickPaymentMethods();
		chooseNewCard();
		clickCheckout();
		Thread.sleep(3000);
		dismissAlert();
		waits.waitLoop(driver, By.name("stripe_checkout_app"));
		fillInCardDetails();
		clickPayMoney();
	
	}

	 @After
	  public void close() throws Exception {
		 
	    driver.quit();
	 }
	
	public void clickPaymentMethods(){
		paymentMethods = driver.findElement(By.id("payment-method-select-btn"));
		paymentMethods.click();
		
	}
	
	public void chooseNewCard(){
		wait = new WebDriverWait(driver,60);
		paymentList = "payment-select-dropdown-menu";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(paymentList)));
	    paymentLists = driver.findElements(By.xpath("//*[@id='payment-select-dropdown-menu']/li/a"));
	    for(WebElement payment: paymentLists){
	    	if(payment.getAttribute("data-card").equalsIgnoreCase("new-card")){
	    		payment.click();
	    		break;	    		
	    	}
	    	
	    }
	    
	}
	
	  public void clickCheckout(){
		  checkout = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		  checkout.click();
		  
	  }
	  
	  public void dismissAlert() {
		  
		  if(driver.findElement(By.id("autoRefillModalDialog")).isDisplayed()){
			  
			  driver.findElement(By.id("noThanksButton"))
			  .click();
		  }
			  
		  

		}
	  
	 public void fillInCardDetails() throws Exception{
		 driver.switchTo().frame(driver.findElement(By.name("stripe_checkout_app")));
		 driver.findElement(By.id("email")).sendKeys("test@gmail.com");
		 driver.findElement(By.id("card_number")).sendKeys("5217");
		 driver.findElement(By.id("card_number")).sendKeys("7817");
		 driver.findElement(By.id("card_number")).sendKeys("1111");
		 driver.findElement(By.id("card_number")).sendKeys("5219");
		 Thread.sleep(1000);
		 driver.findElement(By.id("cc-exp")).sendKeys("03");
		 driver.findElement(By.id("cc-exp")).sendKeys("17");
		 driver.findElement(By.id("cc-csc")).sendKeys("123");
	 }
	 
	 public void clickPayMoney(){
		 pay =driver.findElement(By.id("submitButton"));
		 pay.click();
	 }


}
