package portal.testauto.scripts;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.BuyMinutesFunction;
import functions.WaitLoopFunction;

public class CheckoutWithExistingValidCard {
	public WebDriver driver;
	public BuyMinutesFunction cart1;
	public WaitLoopFunction cart;
	public Properties prop;
	public WebElement paymentMethods;
	public WebDriverWait wait;
	public String paymentList;
	public Actions action;
	public List<WebElement> paymentLists;
	public WebElement checkout;
	JavascriptExecutor js;
	public WebElement checkTransactionButton;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		cart = new WaitLoopFunction();
		cart1 = new BuyMinutesFunction();
		js = (JavascriptExecutor) driver;
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	public void buyMinutesCheckout() throws Exception {

		cart1.BuyMinutesFunction(driver, 1);
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

	public void clickPaymentMethods() throws Exception {
		cart.waitLoop(driver, By.id("payment-method-select-btn"));
		paymentMethods = driver.findElement(By.id("payment-method-select-btn"));
		paymentMethods.click();

	}

	public void chooseValidExistingCard() throws Exception {
		cart.waitLoop(driver, By.xpath("//*[@id='payment-select-dropdown-menu']/li/a"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		action.sendKeys(Keys.ENTER).perform();

		/*
		 * Due to the bug of the CSS and html code on the website, the following
		 * code does not work on every browser so we change to keys actions
		 * instead.
		 *
		 * paymentList = "payment-select-dropdown-menu"; paymentLists =
		 * driver.findElements(By.xpath(
		 * "//*[@id='payment-select-dropdown-menu']/li/a")); for (WebElement
		 * payment : paymentLists) { if
		 * (payment.getAttribute("data-card").equalsIgnoreCase(
		 * "use-existing-card")) { payment.click(); break; }
		 * 
		 * }
		 */

	}

	public void clickCheckout() throws Exception {
		cart.waitLoop(driver, By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		checkout = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>button"));
		checkout.click();
	}

	public void dismissAlert() throws InterruptedException {
		Thread.sleep(10000);
		try {

			driver.findElement(By.id("noThanksButton")).click();
		} catch (Exception e) {

		}
		Thread.sleep(5000);
	}

	public void checkTransationHistory() throws Exception {
		cart.waitLoop(driver, By.xpath("/html/body/div[2]/section/div[2]/div/div/div/div/article/div/a[1]"));
		checkTransactionButton = driver
				.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div/div/article/div/a[1]"));
		js.executeScript("arguments[0].click();", checkTransactionButton);

	}

}
