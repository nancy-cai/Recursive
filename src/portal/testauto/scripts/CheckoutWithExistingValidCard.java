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

public class CheckoutWithExistingValidCard {
	public WebDriver driver;
	public BuyMinutesFunction cart1; 
	public WebElement paymentMethods;
	public WebDriverWait wait;
	public String paymentList;
	public List<WebElement> paymentLists;
	public WebElement checkout;
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
		
		cart1 = new BuyMinutesFunction();
		cart1.BuyMinutesFunction(driver,1);		
		clickPaymentMethods();
		chooseValidExistingCard();
		clickCheckout();
		dismissAlert();
		checkTransationHistory();
	}

	 @After
	  public void close() throws Exception {
		 
	    driver.quit();
	 }
	
	 
	 
	 
	 
	public void clickPaymentMethods(){
		paymentMethods = driver.findElement(By.id("payment-method-select-btn"));
		paymentMethods.click();
		
	}
	
	public void chooseValidExistingCard(){
		wait = new WebDriverWait(driver,60);
		paymentList = "payment-select-dropdown-menu";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(paymentList)));
	    paymentLists = driver.findElements(By.xpath("//*[@id='payment-select-dropdown-menu']/li/a"));
	    for(WebElement payment: paymentLists){
	    	if(payment.getAttribute("data-card").equalsIgnoreCase("use-existing-card")){
	    		payment.click();
	    		break;	    		
	    	}
	    	
	    }
	    
	}
	
	  public void clickCheckout(){
		  checkout = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		  checkout.click();
	  }
	  
	  public void dismissAlert() throws InterruptedException {
		    try {
		        WebDriverWait wait = new WebDriverWait(driver, 5);
		        wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = driver.switchTo().alert();
		        alert.dismiss();
		    } catch (Exception e) {
		        
		    }
		    Thread.sleep(3000);
		}
	  
	 public void checkTransationHistory(){
		 checkTransactionButton = driver.findElement(By.partialLinkText("View all Transactions"));
		 checkTransactionButton.click();
	 }
		

}
