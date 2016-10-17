package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

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

import functions.LoginFunction;
import functions.WaitLoopFunction;

public class SearchAndEditReplays {
	private WebDriver driver;
	private Properties prop;
	private LoginFunction replay;
	private WaitLoopFunction replay1;
	private WebElement sideMenu;
	private WebElement replayMenu;
	private WebElement view;
	private List<WebElement> copyMsg;
	private String copyMassage;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		replay = new LoginFunction();
		replay1 = new WaitLoopFunction();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {

		replay.Login(driver);
		hoverSideMenu();
		clickReplays();
		searchReplay();
		clickView();
		copyVideoURL();
		assertCopyURLMsg();
		copyHTML();
		assertCopyHTMLMsg();
		editDescription();
		saveChange();
		assertSuccessMsg();
	}

	@After
	public void close() throws Exception {

		driver.quit();
	}

	private void hoverSideMenu() {
		Actions builder = new Actions(driver);
		sideMenu = driver.findElement(By.className("main-nav__link "));
		builder.moveToElement(sideMenu).build().perform();
	}

	private void clickReplays() throws Exception {
		replay1.waitLoop(driver, By.xpath("/html/body/div[2]/aside/ul/li[2]/a"));
		replayMenu = driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[2]/a"));
		replayMenu.click();
	}

	private void searchReplay() throws Exception {
		WebElement search = driver.findElement(By.id("replaySearchText"));
		search.sendKeys("target");
		Thread.sleep(2000);
		WebElement searchBtn = driver.findElement(By.id("searchButton"));
		searchBtn.click();
	}

	private void clickView() throws Exception {
		replay1.waitLoop(driver, By.xpath("//*[@id='replayTable']/tbody/tr[2]/td[6]/a"));
		view = driver.findElement(By.xpath("//*[@id='replayTable']/tbody/tr[2]/td[6]/a"));
		view.click();
	}

	public void copyVideoURL() throws Exception {
		WebElement videoURL = driver.findElement(By.cssSelector("button[title='Copy video URL']"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(videoURL).build().perform();
		Thread.sleep(2000);
		builder1.click(videoURL).build().perform();
	}

	public void assertCopyURLMsg() {
		copyMsg = driver.findElements(By.className("clipboard-status-message"));
		copyMassage = "Copied!";
		String copyURLMsg = copyMsg.get(0).getText();
		assertEquals(copyMassage, copyURLMsg);

	}

	public void copyHTML() throws Exception {
		WebElement videoHTML = driver.findElement(By.cssSelector("button[title='Copy Embedly HTML']"));
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(videoHTML).build().perform();
		Thread.sleep(2000);
		builder2.click(videoHTML).build().perform();
	}

	public void assertCopyHTMLMsg() {

		String copyHTMLMsg = copyMsg.get(1).getText();
		assertEquals(copyMassage, copyHTMLMsg);

	}

	public void editDescription() {
		WebElement title = driver.findElement(By.id("titleInput"));
		title.clear();
		title.sendKeys("target");
		WebElement description = driver.findElement(By.id("descriptionInput"));
		description.clear();
		description.sendKeys("target example");

	}

	public void saveChange() {
		WebElement saveBtn = driver.findElement(By.cssSelector("button[type='submit']"));
		saveBtn.click();
	}

	public void assertSuccessMsg() {
		String expectedMsg = "Saved title and description";
		String alertText = driver.findElement(By.className("alert__text")).getText();
		assertEquals(expectedMsg, alertText);
	}

}
