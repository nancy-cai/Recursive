package realtime.testauto.scripts;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.WaitLoopFunction;

public class RealtimeJoinEndToEnd {

	private WebDriver driver;
	private WebDriver driver2;
	private WaitLoopFunction waits;
	private WebDriverWait wait;
	private WebElement joincode;
	private String code;
	private WebElement joinBtn;
	private String name;
	private ArrayList<String> tabs;
	private ArrayList<String> tabs2;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		/*
		 * System.setProperty("webdriver.firefox.marionette",
		 * "C:/Selenium/geckodriver.exe"); driver = new FirefoxDriver();
		 */

	}

	@Test
	public void test() throws Exception {

		openTestPage();
		tabs = new ArrayList<String>(driver.getWindowHandles());
		chooseRealtimeUI();
		clickRcrsvButton();
		getCode();
		openJoinPage();
		tabs2 = new ArrayList<String>(driver2.getWindowHandles());
		joinSession();
		switchDriverTab();
		assertJoinMsg();
		switchDriverTab();
		clickRectangle();
		switchDriverTab();
		draw();
		switchDriver2Tab();
		joinDraw();

		clickAddGuest();
		inviteNewGuest();
		switchDriverTab();
		acceptNewGuestNotice();

		switchDriver2Tab();
		uploadDoc();
		switchDriverTab();

		endSession();
		driver.close();
		switchDriver2Tab();
		joinNewSession();
		driver2.close();
	}

	public void openTestPage() {
		driver.get("http://testpages.clickwith.me/testpage.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public void chooseRealtimeUI() {
		new Select(driver.findElement(By.id("ui"))).selectByVisibleText("realtime-join-code");
		driver.findElement(By.id("save")).click();
	}

	public void clickRcrsvButton() {

		driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));
		WebElement temp = driver.findElement(By.xpath("//*[@id='app']/div/i"));
		temp.click();
	}

	public void getCode() throws Exception {
		joincode = driver.findElement(By.className("join-code"));
		code = joincode.getAttribute("value");
		Thread.sleep(3000);
	}

	public void openJoinPage() {
		driver2 = new ChromeDriver();
		driver2.get("https://join-staging.rcrsv.io/");
	}

	public void joinSession() {
		driver2.findElement(By.id("joincode")).sendKeys(code);
		name = "nancy";
		driver2.findElement(By.id("name")).sendKeys(name);

		joinBtn = driver2.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/form/button"));
		joinBtn.sendKeys(Keys.ENTER);
	}

	public void assertJoinMsg() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[3]")));
		Thread.sleep(5000);
		String expectedJoinMsg = name + " has joined the session.";
		String actualJoinMsg = driver.findElement(By.className("content")).getText();
		System.out.println(actualJoinMsg);
		assertEquals(expectedJoinMsg, actualJoinMsg);
	}

	public void switchDriverTab() {

		driver.switchTo().window(tabs.get(0));

	}

	public void clickRectangle() {
		driver.findElement(By.linkText("Typography")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[4]")));
		WebElement annotFrame = driver.findElement(By.cssSelector("#rcrsv-annotation-toolbar"));
		// #rcrsv-annotation-toolbar
		if (annotFrame.isDisplayed()) {
			System.out.println("annotation frame FOUND..");
			driver.findElement(By.id("atb-button-rectangle")).click();
		}
	}

	public void draw() {
		WebElement element = driver.findElement(By.id("typography"));
		Actions recPen = new Actions(driver);
		Action drawRecAction = recPen.moveToElement(element).clickAndHold().moveByOffset(744, 370)
				.moveByOffset(295, 514).release().build();
		drawRecAction.perform();
	}

	public void switchDriver2Tab() {
		driver2.switchTo().window(tabs2.get(0));
	}

	public void joinDraw() throws Exception {
		driver2.findElement(By.xpath("//*[@id='bottom-nav']/div/div[4]/div[1]/button[2]")).click();
		WebElement ele = driver2.findElement(By.id("rcrsv-annotation-container"));
		Actions pen = new Actions(driver2);
		pen.moveToElement(ele).build().perform();

		Action drawAction = pen.moveToElement(ele, 900, 400).clickAndHold().moveToElement(ele, 700, 300).release()
				.build();
		drawAction.perform();
		Thread.sleep(8000);
	}

	public void joinDeleteDraw() throws Exception {
		driver2.findElement(By.xpath("//*[@id='bottom-nav']/div/div[4]/div[1]/button[7]")).click();
		Thread.sleep(2000);
	}

	public void clickAddGuest() throws Exception {

		driver2.findElement(
				By.cssSelector("#bottom-nav > div > div.hidden-xs > div.navbar-right > div:nth-child(2) > button"))
				.click();

		driver2.findElement(By.xpath("//*[@id='bottom-nav']/div/div[4]/div[2]/div[2]/ul/li[5]/a")).click();
		Thread.sleep(4000);
	}

	public void inviteNewGuest() throws Exception {
		driver2.findElement(By.xpath("//div[@class='modal-content']//input[@id='guestInviteEmailName']"))
				.sendKeys("yaya");
		driver2.findElement(By.id("guestInviteEmail")).sendKeys("yaya@adactin.com");
		driver2.findElement(By.id("guestInviteMessage")).sendKeys("Please join us");
		driver2.findElement(By.xpath("//*[@id='app']/div[3]/div/div/div[3]/button[1]")).click();
		Thread.sleep(2000);
	}

	public void acceptNewGuestNotice() {
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[3]")));
		driver.findElement(By.xpath("//*[@id='rcrsv-notifications']/div/div[3]/div/i[2]")).click();
	}

	public void uploadDoc() throws Exception {
		driver2.findElement(By.xpath("//*[@id='bottom-nav']/div/div[4]/div[2]/div[1]/button")).click();
		driver2.findElement(By.id("inputFile")).sendKeys("C:/example.docx");
		Thread.sleep(2000);

		driver2.findElement(By.xpath("//*[@id='bottom-nav']/div/div[4]/div[2]/div[1]/ul/li[2]")).click();
		Thread.sleep(5000);
	}

	public void denyDocUploadRequest() throws Exception {
		wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(
				"iframe[style='position: fixed; bottom: 60px; right: 15px; width: 280px; min-width: 280px; overflow: hidden; height: 0px; border: none; visibility: visible;']")));
		Thread.sleep(5000);
		waits.waitLoop(driver, By.xpath("//*[@id='rcrsv-notifications']/div/div[3]/div/i[1]"));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id='rcrsv-notifications']/div/div[3]/div/i[1]"))).click();

	}

	public void endSession() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[2]")));
		driver.findElement(By.linkText("End Session")).click();
		Thread.sleep(2000);
	}

	public void joinNewSession() throws Exception {

		driver2.findElement(By.cssSelector("#app > div > div > div > div.well.clearfix > p:nth-child(3) > a")).click();
	}

}
