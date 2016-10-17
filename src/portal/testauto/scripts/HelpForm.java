package portal.testauto.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.LoginFunction;
import functions.WaitLoopFunction;

public class HelpForm {

	public WebDriver driver;
	public LoginFunction login;
	public WaitLoopFunction waits;
	public WebDriverWait wait;
	public Properties prop;

	@Before
	public void startUp() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginFunction();
		waits = new WaitLoopFunction();
		wait = new WebDriverWait(driver, 30);
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		driver.get(prop.getProperty("base_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		login.Login(driver);
		clickHelpBtn();
		fillInForm();
		uploadAttach();
		sendRequest();
		assertMassageDisplay();
	}

	@After
	public void close() {
		driver.close();
	}

	public void clickHelpBtn() throws Exception {
		driver.switchTo().frame(driver.findElement(By.id("launcher")));
		By help = By.id("Embed");
		waits.waitLoop(driver, help);
		driver.findElement(help).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
	}

	public void fillInForm() throws Exception {
		driver.switchTo().frame(driver.findElement(By.id("ticketSubmissionForm")));
		By name = By.cssSelector("input[name='name']");
		waits.waitLoop(driver, name);
		driver.findElement(name).sendKeys("Nancy");
		WebElement email = driver.findElement(By.cssSelector("input[name='email']"));
		email.sendKeys("test@adactin.com");
		WebElement description = driver.findElement(By.cssSelector("textarea[name='description']"));
		description.sendKeys("help");
	}

	public void uploadAttach() {
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("C:/example.docx");
	}

	public void sendRequest() {
		WebElement sendBtn = driver.findElement(By.cssSelector("input[value='Send']"));
		wait.until(ExpectedConditions.elementToBeClickable(sendBtn));
		sendBtn.click();

	}

	public void assertMassageDisplay() throws Exception {
		By msgSent = By.xpath("//*[@id='Embed']/div/div/div/header/div");
		waits.waitLoop(driver, msgSent);
		if (driver.findElement(msgSent).getText().equalsIgnoreCase("Message sent")) {
			System.out.println("Massage sent");
		}
	}
}
