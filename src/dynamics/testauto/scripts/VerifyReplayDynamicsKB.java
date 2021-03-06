package dynamics.testauto.scripts;

import static org.junit.Assert.fail;

import java.awt.Robot;
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

public class VerifyReplayDynamicsKB {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
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
		driver.findElement(By.id("cred_password_inputtext")).sendKeys("Adactin1234");

		WebElement loginBtn = driver
				.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td/div[2]/div[1]/ul/li[4]/button[1]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// WebElement previewBtn = driver.findElement(By.linkText("Preview
		// Video"));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[4]/table/tbody/tr/td/div[2]/div[1]/ul/li[4]/button[1]")));
		System.out.println("after the wait here..");
		if (loginBtn.isDisplayed()) {
			System.out.println("Login button found.. executing..");
			Actions action = new Actions(driver);
			action.moveToElement(loginBtn).build().perform();
			action.click(loginBtn).build().perform();

		}

		WebElement homeTab = driver.findElement(By.cssSelector("#HomeTabLink > span.navTabButtonImageContainer"));
		// checking if the landing page has been loaded after logging in
		if (homeTab.isDisplayed()) {

			System.out.println("Changing to Services Tab..");
			Actions action = new Actions(driver);
			action.moveToElement(homeTab).build().perform();
			action.click(homeTab).build().perform();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#CS > span.navActionButtonIconContainer > span.navActionButtonIcon"))
					.click();

			driver.findElement(By.cssSelector("span.nav-rowLabel")).click();
			Thread.sleep(1000);

		}
		WebElement temp = driver.findElement(By.xpath("//div[contains(@id, 'crmContentPanel')]"));
		if (temp.isDisplayed()) {
			System.out.println("This is the page source --> " + temp.getAttribute("innerHTML"));
		}
		// driver.switchTo().frame(driver.findElement(By.id("contentIFrame1")));
		List<WebElement> myIframes = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < myIframes.size(); i++) {
			String frameName = (String) myIframes.get(i).getAttribute("name");
			System.out.println("These are the frames currently [" + i + "] " + frameName);

		}
		driver.switchTo().frame(driver.findElement(By.id("contentIFrame0")));
		List<WebElement> myIframes2 = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < myIframes2.size(); i++) {
			String frameName = (String) myIframes2.get(i).getAttribute("name");
			System.out.println("These are the frames 2 currently [" + i + "] " + frameName);

		}
		driver.switchTo().frame(driver.findElement(By.id("dashboardFrame")));
		List<WebElement> myIframes3 = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < myIframes3.size(); i++) {
			String frameName = (String) myIframes3.get(i).getAttribute("name");
			System.out.println("These are the frames 2 currently [" + i + "] " + frameName);

		}

		driver.switchTo().frame(driver.findElement(By.id("WebResource_PersonalWall")));

		if (driver.findElement(By.linkText("Replay-Dynamics UI SEP22")).isDisplayed()) {
			System.out.println("Found the replay instance..");
			Thread.sleep(3000);
			Robot uiBtn = new Robot();
			uiBtn.mouseMove(2180, 808);
			Thread.sleep(5000);

			WebElement replayCase = driver.findElement(By.linkText("Replay-Dynamics UI SEP22"));
			// WebElement replayCase =
			// driver.findElement(By.xpath("html/body/div[1]/div[1]/div[8]/div[2]/div[2]/div[1]"));
			/// html/body/div[1]/div[1]/div[8]/div[2]/div[2]/div[1]
			Actions action = new Actions(driver);
			action.doubleClick(replayCase).build().perform();
			Thread.sleep(12000);
		}

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
