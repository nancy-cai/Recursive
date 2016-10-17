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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import functions.LoginFunction;

public class ViewAndEditSiteRules {
	public WebDriver driver;
	public LoginFunction login;
	public Actions action;
	public Properties prop;
	JavascriptExecutor js;

	@Before
	public void startUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginFunction();
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		login.Login(driver);
		clickSiteRules();
		searchBySite();
		clickChangeRules();
		clickPPTForUpload();
		chooseJpegForLinkClick();
		clickUpdateRules();
		assertSuccessMsg();

	}

	@After
	public void close() {
		driver.close();
	}

	public void clickSiteRules() throws Exception {
		WebElement siteRules = driver.findElement(By.xpath("//ul[@class='main-nav']/li[4]"));
		action.moveToElement(siteRules).build().perform();
		Thread.sleep(2000);
		action.click(driver.findElement(By.xpath("/html/body/div[2]/aside/ul/li[4]/a"))).build().perform();

	}

	public void searchBySite() throws Exception {
		WebElement searchInput = driver.findElement(By.id("domainSearchText"));
		searchInput.sendKeys("add.com");
		Thread.sleep(2000);
		WebElement searchBtn = driver.findElement(By.id("searchButton"));
		searchBtn.click();

	}

	public void clickChangeRules() {

		WebElement changeRuleBtn = driver.findElement(By.linkText("Change Rules"));
		js.executeScript("arguments[0].click();", changeRuleBtn);
	}

	public void clickPPTForUpload() throws Exception {

		List<WebElement> docUpload = driver.findElements(By.xpath("//*[@id='uploadCheckboxDiv']/div/label"));
		for (WebElement doc : docUpload) {
			System.out.println(doc.getText());
			if (doc.getText().equalsIgnoreCase(".ppt")) {
				Thread.sleep(2000);
				doc.findElement(By.tagName("input")).click();
				break;
			}
		}
	}

	public void chooseJpegForLinkClick() throws Exception {
		List<WebElement> docClick = driver.findElements(By.xpath("//*[@id='linkClickCheckboxDiv']/div/label"));
		for (WebElement docType : docClick) {
			System.out.println(docType.getText());
			if (docType.getText().equalsIgnoreCase(".jpeg")) {
				Thread.sleep(2000);
				docType.findElement(By.tagName("input")).click();
				break;
			}
		}
	}

	public void clickUpdateRules() throws Exception {
		Thread.sleep(2000);
		WebElement updateBtn = driver.findElement(By.xpath("//*[@id='updateSiteRules']/article/div/div[8]/div/button"));
		updateBtn.click();
	}

	public void assertSuccessMsg() {
		WebElement successMsg = driver.findElement(By.xpath("//*[@id='updateSiteRules']/div/p"));
		assertEquals("Updated site rules", successMsg.getText());
	}

}
