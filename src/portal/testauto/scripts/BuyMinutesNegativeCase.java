package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

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

public class BuyMinutesNegativeCase {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private String baseUrl;
	private WebElement email;
	private WebElement password;
	private WebElement login;
	private WebElement sideMenu;
	private WebElement buyMin;
	private WebElement minutes;
	private WebElement addToCart;
	private WebElement errorMsg;
	private String expectedErrorMsg;
	private String actualErrorMsg;
	public WebElement checkoutButton;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://portal-staging.rcrsv.io/login";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void buyMinutes() throws Exception {
		waits = new WaitLoopFunction();
		enterEmail();
		enterPassword();
		clickLogin();
		hoverSideMenu();
		clickBuyMoreMinute();
		enterMinutesToBuy(0);
		pressEnter();
		hoverAddToCart();
		clickAddToCart();
		assertErrorMessage();
	}

	@After
	public void close() throws Exception {

		driver.quit();
	}

	private void enterEmail() {
		email = driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys("chintan.patel@adactin.com");
	}

	private void enterPassword() {
		password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Adactin123");
	}

	private void clickLogin() {

		login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();

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

	private void enterMinutesToBuy(Integer min) {
		minutes = driver.findElement(By.id("customMinutesInput"));
		minutes.clear();
		minutes.sendKeys(Integer.toString(min));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addToCart.click();
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
