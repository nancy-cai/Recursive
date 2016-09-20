package saleforce.testauto.scripts;

import static org.junit.Assert.fail;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyTicketAtSalesforce {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
	driver = new ChromeDriver(); 
    //driver = new FirefoxDriver();
	  
    baseUrl = "https://www.salesforce.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testVerifyTicketAtSalesforceInbox() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("button-login")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("testeradmin@recursivelabs.io");
    Thread.sleep(3000);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("wCtaMJ5MLgZo");
    Thread.sleep(3000);
    driver.findElement(By.id("Login")).click();
    //asking for the verification code part
    driver.findElement(By.id("emc")).clear();
   
    Thread.sleep(3000);


	WebElement codeVeri = driver.findElement(By.id("emc"));

		boolean codeRecNotEmpty = false;
		while(!codeRecNotEmpty){
			
			//Get the Verification code from email
			Thread.sleep(60000);
			String codeRec = codeVeri.getAttribute("value");
			System.out.println("This is the current code verification entered --> "+codeRec);
			if(codeRec != null && !codeRec.trim().equals("") && codeRec.trim().length() == 5){
				System.out.println("Code verification of 5 digits has been entered..submitting..");
				codeRecNotEmpty = true;
			
			}
		}
	
	
	Thread.sleep(6000);  
    driver.findElement(By.id("save")).click();
    Thread.sleep(5000);
    driver.findElement(By.linkText("Knowledge")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("span.arrow")).click();
    Thread.sleep(3000);
    driver.findElement(By.linkText("Draft")).click();
    Thread.sleep(3000);
    driver.findElement(By.linkText("Replay Ticket for Salesforce UI")).click();
    
    Thread.sleep(9000);
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
