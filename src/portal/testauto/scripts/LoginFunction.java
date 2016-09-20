package portal.testauto.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFunction {

	public String baseUrl;
	public WebElement email;
	public WebElement password;
	public WebElement login;

	public void Login(WebDriver driver) throws Exception {
		email = driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys("chintan.patel@adactin.com");

		password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Adactin123");
		Thread.sleep(2000);
		login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();

	}

}
