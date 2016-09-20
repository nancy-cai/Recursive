package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyMinutesFunction {
	public WebDriver driver;
	public String baseUrl;
	public WebElement email;
	public WebElement password;
	public WebElement login;
	public WebElement buyMin;
	public WebDriverWait wait;
	public WebElement minutes;
	public Integer min;
	public WebElement totalPrice;
	public String actualTotalPrice;
	public String expectedTotalPrice;
	public WebElement addToCart;
	public WebElement successMsg;
	public String expectedSuccessMsg;
	public String actualSuccessMsg;
	public WebElement checkoutButton;

	public void BuyMinutesFunction(WebDriver driver, Integer min) throws Exception {
		email = driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys("chintan.patel@adactin.com");

		password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Adactin123");

		login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();

		Thread.sleep(3000);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.className("main-nav__link "))).build().perform();

		wait = new WebDriverWait(driver, 60);
		buyMin = driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[5]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(buyMin));
		buyMin.click();
		Thread.sleep(3000);

		minutes = driver.findElement(By.id("customMinutesInput"));
		minutes.clear();
		minutes.sendKeys(Integer.toString(min));

		minutes.sendKeys(Keys.ENTER);

		totalPrice = driver.findElement(By.id("customMinutesTotalCost"));
		actualTotalPrice = totalPrice.getText();
		System.out.println("Actual Price is " + actualTotalPrice);

		expectedTotalPrice = "$" + Double.toString(min * 1.25);
		if (expectedTotalPrice.length() == 4) {
			expectedTotalPrice = expectedTotalPrice + "0";

		}
		System.out.println("Expected price is " + expectedTotalPrice);

		assertEquals(expectedTotalPrice, actualTotalPrice);

		Actions builder1 = new Actions(driver);
		addToCart = driver.findElement(
				By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		builder1.moveToElement(addToCart).build().perform();

		addToCart.click();
		Thread.sleep(3000);

		if (min == 1) {
			expectedSuccessMsg = "Added " + min.toString() + " minute to your cart.";
		} else {
			expectedSuccessMsg = "Added " + min.toString() + " minutes to your cart.";
		}

		successMsg = driver.findElement(By.xpath(".//*[@id='successMessage']"));
		actualSuccessMsg = successMsg.getText();
		System.out.println("Expected success message is " + expectedSuccessMsg);
		System.out.println("Actual success message is " + actualSuccessMsg);
		assertEquals(expectedSuccessMsg, actualSuccessMsg);

		checkoutButton = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>a>button"));
		checkoutButton.click();
		Thread.sleep(3000);
	}

}
