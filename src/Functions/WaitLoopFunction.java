package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WaitLoopFunction {


	
	public void waitLoop(WebDriver driver, By by) throws Exception{
		for(int second = 0; second<60;second++){

				Thread.sleep(1000);
				if(driver.findElement(by).isDisplayed()){
					
					break;
				}
		}	
	}
}
