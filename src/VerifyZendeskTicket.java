package zendesk.testauto.scripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import functions.TimePropertiesSetup;
import functions.WaitLoopFunction;

public class VerifyZendeskTicket {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private String baseUrl;
	private WebElement email;
	private WebElement password;
	private WebElement login;
	private WebElement group;
	private List<WebElement> summary;
	private WebElement replayLink;
	public TimePropertiesSetup title;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		title = new TimePropertiesSetup();
		baseUrl = "https://recursivelabsdev.zendesk.com/agent/dashboard";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(460, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {

		switchIFrame();
		enterEmail();
		enterPassword();
		clickLogin();
		clickGroup();
		chooseTicket();
		clickReplayLink();
		clickReplayButton();

	}

	@After
	public void close() {
		driver.close();
	}

	private void switchIFrame() {
		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[3]/iframe")));
	}

	private void enterEmail() {
		email = driver.findElement(By.id("user_email"));
		email.clear();
		email.sendKeys("chintan.patel@adactin.com");
	}

	private void enterPassword() {
		password = driver.findElement(By.id("user_password"));
		password.clear();
		password.sendKeys("Adactin123");
	}

	private void clickLogin() {
		login = driver.findElement(By.xpath("//input[@type='submit']"));
		login.click();
	}

	public void clickGroup() throws Exception {
		waits.waitLoop(driver, By.xpath("//div[@class='stats-group']/ul/li[2]"));
		group = driver.findElement(By.xpath("//div[@class='stats-group']/ul/li[2]"));
		group.click();
	}

	private void chooseTicket() throws Exception {
		waits.waitLoop(driver, By.cssSelector(".subject > a"));
		title.readFile();
		summary = driver.findElements(By.cssSelector(".subject > a"));
		for (WebElement smr : summary) {

			if (smr.getText().equalsIgnoreCase(title.time())) {
				smr.click();
				break;
			}

		}
	}

	private void clickReplayLink() throws Exception {
		waits.waitLoop(driver, By.xpath("//div[@class='zd-comment']/p/a"));
		replayLink = driver.findElement(By.xpath("//div[@class='zd-comment']/p/a"));
		driver.get(replayLink.getAttribute("href"));

	}

	private void clickReplayButton() throws Exception {
		waits.waitLoop(driver, By.id("rcrsv-replay-video_html5_api"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById(\"rcrsv-replay-video_html5_api\").play()");
		Thread.sleep(10000);

	}

}
