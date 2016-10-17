package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
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

import functions.LoginFunction;
import functions.WaitLoopFunction;

public class ChangeReplayPermissions {
	public WebDriver driver;
	public LoginFunction login;
	public WaitLoopFunction waits;
	public Actions action;
	public Properties prop;

	@Before
	public void startUp() throws Exception, IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginFunction();
		waits = new WaitLoopFunction();
		action = new Actions(driver);
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		login.Login(driver);
		hoverSideMenu();
		clickPermission();
		checkChangeVisibility();
		changeDeleteSetting();
		clickUpdate();
		assertSuccessMsg();
	}

	@After
	public void close() {
		driver.close();
	}

	public void hoverSideMenu() throws Exception {
		waits.waitLoop(driver, By.className(prop.getProperty("List_Manage_Organisation")));
		WebElement manageOrg = driver.findElement(By.className(prop.getProperty("List_Manage_Organisation")));
		action.moveToElement(manageOrg).build().perform();
		action.click(manageOrg).build().perform();
	}

	public void clickPermission() throws Exception {

		waits.waitLoop(driver, By.className("main-nav__submenu"));
		WebElement subMenuPer = driver.findElement(By.linkText("PERMISSIONS"));
		action.moveToElement(subMenuPer).click().build().perform();

	}

	public void checkChangeVisibility() throws Exception {
		By canView = By.cssSelector("label[for='usersCanViewOthersReplays']");
		waits.waitLoop(driver, canView);
		driver.findElement(canView).click();
		Thread.sleep(2000);
		WebElement visible = driver.findElement(By.id("onlyAvailableWithView"));
		if (!visible.isDisplayed()) {
			driver.findElement(canView).click();
		}
	}

	public void changeDeleteSetting() throws Exception {
		By delete = By.cssSelector("label[for='usersCanDeleteOthersReplays']");
		waits.waitLoop(driver, delete);
		driver.findElement(delete).click();
		Thread.sleep(2000);
	}

	public void clickUpdate() {
		WebElement update = driver.findElement(By.cssSelector("button[type='submit']"));
		update.click();
	}

	public void assertSuccessMsg() throws Exception {
		By msg = By.className("alert__text");
		waits.waitLoop(driver, msg);
		String expectedMsg = "Updated replay permissions";
		String actualMsg = driver.findElement(msg).getText();
		assertEquals(expectedMsg, actualMsg);
	}

}
