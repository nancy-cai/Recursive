package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import functions.DataFileLoad;
import functions.LoginFunction;
import functions.WaitLoopFunction;

public class BuyMinutesHappyCase {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private LoginFunction login;
	private Properties prop;
	private WebElement sideMenu;
	private WebElement buyMin;
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
	private DataFileLoad file;
	private String minToBuy;
	private String strfilepath;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginFunction();
		waits = new WaitLoopFunction();
		file = new DataFileLoad();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void buyMinutes() throws Exception {
		login.Login(driver);
		hoverSideMenu();
		clickBuyMoreMinute();
		min = 1;
		enterMinutesToBuy();
		pressEnter();
		getActualTotalPrice();
		getExpectedTotalPrice();
		checkTotalPrice();
		hoverAddToCart();
		clickAddToCart();
		assertSuccessMessage();
	}

	@After
	public void close() throws Exception {

		driver.quit();
	}

	private void hoverSideMenu() throws Exception {
		waits.waitLoop(driver, By.className("main-nav__link "));
		Actions builder = new Actions(driver);
		sideMenu = driver.findElement(By.className("main-nav__link "));
		builder.moveToElement(sideMenu).build().perform();
	}

	private void clickBuyMoreMinute() throws Exception {
		waits.waitLoop(driver, By.xpath("/html/body/div[2]/aside/ul/li[5]/a"));
		buyMin = driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[5]/a"));
		buyMin.click();
	}

	private void enterMinutesToBuy() {
		strfilepath = "./Datapool/RecursiveData.xls";
		minToBuy = file.HA_GF_readXL(1, "MinutesToBuy", strfilepath);
		min = Integer.parseInt(minToBuy);
		minutes = driver.findElement(By.id("customMinutesInput"));
		minutes.clear();
		minutes.sendKeys(minToBuy);
	}

	private void pressEnter() {
		minutes.sendKeys(Keys.ENTER);
	}

	private String getActualTotalPrice() {
		totalPrice = driver.findElement(By.id("customMinutesTotalCost"));
		actualTotalPrice = totalPrice.getText();
		System.out.println("Actual Price is " + actualTotalPrice);
		return actualTotalPrice;

	}

	private String getExpectedTotalPrice() {
		expectedTotalPrice = "$" + Double.toString(min * 1.25);
		if (expectedTotalPrice.length() == 4) {
			expectedTotalPrice = expectedTotalPrice + "0";

		}
		System.out.println("Expected price is " + expectedTotalPrice);
		return expectedTotalPrice;
	}

	private void checkTotalPrice() {

		assertEquals(expectedTotalPrice, actualTotalPrice);
	}

	private void hoverAddToCart() {
		Actions builder1 = new Actions(driver);
		addToCart = driver.findElement(
				By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		builder1.moveToElement(addToCart).build().perform();
	}

	private void clickAddToCart() throws Exception {
		waits.waitLoop(driver,
				By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		addToCart.click();
		Thread.sleep(3000);
	}

	private void assertSuccessMessage() {
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
	}

	public void clickCheckout() {
		checkoutButton = driver.findElement(By.cssSelector(".widget__content.filled.table-responsive.pad20>a>button"));
		checkoutButton.click();
	}

}
