package zendesk.testauto.scripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://recursivelabsdev.zendesk.com/agent/dashboard";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		switchIFrame();
		enterEmail();
		enterPassword();
		clickLogin();
		chooseTicket("target");
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

}
