package zendesk.testauto.scripts;

import static org.junit.Assert.fail;

//import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RecordZendeskTicketTC {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");

		// working plugin installer

		ChromeOptions options = new ChromeOptions();

		options.addArguments("load-extension=C:/Users/houyu/Desktop/Chrome Extention/extension");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		JOptionPane.showMessageDialog(null, "Please click on the Cancel button and manually select a Salesforce UI.");

		baseUrl = "http://www.target.com/c/movies-music-books/-/N-5xsx0";
		driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
	}

	@Test
	public void testValidateZendeskRecord() throws Exception {

		driver.get(baseUrl);

		Thread.sleep(3000);

		if (driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]")).isDisplayed()) {
			System.out.println("Found the nearest location to the coordinates...");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(6000);
		}

		driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));

		WebElement temp = driver.findElement(By.xpath("//div[contains(@title, 'Replay by Recursive Labs')]"));

		if (temp.isDisplayed()) {
			System.out.println("Zendesk button appeared!");
			WebElement recurButtton = driver.findElement(By.xpath("//div[contains(@class, 'branding-logo')]"));

			Actions action3 = new Actions(driver);

			action3.moveToElement(recurButtton).build().perform();
			Robot uiBtn = new Robot();
			uiBtn.mouseMove(95, 1640);

			Thread.sleep(3000);
			action3.click(recurButtton).build().perform();

			System.out.println("Zendesk button clicked!");
			WebElement startRec = driver.findElement(By.linkText("Start Recording"));
			Actions action4 = new Actions(driver);

			action4.moveToElement(startRec).build().perform();

			Thread.sleep(3000);
			Robot uiBtn2 = new Robot();
			uiBtn2.mouseMove(190, 1660);
			action4.click(startRec).build().perform();

			System.out.println("Recording has started..wait for the audio record to finish initialising..");

			Thread.sleep(3000);

			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < tabs2.size(); i++) {
				System.out.println("tab --> " + i);
			}

			driver.switchTo().window(tabs2.get(1));

			System.out.println("accept alert..");

			Robot uiBtn3 = new Robot();
			uiBtn3.mouseMove(180, 300);
			Thread.sleep(3000);
			Thread.sleep(3000);

			Robot audioDialogBox = new Robot();
			audioDialogBox.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			Thread.sleep(3000);
			Point minAudioWin = new Point(2000, 1000);
			driver.manage().window().setPosition(
					minAudioWin);/* setPosition(new Point(-2000, 0)); */

			driver.switchTo().window(tabs2.get(0));

		}

		Thread.sleep(3000);

		if (driver.findElement(By.cssSelector("#COMPONENT-55785 > ul > li:nth-child(4) > a")).isDisplayed()) {

			System.out.println("Action and Adventure FOUND!");
			Thread.sleep(3000);
			WebElement recurButtton = driver.findElement(By.cssSelector("#COMPONENT-55785 > ul > li:nth-child(4) > a"));
			Actions action3 = new Actions(driver);

			action3.moveToElement(recurButtton).build().perform();
			action3.click(recurButtton).build().perform();

		}

		driver.findElement(By.xpath(".//*[@id='js-filterContainer']/ul/li[1]/section/div[2]/a/span[1]")).click();
		Thread.sleep(3000);

		if (driver.findElement(By.id("searchLabel")).isDisplayed()) {
			System.out.println("Found the search box...");

			Thread.sleep(3000);
			driver.findElement(By.id("searchLabel")).click();
			driver.findElement(By.id("search")).clear();

			driver.findElement(By.id("search")).sendKeys("project almanac");
			Thread.sleep(3000);

			driver.findElement(By.xpath("(//button[@id='searchReset'])[2]")).click();

			Thread.sleep(3000);

		}

		// Switch to the frame with the Recursive Button to STOP the recording

		System.out.println("Switching to the Recursive Frame..");
		driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));

		WebElement rcrscUI = driver.findElement(By.xpath("//div[contains(@class, 'ui recording-ui')]"));
		if (rcrscUI.isDisplayed()) {
			System.out.println("Back to recursive UI, will now click the stop record button...");
			Thread.sleep(3000);
			WebElement stopRec = driver.findElement(By.linkText("Stop"));
			Actions actionStopRec = new Actions(driver);
			actionStopRec.moveToElement(stopRec).build().perform();

			Robot uiBtnStop = new Robot();
			uiBtnStop.mouseMove(190, 1670);

			Thread.sleep(3000);
			actionStopRec.click(stopRec).build().perform();

			if (driver.findElement(By.xpath("//input[contains(@class, 'summary-input form-control input-sm')]"))
					.isDisplayed()) {
				System.out.println("Found the title input holder..");
				driver.findElement(By.xpath("//input[contains(@class, 'summary-input form-control input-sm')]"))
						.sendKeys("target");
				Thread.sleep(3000);
			}
			driver.findElement(By.xpath("//input[contains(@class, 'name-input form-control input-sm')]"))
					.sendKeys("Nancy");
			driver.findElement(By.xpath("//input[contains(@class, 'email-input form-control input-sm')]"))
					.sendKeys("test@gmail.com");

			System.out.println("Checking if the rendering has finished..");

			if (driver.findElement(By.cssSelector("body > div > a.btn.btn-success.btn-xs.sharing-button"))
					.isDisplayed()) {

				System.out.println("Found the save button..checking if currently enabled..");
				if (driver.findElement(By.cssSelector("body > div > a.btn.btn-success.btn-xs.sharing-button"))
						.isEnabled()) {
					System.out.println("Is Enabled..");
				} else {
					System.out.println("Disabled at this time..");
				}
			}

			WebElement renderingProgress = driver
					.findElement(By.xpath("//div[contains(@class, 'progress-bar progress-bar-striped active')]"));

			boolean submitBtnAppears = false;
			while (!submitBtnAppears) {
				String progressBar = "";
				boolean rendFinish = false;
				while (!rendFinish) {

					progressBar = renderingProgress.getText();
					System.out.println("renderingProgress text is --> " + progressBar);

					if (progressBar.equalsIgnoreCase("97% rendered") || progressBar.equalsIgnoreCase("98% rendered")
							|| progressBar.equalsIgnoreCase("99% rendered")) {

						System.out.println("renderingProgress is 100% exiting loop");

						WebElement previewbuttonAppears = driver
								.findElement(By.cssSelector("button[class='btn btn-xs btn-primary btnPreviewVideo']"));
						if (previewbuttonAppears.isDisplayed()) {
							System.out.println("Preview Button appeared..");
							rendFinish = true;
							submitBtnAppears = true;
							break;
						}

					}
				}
				if (submitBtnAppears) {
					System.out.println("passed by here..");
					Thread.sleep(2000);
					break;
				}

			}

			if (submitBtnAppears) {

				driver.findElement(By.linkText("Submit Ticket")).click();

			}
			Thread.sleep(5000);

			System.out.println("Submitted dialog box appears..");
			Robot submittedDialogBox = new Robot();
			submittedDialogBox.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);

			System.out.println("Replay has been submitted.");
		}

	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
