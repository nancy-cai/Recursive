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

public class BuyMinutesNegativeCase {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private LoginFunction login;
	private Properties prop;
	private WebElement sideMenu;
	private WebElement buyMin;
	private WebElement minutes;
	private WebElement addToCart;
	private WebElement errorMsg;
	private String expectedErrorMsg;
	private String actualErrorMsg;
	private DataFileLoad file;
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
		for (int i = 2; i < 4; i++) {
			enterMinutesToBuy(i);
			pressEnter();
			hoverAddToCart();
			clickAddToCart();
			assertErrorMessage();
		}
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

	private void enterMinutesToBuy(int index) {
		strfilepath = "./Datapool/RecursiveData.xls";
		String minToBuy = file.HA_GF_readXL(index, "MinutesToBuy", strfilepath);
		minutes = driver.findElement(By.id("customMinutesInput"));
		minutes.clear();
		minutes.sendKeys(minToBuy);
	}

	private void pressEnter() {
		minutes.sendKeys(Keys.ENTER);
	}

	private void hoverAddToCart() {
		Actions builder1 = new Actions(driver);
		addToCart = driver.findElement(
				By.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		builder1.moveToElement(addToCart).build().perform();
	}

	private void clickAddToCart() throws InterruptedException {
		try {
			waits.waitLoop(driver, By
					.xpath("html/body/div[2]/section/div/div/div/div/div/article/div/table/tbody/tr[2]/td[6]/button"));
		} catch (Exception e) {

			e.printStackTrace();
		}

		addToCart.click();
		System.out.println("Added!");
		Thread.sleep(3000);
	}

	private void assertErrorMessage() {
		expectedErrorMsg = "You must add a positive, non-zero number of replay minutes to your cart.";
		errorMsg = driver.findElement(By.xpath(".//*[@id='errorMessage']"));
		actualErrorMsg = errorMsg.getText();
		System.out.println("Expected error message is " + expectedErrorMsg);
		System.out.println("Actual error message is " + actualErrorMsg);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

}
