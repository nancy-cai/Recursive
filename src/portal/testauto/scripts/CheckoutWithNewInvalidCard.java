package portal.testauto.scripts;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.BuyMinutesFunction;
import functions.WaitLoopFunction;

public class CheckoutWithNewInvalidCard {

	public WebDriver driver;
	public BuyMinutesFunction cart1;
	public WebElement paymentMethods;
	public WebDriverWait wait;
	public WaitLoopFunction waits;
	public Properties prop;
	public String paymentList;
	public List<WebElement> paymentLists;
	public WebElement checkout;
	public WebElement pay;
	public WebElement checkTransactionButton;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		cart1 = new BuyMinutesFunction();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

	@Test
	public void buyMinutesCheckout() throws Exception {

		cart1.BuyMinutesFunction(driver, 1);
		clickPaymentMethods();
		chooseNewCard();
		clickCheckout();
		Thread.sleep(3000);
		dismissAlert();
		fillInCardDetails();
		clickPayMoney();

	}

	@After
	public void close() throws Exception {

		driver.quit();
	}

	public void clickPaymentMethods() throws Exception {
		waits.waitLoop(driver, By.id("payment-method-select-btn"));
		paymentMethods = driver.findElement(By.id("payment-method-select-btn"));
		paymentMethods.click();

	}

	public void chooseNewCard() throws Exception {
		waits.waitLoop(driver, By.xpath("//*[@id='payment-select-dropdown-menu']/li/a"));
		WebElement newCard = driver.findElement(By.linkText("Checkout with new card (replace existing)"));
		Actions action = new Actions(driver);
		action.moveToElement(newCard).build().perform();
		Thread.sleep(2000);
		action.click(newCard).build().perform();

		/*
		 * Actions action = new Actions(driver);
		 * action.sendKeys(Keys.ARROW_DOWN).perform();
		 * action.sendKeys(Keys.ARROW_DOWN).perform();
		 * action.sendKeys(Keys.ENTER).perform();
		 */

	}

	public void clickCheckout() throws Exception {
		waits.waitLoop(driver, By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		checkout = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		checkout.click();

	}

	public void dismissAlert() {

		if (driver.findElement(By.id("autoRefillModalDialog")).isDisplayed()) {

			driver.findElement(By.id("noThanksButton")).click();
		}

	}

	public void fillInCardDetails() throws Exception {
		waits.waitLoop(driver, By.name("stripe_checkout_app"));
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

	public void clickPayMoney() {
		pay = driver.findElement(By.id("submitButton"));
		pay.click();
	}

}
