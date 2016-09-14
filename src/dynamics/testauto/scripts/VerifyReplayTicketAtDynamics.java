package dynamics.testauto.scripts;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.io.File;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyReplayTicketAtDynamics {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
	driver = new ChromeDriver();   
	driver.manage().window().maximize();
    baseUrl = "https://login.microsoftonline.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testVerifyReplayRecAtDynamics() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("cred_userid_inputtext")).clear();
    driver.findElement(By.id("cred_userid_inputtext")).sendKeys("testing1@recursivelabs.net");
    driver.findElement(By.id("cred_password_inputtext")).clear();
    driver.findElement(By.id("cred_password_inputtext")).sendKeys("Adactin123");
    
    WebElement loginBtn = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td/div[2]/div[1]/ul/li[4]/button[1]"));
	 WebDriverWait wait = new WebDriverWait(driver, 10);
	  // WebElement previewBtn = driver.findElement(By.linkText("Preview Video"));
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/table/tbody/tr/td/div[2]/div[1]/ul/li[4]/button[1]")));
	 System.out.println("after the wait here..");
    if(loginBtn.isDisplayed()){
    	System.out.println("Login button found.. executing..");
    	 Actions action = new Actions(driver);
    	 action.moveToElement(loginBtn).build().perform();
    	 action.click(loginBtn).build().perform();
    	 
    }
   
    //driver.findElement(By.id("cred_sign_in_button")).click();
    //Thread.sleep(10000);
    WebElement homeTab = driver.findElement(By.cssSelector("#HomeTabLink > span.navTabButtonImageContainer"));
    //checking if the landing page has been loaded after logging in
    if(homeTab.isDisplayed()){
    	//Thread.sleep(3000);
    	System.out.println("Changing to Services Tab..");
    	Actions action = new Actions(driver);
   	 	action.moveToElement(homeTab).build().perform();
   	 	action.click(homeTab).build().perform();
   	 	Thread.sleep(1000);
   	    driver.findElement(By.cssSelector("#CS > span.navActionButtonIconContainer > span.navActionButtonIcon")).click();
   	    //driver.findElement(By.cssSelector("#nav_cases > span.nav-rowLabel")).click();
   	    Thread.sleep(1000);
   	    //driver.findElement(By.cssSelector("span.nav-rowLabel")).click();
   	    
   	    //driver.findElement(By.cssSelector("#HomeTabLink > span.navTabButtonImageContainer")).click();
   	    //driver.findElement(By.cssSelector("img[alt=\"Service\"]")).click();
   	    driver.findElement(By.cssSelector("#nav_cases > span.nav-rowLabel")).click();
   	    Thread.sleep(1000);
    	
    }
    WebElement temp = driver.findElement(By.xpath("//div[contains(@id, 'crmContentPanel')]"));
    if(temp.isDisplayed()){
    	System.out.println("This is the page source --> "+temp.getAttribute("innerHTML"));
    }
    //driver.switchTo().frame(driver.findElement(By.id("contentIFrame1")));
    List<WebElement> myIframes= driver.findElements(By.tagName("iframe"));
    for (int i = 0; i < myIframes.size(); i++) {
    	String frameName = (String) myIframes.get(i).getAttribute("name");
    	System.out.println("These are the frames currently ["+i+"] "+frameName);
		
	}
    driver.switchTo().frame(driver.findElement(By.id("contentIFrame0")));
    List<WebElement> myIframes2= driver.findElements(By.tagName("iframe"));
    for (int i = 0; i < myIframes2.size(); i++) {
    	String frameName = (String) myIframes2.get(i).getAttribute("name");
    	System.out.println("These are the frames 2 currently ["+i+"] "+frameName);
		
	}
    
    
    WebElement createdOnDesc = driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label"));
    if(createdOnDesc.isDisplayed()){
    	System.out.println("before the search input..");
    	Thread.sleep(12000);
    }
    
    WebElement searchCase = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[1]/div[2]/input"));
    if(searchCase.isDisplayed()){
    	System.out.println("Found the search input..");
    	driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[1]/div[2]/input")).clear();
    	driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[1]/div[2]/input")).click();
    	driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[1]/div[2]/input")).sendKeys("Dynamics Ticket UI AUG31");
    	
    	driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[2]/a[1]")).click();
    }
    Thread.sleep(3000);
    WebElement searchCaseBtn = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td/span/table/tbody/tr/td[3]/table/tbody/tr/td[2]/a[1]"));
    if(searchCaseBtn.isDisplayed()){
    	System.out.println("Found the search button..");
    	Actions action = new Actions(driver);
   	 	action.doubleClick(searchCaseBtn).build().perform();
    }
    /*
    WebElement createdOnDesc = driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label"));
    if(createdOnDesc.isDisplayed()){
        //driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label")).click();
        Actions action = new Actions(driver);
   	 	action.doubleClick(createdOnDesc).build().perform();
        
    }   
    Thread.sleep(3000);
    //driver.switchTo().frame(driver.findElement(By.id("contentIFrame0")));
    WebElement createdOnAsc = driver.findElement(By.xpath("//html/body/div/div[3]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[1]/div/table/tbody/tr/th[7]/table/tbody/tr/td[1]/a"));
    if(createdOnAsc.isDisplayed()){
    	System.out.println("Created On ASC appears..");
    	Actions action2 = new Actions(driver);
    	action2.moveToElement(createdOnAsc).build().perform();
        action2.doubleClick(createdOnAsc).build().perform();
    }*/
    
        //driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label")).click();
      
        //driver.findElement(By.id("gridBodyTable_primaryField_{24F58C23-3A6F-E611-80DC-C4346BAC4B14}_0")).click();
        driver.switchTo().frame(driver.findElement(By.id("contentIFrame1")));
       // driver.switchTo().frame(driver.findElement(By.id("exportFrame")));
        List<WebElement> myIframes3= driver.findElements(By.tagName("iframe"));
        for (int i = 0; i < myIframes3.size(); i++) {
        	String frameName = (String) myIframes3.get(i).getAttribute("name");
        	System.out.println("These are the frames 3 currently ["+i+"] "+frameName);
    		
    	}
        //
        WebElement replayCase = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[2]/div/div/div/div[1]/table/tbody/tr/td[3]/nobr/a"));
        if(replayCase.isDisplayed()){
        	Actions action2 = new Actions(driver);
        	//action2.moveToElement(createdOnAsc).build().perform();
            action2.doubleClick(replayCase).build().perform();
        }
        
        /*if(driver.findElement(By.xpath("//*[contains(@title, 'Dynamics Ticket UI AUG31')]")).isDisplayed()){
        	System.out.println("Found the replay dynamics ticket ui ticket...");
        }*/
   

    
    /*driver.switchTo().frame(driver.findElement(By.id("dashboardFrame")));
    List<WebElement> myIframes3= driver.findElements(By.tagName("iframe"));
    for (int i = 0; i < myIframes3.size(); i++) {
    	String frameName = (String) myIframes3.get(i).getAttribute("name");
    	System.out.println("These are the frames 2 currently ["+i+"] "+frameName);
		
	}
    
    driver.switchTo().frame(driver.findElement(By.id("WebResource_PersonalWall")));*/
    
    
    //WebResource_PersonalWall
    //WebElement source_code = driver.findElement(By.id("contentIFrame0"));
    //WebElement temp = driver.findElement(By.xpath("//div[contains(@id, 'wallElementContainer')]"));
    
    //elem = wd.find_element_by_css_selector('#my-id')
    //html = driver.getPageSource() execute_script("return arguments[0].innerHTML;", source_code);
    //driver.switchTo().frame(driver.findElement(By.id("dashboardFrame")));
    //driver.switchTo().frame(driver.findElement(By.id("WebResource_PersonalWall")));
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | contentIFrame1 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | dashboardFrame | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | WebResource_PersonalWall | ]]
    
    /*if(driver.findElement(By.linkText("Replay-Dynamics UI SEP31")).isDisplayed()){
    	System.out.println("Found the replay instance..");
    	Thread.sleep(3000);
    	Robot uiBtn = new Robot();
    	uiBtn.mouseMove(2180,808);
    	Thread.sleep(5000);
    	
    	WebElement replayCase = driver.findElement(By.linkText("Replay-Dynamics UI SEP31"));
    	//WebElement replayCase = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[8]/div[2]/div[2]/div[1]"));
    	///html/body/div[1]/div[1]/div[8]/div[2]/div[2]/div[1]
    	Actions action = new Actions(driver);
   	 	//action.moveToElement(replayCase).build().perform();
   	 	//action.click(replayCase).build().perform();
   	 	action.doubleClick(replayCase).build().perform();
    	Thread.sleep(12000);
    }*/
    
    
    /*driver.findElement(By.cssSelector("#HomeTabLink > span.navTabButtonImageContainer")).click();
    driver.findElement(By.cssSelector("#CS > span.navActionButtonIconContainer > span.navActionButtonIcon")).click();
    driver.findElement(By.cssSelector("#nav_cases > span.nav-rowLabel")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | contentIFrame0 | ]]
    driver.findElement(By.id("crmGrid_findCriteria")).clear();
    driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("Replay-Dynamics UI");
    driver.findElement(By.id("crmGrid_findCriteriaImg")).click();    */
    Thread.sleep(10000);
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
