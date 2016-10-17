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

public class AddSiteRules {
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
		addRules();
		deleteStyle();
		confirmDelete();

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

	public void addRules() {

		WebElement addRulesBtn = driver.findElement(By.id("addRedactionRow"));
		js.executeScript("arguments[0].click();", addRulesBtn);
	}

	public void clickAppliedStyleDropdown() throws Exception {
		WebElement appliedStyle = driver.findElement(By.id("redactionTypeVisual0"));
		Actions action = new Actions(driver);
		action.moveToElement(appliedStyle).build().perform();
		Thread.sleep(2000);
		action.click(appliedStyle).build().perform();
		Thread.sleep(2000);
	}

	public void selectStyle() {
		WebElement hide = driver.findElement(By.linkText("Hide"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(hide).build().perform();
		action1.click(hide).build().perform();
	}

	public void deleteStyle() throws Exception {
		WebElement delete = driver.findElement(By.xpath("//*[@id='row0']/td[3]/a"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(delete).build().perform();
		Thread.sleep(2000);
		action2.click(delete).build().perform();
		Thread.sleep(2000);
	}

	public void confirmDelete() {
		WebElement confirm = driver.findElement(By.id("selectiveRedactionRemovalButton"));
		confirm.click();
	}

}
