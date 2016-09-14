package zendesk.testauto.scripts;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ValidateZendeskIntegrationTC {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();   
		 // driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = "https://portal-staging.rcrsv.io/login";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testValidateSalesforceIntegrationTC() throws Exception {
		  driver.get(baseUrl);
		  driver.findElement(By.id("email")).clear();
		  driver.findElement(By.id("email")).sendKeys("chintan.patel@adactin.com");
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys("Adactin123");
		  driver.findElement(By.cssSelector("button[type=\"submit\"]")).submit();
	      Robot uiBtn = new Robot();
	    
	    //mouse over "Manage Integrations"
	      uiBtn.mouseMove(2180,808);
	    
	 
	    if(driver.findElement(By.className("main-nav--collapsible ")).isDisplayed()){
	
	    	System.out.println("Manage Integrations Tab Location --> "+driver.findElement(By.className("main-nav--collapsible ")).getLocation());
	
	    	Actions builder = new Actions(driver);   
	    
	        builder.moveToElement(driver.findElement(By.className("main-nav--collapsible "))).build().perform();
	    }
	    


	     Thread.sleep(3000);
	    
	     if(driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[6]/ul/li[1]/a/span")).isDisplayed()){
	    	 System.out.println("Manage Integrations Hovered! Displaying sub menus");
	    	    WebElement subMenuInt = driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[6]/ul/li[1]/a/span"));
	    	  
	    	    Actions action2 = new Actions(driver);
	    	    action2.click(subMenuInt).build().perform();
	    	    Thread.sleep(3000);
	     }
	     Thread.sleep(3000);
	  
	     if(driver.findElement(By.xpath("/html/body/div[2]/section/div/div/div/article/div/div[4]/a/p")).isDisplayed()){
	    	 System.out.println("Listing all Integrations..");
	 	    WebElement integrationZendesk = driver.findElement(By.xpath("/html/body/div[2]/section/div/div/div/article/div/div[4]/a/p"));

	 	
	 	    Actions action3 = new Actions(driver);
	 	    action3.click(integrationZendesk).build().perform();
	 	    Thread.sleep(3000);
	    	 
	     }


	    
	  }

	  @After
	  public void tearDown() throws Exception {
		 
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
