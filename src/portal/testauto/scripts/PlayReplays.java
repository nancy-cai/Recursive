
package portal.testauto.scripts;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import functions.LoginFunction;
import functions.WaitLoopFunction;

public class PlayReplays {

	private WebDriver driver;
	private LoginFunction replay;
	private Properties prop;
	private WaitLoopFunction replay1;
	private WebElement sideMenu;
	private WebElement replayMenu;
	private WebElement view;
	private WebElement play;
	private WebElement stop;

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
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {

		replay.Login(driver);
		hoverSideMenu();
		clickReplays();
		clickView();
		clickPlayButton();
		clickStopButton();

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

	private void clickView() throws Exception {
		replay1.waitLoop(driver, By.xpath("//*[@id='replayTable']/tbody/tr[2]/td[6]/a"));
		view = driver.findElement(By.xpath("//*[@id='replayTable']/tbody/tr[2]/td[6]/a"));
		view.click();
	}

	private void clickPlayButton() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='replay-video-container']/iframe")));
		replay1.waitLoop(driver, By.cssSelector(".vjs-big-play-button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById(\"rcrsv-replay-video_html5_api\").play()");
		/*
		 * or play = driver.findElement(By.cssSelector(".vjs-big-play-button"));
		 * play.click();
		 */
	}

	private void clickStopButton() throws Exception {
		Thread.sleep(3000);
		replay1.waitLoop(driver, By.xpath("//*[@id='rcrsv-replay-video']/div[6]/div[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById(\"rcrsv-replay-video_html5_api\").pause()");
		Thread.sleep(3000);
		/*
		 * stop = driver.findElement(By.xpath(
		 * "//*[@id='rcrsv-replay-video']/div[6]/div[1]")); stop.click();
		 */

	}

}
