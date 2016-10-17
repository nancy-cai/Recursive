package zendesk.testauto.scripts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Robot;
import java.io.FileInputStream;
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

public class ValidateZendeskIntegrationTC {
	private WebDriver driver;
	private WaitLoopFunction waits;
	private LoginFunction login;
	private Properties prop;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		login = new LoginFunction();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testValidateZendeskIntegrationTC() throws Exception {
		login.Login(driver);

		waits.waitLoop(driver, By.className(prop.getProperty("List_Manage_Organisation")));
		System.out.println("Manage Integrations Tab Location --> "
				+ driver.findElement(By.className(prop.getProperty("List_Manage_Organisation"))).getLocation());

		Robot uiBtn = new Robot();
		uiBtn.mouseMove(0, 386);
		Thread.sleep(2000);
		Actions builder = new Actions(driver);

		builder.moveToElement(driver.findElement(By.className(prop.getProperty("List_Manage_Organisation")))).build()
				.perform();
		builder.click(driver.findElement(By.className(prop.getProperty("List_Manage_Organisation")))).build().perform();

		waits.waitLoop(driver, By.xpath(prop.getProperty("Link_Integration")));
		System.out.println("Manage Organisation Hovered! Displaying sub menus");
		WebElement subMenuInt = driver.findElement(By.xpath(prop.getProperty("Link_Integration")));

		Actions action2 = new Actions(driver);
		action2.click(subMenuInt).build().perform();
		Thread.sleep(2000);

		waits.waitLoop(driver, By.xpath("/html/body/div[2]/section/div/div/div/article/div/div[4]/a/p"));
		System.out.println("Listing all Integrations..");

		WebElement integrationDynamics = driver
				.findElement(By.xpath("/html/body/div[2]/section/div/div/div/article/div/div[4]/a/p"));

		Actions action3 = new Actions(driver);
		action3.click(integrationDynamics).build().perform();
		Thread.sleep(3000);
		verifyUnlinkExists();

	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public void verifyUnlinkExists() {
		WebElement unlink = driver
				.findElement(By.cssSelector("body > div.wrapper > section > div > div.row > div > article > div > a"));
		String expectedLink = "Unlink Account";
		String actualLink = unlink.getText();
		assertEquals(expectedLink, actualLink);
	}

}
