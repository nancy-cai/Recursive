package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class VerifyContentOnHomePage {

	public WebDriver driver;
	public LoginFunction login;
	public Properties prop;
	List<WebElement> side_links;
	List<String> sideLinks;
	List<String> expectedSideLinks;
	List<WebElement> sub_links;
	List<String> subLinks;
	List<String> expectedSubLinks;

	@Before
	public void startUp() throws Exception, IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginFunction();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		login.Login(driver);
		getSideMenuLinks();
		assertNavigationLinks();
		getSubMenuLinks();
		assertSubLinks();
		assertLinks();
	}

	@After
	public void closeDriver() {
		driver.close();
	}

	public List<String> getSideMenuLinks() {
		side_links = driver.findElements(By.xpath("//ul[@class='main-nav']/li/a"));
		sideLinks = new ArrayList<>();

		for (WebElement link : side_links) {
			sideLinks.add(link.getText());
		}
		return sideLinks;
	}

	public void assertNavigationLinks() {
		expectedSideLinks = Arrays.asList("Dashboard", "Replays", "Site Keys", "Site Rules", "Buy More Minutes",
				"Manage Organization");
		assertEquals(sideLinks, expectedSideLinks);
		System.out.println(sideLinks);
	}

	public List<String> getSubMenuLinks() throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//ul[@class='main-nav']/li[6]"))).build().perform();
		Thread.sleep(5000);
		sub_links = driver.findElements(By.xpath("/html/body/div[2]/aside/ul/li[6]/ul/li/a/span"));
		subLinks = new ArrayList<>();

		for (WebElement slink : sub_links) {
			subLinks.add(slink.getText());
		}
		System.out.println(subLinks);
		return subLinks;
	}

	public void assertSubLinks() {
		expectedSubLinks = Arrays.asList("INTEGRATIONS", "PERMISSIONS", "TRANSACTION HISTORY", "USERS");
		assertEquals(subLinks, expectedSubLinks);

	}

	public void assertLinks() {
		WebElement buyMinLink = driver.findElement(By.className("pe-7s-cart"));
		assertTrue(buyMinLink.isDisplayed());
		WebElement siteKeysLink = driver.findElement(By.className("rcrsv-icon-fa-code"));
		assertTrue(siteKeysLink.isDisplayed());
	}

}
