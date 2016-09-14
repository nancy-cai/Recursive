package dynamics.testauto.scripts;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MicroSoftDynamicsRecTS {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
	  //driver = new FirefoxDriver();
    baseUrl = "http://testpages.clickwith.me/testpage.html";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMicroSoftDynamicsRecTS() throws Exception {
    driver.get(baseUrl);
    new Select(driver.findElement(By.id("ui"))).selectByVisibleText("dynamics-replay-kb");
    Thread.sleep(3000);
    driver.findElement(By.id("save")).click();
    Thread.sleep(6000);
    
    // Switch to the frame with the Recursive Button
    driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));
    
    WebElement temp = driver.findElement(By.xpath("//div[contains(@title, 'Replay by Recursive Labs')]"));
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@title, 'Replay by Recursive Labs')]")));
    
    if(temp.isDisplayed()){
       System.out.println("MS button appeared!");
       WebElement recurButtton = driver.findElement(By.xpath("//div[contains(@class, 'branding-logo')]"));
    	
       Actions action3 = new Actions(driver);
    	
       action3.moveToElement(recurButtton).build().perform();
	   Robot uiBtn = new Robot();
	   uiBtn.mouseMove(95,1640); 

       //driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	   Thread.sleep(3000); 
 	   action3.click(recurButtton).build().perform();
 	    
 	   //Thread.sleep(6000);
 	   System.out.println("MS button clicked!");
   	   WebElement startRec = driver.findElement(By.linkText("Start Recording"));   	    	   
   	   Actions action4 = new Actions(driver);
 	    
   	   
   	   action4.moveToElement(startRec).build().perform();
   	   //driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
   	   Thread.sleep(3000);   	   
   	   Robot uiBtn2 = new Robot();
	   uiBtn2.mouseMove(190,1660);
   	   action4.click(startRec).build().perform();
	       
	       System.out.println("Recording has started..wait for the audio record to finish initialising..");
	       //driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	       Thread.sleep(9000); 
	        
	       //added today because of slow internet
	       /*Thread.sleep(6000); 
	       Thread.sleep(6000); 
	       Thread.sleep(3000);*/ 
	  
	       //driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));
	       
	       //psdbComponent.clickDocumentLink();
	       ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	       for (int i = 0; i < tabs2.size(); i++) {
	    	   System.out.println("tab --> "+i);
	       }
	    
	       driver.switchTo().window(tabs2.get(1));
	    
	       //new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	       
	       System.out.println("accept alert..");
	       //driver.switchTo().alert().accept();
	       Robot uiBtn3 = new Robot();
	       uiBtn3.mouseMove(180,300);
	       Thread.sleep(6000); 
	      	       
	 	   Robot audioDialogBox = new Robot();
	 	   audioDialogBox.keyPress(KeyEvent.VK_ENTER);
	 	   Thread.sleep(6000); 	 	   
	 	   Point minAudioWin = new Point(2000, 1000);
	 	   driver.manage().window().setPosition(minAudioWin);/* setPosition(new Point(-2000, 0));*/
	 	   
	 	   driver.switchTo().window(tabs2.get(0));
    }
    Thread.sleep(3000);   
    
    //Do some browser activity to be recorded.
    
    Actions builders1 = new Actions(driver); 
    builders1.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(1) > a"))).build().perform();
    builders1.click().build().perform();
    Thread.sleep(3000);  
    
    /*Actions builders2 = new Actions(driver); 
	builders2.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(2) > a"))).build().perform();
	builders2.click().build().perform();
    Thread.sleep(3000);*/
    
    Actions builders3 = new Actions(driver); 
    builders3.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(3) > a"))).build().perform();
    builders3.click().build().perform();
    
    Thread.sleep(3000);  
    
    /*Actions builders4 = new Actions(driver); 
    builders4.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(4) > a"))).build().perform();
    builders4.click().build().perform();
    
    Thread.sleep(3000); */ 
    
    Actions builders5 = new Actions(driver); 
    builders5.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(5) > a"))).build().perform();
    builders5.click().build().perform();
	Thread.sleep(3000);  
	
	Actions builders6 = new Actions(driver); 
	builders6.moveToElement(driver.findElement(By.cssSelector("#sidebar > ul > li:nth-child(2) > a"))).build().perform();
	builders6.click().build().perform();
	
    Thread.sleep(6000);  
    //driver.findElement(By.xpath("(//a[contains(text(),'Components')])[2]")).click();
    //Thread.sleep(3000);  
    
    
    
    driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[4]")));
    //WebElement annotFrame = driver.findElement(By.xpath("//*[@id='rcrsv-annotation-toolbar']"));
    WebElement annotFrame = driver.findElement(By.cssSelector("#rcrsv-annotation-toolbar"));
    //#rcrsv-annotation-toolbar
    if(annotFrame.isDisplayed()){
    	System.out.println("annotation frame FOUND..");
        driver.findElement(By.id("atb-button-rectangle")).click();//rectangle
    	//driver.findElement(By.id("atb-button-pencil")).click();//atb-button-pencil
        
    	ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	       for (int i = 0; i < tabs2.size(); i++) {
	    	   System.out.println("tabs now after clicking annotation --> "+i);
	       }
	       Thread.sleep(3000);  
	       
	       //Back to main page to draw
	       driver.switchTo().window(tabs2.get(0));
	       
	       WebElement element = driver.findElement(By.cssSelector("#settings"));
	       if(element.isDisplayed()){
	    	   System.out.println("Back to main page to draw..");
	    	   /*Actions builder = new Actions(driver);
		       Action drawAction = builder.moveToElement(element,135,15) //start points x axis and y axis. 
		                 .click()
		                 .moveByOffset(200, 60) // 2nd points (x1,y1)
		                 .click()
		                 .moveByOffset(100, 70)// 3rd points (x2,y2)
		                 .doubleClick()
		                 .build();
		       drawAction.perform();*/
	    	   Actions recPen = new Actions(driver);
		       Action drawRecAction = recPen.moveToElement(element,330, 195).clickAndHold().moveByOffset(744, 370).moveByOffset(295, 514).release().build();
		       drawRecAction.perform();
		       		  
		       /*Actions builder = new Actions(driver);
		       Action drawAction = builder.moveByOffset(330, 195).clickAndHold().moveByOffset(744, 370).moveByOffset(295, 514)
		                 .release().build();
		       		  drawAction.perform();*/
		       Thread.sleep(8000);
		       
	      }
	       driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[4]")));//again
	       //WebElement annotFrame = driver.findElement(By.xpath("//*[@id='rcrsv-annotation-toolbar']"));
	       WebElement annotFrame2 = driver.findElement(By.cssSelector("#rcrsv-annotation-toolbar"));
	       //#rcrsv-annotation-toolbar
	       if(annotFrame2.isDisplayed()){
		       	System.out.println("change to arrow..");
		        driver.findElement(By.id("atb-button-arrow")).click();//--->
		      //Back to main page to draw
			       driver.switchTo().window(tabs2.get(0));
			       
			       WebElement elementOfOrigin = driver.findElement(By.cssSelector("body > div.container > div:nth-child(2) > div.col-md-9 > blockquote > p"));
			       if(element.isDisplayed()){
			    	   Actions arrowPen = new Actions(driver);
						Action drawArrowAction = arrowPen.moveToElement(elementOfOrigin,600, 150).clickAndHold().moveByOffset(135, 15).release().build();
						drawArrowAction.perform();
			       }
	       }

	       Thread.sleep(10000);         
	       
        //driver.findElement(By.id("atb-button-none")).click();

    }
    
    //Stopping the recording
 // Switch to the frame with the Recursive Button
    System.out.println("Switching to the Recursive Frame..");	
    driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));
    //driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@title, 'Recursive UI Widget')]")));
    //WebElement rcrscUI = driver.findElement(By.xpath("//div[contains(@title, 'ui recording-ui')]"));
    WebElement rcrscUI = driver.findElement(By.xpath("//div[contains(@class, 'ui recording-ui')]"));
    if(rcrscUI.isDisplayed()){
    	  System.out.println("Back to recursive UI, will now click the stop record button...");
    	  WebElement stopRec = driver.findElement(By.linkText("Stop"));   	    	   
	    	   Actions actionStopRec = new Actions(driver);
	    	   actionStopRec.moveToElement(stopRec).build().perform();
	    	   //driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	    	   Robot uiBtnStop = new Robot();
	    	   uiBtnStop.mouseMove(190,1670);	    	   
	    	   Thread.sleep(3000);   	   
	    	   actionStopRec.click(stopRec).build().perform();   
	    	   
	    	   Thread.sleep(3000);
	    	   if(driver.findElement(By.xpath("//input[contains(@class, 'title-input form-control input-sm')]")).isDisplayed()){
    	    		 System.out.println("Found the title input holder..");
    	    		 driver.findElement(By.xpath("//input[contains(@class, 'title-input form-control input-sm')]")).sendKeys("Replay-Dynamics UI SEP31");
    	    		Thread.sleep(3000); 
    	       }
	    	   
	    	  // WebElement previewbuttonAppears = driver.findElement(By.cssSelector("button[class='btn btn-xs btn-primary btnPreviewVideo']"));
	    	   System.out.println("renderingProgress in progress..");
	    	   WebElement renderingProgress = driver.findElement(By.xpath("//div[contains(@class, 'progress-bar progress-bar-striped active')]"));
     	    	boolean submitBtnAppears = false;
     	    	while(!submitBtnAppears){
     	    		String progressBar = "";
     	    		boolean rendFinish = false;
     	    		while(!rendFinish){
     	    			
     	    			progressBar = renderingProgress.getText();
     	    			//System.out.println("renderingProgress text is --> "+progressBar);
     	    			//Thread.sleep(1000);      	    			
     	    			if(progressBar.equalsIgnoreCase("97% rendered") || progressBar.equalsIgnoreCase("98% rendered") || progressBar.equalsIgnoreCase("99% rendered")){
     	    				
     	    				System.out.println("renderingProgress is 100% exiting loop");
     	    				Thread.sleep(6000);  
     	    				WebElement previewbuttonAppears = driver.findElement(By.cssSelector("button[class='btn btn-xs btn-primary btnPreviewVideo']"));
     	    				if(previewbuttonAppears.isDisplayed()){
     	    					System.out.println("Preview Button appeared..");
     	    					rendFinish = true;
     	    					submitBtnAppears = true;
     	    					break;
     	    					
     	    					
     	    				}
     	    				
     	    			}
     	    		}
     	    		if(submitBtnAppears){
     	    			System.out.println("passed by here..");
     	    			Thread.sleep(2000); 
     	    			break;
     	    		}
     	    		
     	    	}
     	    	
     	    	if(submitBtnAppears){
     	    		System.out.println("save and submit button enabled..");
      	    		Thread.sleep(1000);
      	    		//Thread.sleep(3000);
      	    		driver.findElement(By.linkText("Save Draft Article")).click();
      	    		
      	    	}
      	    	Thread.sleep(15000);      	    	
      	    	System.out.println("Submitted dialog box appears..");
      	    	Robot submittedDialogBox = new Robot();
      	    	submittedDialogBox.keyPress(KeyEvent.VK_ENTER);
      	    	Thread.sleep(2000);

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
