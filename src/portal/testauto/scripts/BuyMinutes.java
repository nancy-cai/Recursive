package portal.testauto.scripts;

import static org.junit.Assert.*;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyMinutes {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private String baseUrl;
	private WebElement email;
	private WebElement password;
	private WebElement login;
	private WebElement sideMenu;
	private WebElement buyMin;
	private WebDriverWait wait;
	private WebElement minutes;
	private Integer min;
	private WebElement totalPrice;
	private String actualTotalPrice;
	private String expectedTotalPrice;
	private WebElement addToCart;
	private WebElement successMsg;
	private String expectedSuccessMsg;
	private String actualSuccessMsg;
	public WebElement checkoutButton;
	
	 @Before
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();   
		baseUrl = "https://portal-staging.rcrsv.io/login";
		driver.get(baseUrl);
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	@Test
	public void buyMinutes() throws Exception {
		waits = new WaitLoopFunction();
		enterEmail();
		enterPassword();
		clickLogin();
		waits.waitLoop(driver,By.className("main-nav__link "));
		hoverSideMenu();
		waits.waitLoop(driver,By.xpath("/html/body/div[2]/aside/ul/li[5]/a"));
		clickBuyMoreMinute();
		min=1;
		enterMinutesToBuy();
		pressEnter();
		getActualTotalPrice();
		getExpectedTotalPrice();
		checkTotalPrice();
		hoverAddToCart();
		waits.waitLoop(driver,By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		clickAddToCart();
		assertSuccessMessage();
	}
	
	 @After
	  public void close() throws Exception {
		 
	    driver.quit();
	 }
	
	 
	 
	private void enterEmail(){
		 email = driver.findElement(By.id("email"));
		 email.clear();
		 email.sendKeys("chintan.patel@adactin.com");
	}
	
	private void enterPassword(){
		password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Adactin123");
	}
	
	private void clickLogin(){
		login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();
		
	}
	
	private void hoverSideMenu(){
    	Actions builder = new Actions(driver);  
    	sideMenu =driver.findElement(By.className("main-nav__link "));
        builder.moveToElement(sideMenu)
        .build()
        .perform();
	}
	
	private void clickBuyMoreMinute(){
		
		buyMin = driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[5]/a"));
		buyMin.click();
	}
	
	private void enterMinutesToBuy(){
		minutes = driver.findElement(By.id("customMinutesInput"));
		minutes.clear();
		minutes.sendKeys(Integer.toString(min));
	}
	
	private void pressEnter(){
		minutes.sendKeys(Keys.ENTER);
	}
	
	private String getActualTotalPrice(){
		totalPrice = driver.findElement(By.id("customMinutesTotalCost"));		
		actualTotalPrice = totalPrice.getText();
		System.out.println("Actual Price is "+actualTotalPrice);
		return actualTotalPrice;
		
	}
	
	private String getExpectedTotalPrice(){
		expectedTotalPrice = "$"+Double.toString(min*1.25);
		if(expectedTotalPrice.length()==4){
			expectedTotalPrice = expectedTotalPrice + "0";
			
		}
		System.out.println("Expected price is " + expectedTotalPrice);
		return expectedTotalPrice;
	}
	
	private void checkTotalPrice(){
	
		assertEquals(expectedTotalPrice, actualTotalPrice);
	}
	
	private void hoverAddToCart(){
    	Actions builder1 = new Actions(driver);
    	addToCart = driver.findElement(By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
        builder1.moveToElement(addToCart)
        .build()
        .perform();
	}
	
	private void clickAddToCart() throws InterruptedException{

		addToCart.click();
		Thread.sleep(3000);
	}
	
	private void assertSuccessMessage(){
		if(min == 1){
			expectedSuccessMsg = "Added "+ min.toString()+ " minute to your cart.";
		}else{
			expectedSuccessMsg = "Added "+ min.toString()+ " minutes to your cart.";
		}
		
		successMsg = driver.findElement(By.xpath(".//*[@id='successMessage']"));
		actualSuccessMsg = successMsg.getText();
		System.out.println("Expected success message is "+ expectedSuccessMsg);
		System.out.println("Actual success message is "+ actualSuccessMsg);
		assertEquals(expectedSuccessMsg,actualSuccessMsg);
	}
	
	public void clickCheckout(){
		checkoutButton = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>a>button"));
		checkoutButton.click();
	}


}
