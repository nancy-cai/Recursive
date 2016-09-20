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

import portal.testauto.scripts.WaitLoopFunction;

public class VerifyZendeskTicket {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private String baseUrl;
	private WebElement email;
	private WebElement password;
	private WebElement login;
	private List<WebElement> summary;
	private WebElement replayLink;
	private WebElement video;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://recursivelabsdev.zendesk.com/agent/dashboard";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		waits = new WaitLoopFunction();
		switchIFrame();
		enterEmail();
		enterPassword();
		clickLogin();
		chooseTicket("Zendesk Ticket UI Sep22");
		clickReplayLink();
		waits.waitLoop(driver, By.id("rcrsv-replay-video_html5_api"));
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

	private void chooseTicket(String summaryName) {
		summary = driver.findElements(By.cssSelector(".subject > a"));
		for (WebElement smr : summary) {
			if (smr.getText().equalsIgnoreCase(summaryName)) {
				smr.click();
				break;
			}
		}
	}

	private void clickReplayLink() throws Exception {
		replayLink = driver.findElement(By.xpath("//div[@class='zd-comment']/p/a"));
		driver.get(replayLink.getAttribute("href"));

	}

	private void clickReplayButton() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById(\"rcrsv-replay-video_html5_api\").play()");
		Thread.sleep(10000);

	}

}
