
package zendesk.testauto.scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import portal.testauto.scripts.WaitLoopFunction;

public class RecordZendeskTicketTC {
	private WebDriver driver;
	private String baseUrl;
	private WaitLoopFunction waits;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		driver.manage().window().maximize();
		baseUrl = "http://testpages.clickwith.me/testpage.html";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testMicroSoftDynamicsRecTS() throws Exception {
		driver.get(baseUrl);
		new Select(driver.findElement(By.id("ui"))).selectByVisibleText("zendesk-replay-ticket");
		waits.waitLoop(driver, By.id("save"));
		driver.findElement(By.id("save")).click();

		// Switch to the frame with the Recursive Button

		driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));

		WebElement temp = driver.findElement(By.xpath("//div[contains(@title, 'Replay by Recursive Labs')]"));

		if (temp.isDisplayed()) {
			System.out.println("Zendesk appeared!");
			waits.waitLoop(driver, By.xpath("//div[contains(@class, 'branding-logo')]"));
			WebElement recurButton = driver.findElement(By.xpath("//div[contains(@class, 'branding-logo')]"));

			recurButton.click();

			System.out.println("Zendesk button clicked!");

			waits.waitLoop(driver, By.linkText("Start Recording"));
			WebElement startRec = driver.findElement(By.linkText("Start Recording"));
			startRec.click();

			System.out.println("Recording has started..wait for the audio record to finish initialising..");

			Thread.sleep(3000);
			Thread.sleep(6000);
			Thread.sleep(6000);
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

			Robot audioDialogBox = new Robot();
			audioDialogBox.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);

			Point minAudioWin = new Point(2000, 1000);
			driver.manage().window().setPosition(
					minAudioWin);/* setPosition(new Point(-2000, 0)); */

			driver.switchTo().window(tabs2.get(0));
		}

		By by1 = By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(1) > a");
		waits.waitLoop(driver, by1);
		driver.findElement(by1).click();

		By by2 = By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(3) > a");
		waits.waitLoop(driver, by2);
		driver.findElement(by2).click();

		By by3 = By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(5) > a");
		waits.waitLoop(driver, by3);
		driver.findElement(by3).click();

		By by4 = By.cssSelector("#sidebar > ul > li.active > ul > li:nth-child(2) > a");
		waits.waitLoop(driver, by4);
		driver.findElement(by4).click();

		Thread.sleep(3000);

		driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[3]")));
		waits.waitLoop(driver, By.id("rcrsv-annotation-toolbar"));

		WebElement annotFrame = driver.findElement(By.id("rcrsv-annotation-toolbar"));

		if (annotFrame.isDisplayed()) {
			System.out.println("annotation frame FOUND..");
			driver.findElement(By.id("atb-button-rectangle")).click();// rectangle

			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < tabs2.size(); i++) {
				System.out.println("tabs now after clicking annotation --> " + i);
			}
			Thread.sleep(3000);

			// Back to main page to draw
			driver.switchTo().window(tabs2.get(0));

			WebElement element = driver.findElement(By.cssSelector("#settings"));
			if (element.isDisplayed()) {
				System.out.println("Back to main page to draw..");

				Actions recPen = new Actions(driver);
				Action drawRecAction = recPen.moveToElement(element, 330, 195).clickAndHold().moveByOffset(744, 370)
						.moveByOffset(295, 514).release().build();
				drawRecAction.perform();

				Thread.sleep(8000);

			}
			driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe[3]")));// again

			WebElement annotFrame2 = driver.findElement(By.cssSelector("#rcrsv-annotation-toolbar"));

			if (annotFrame2.isDisplayed()) {
				System.out.println("change to arrow..");
				driver.findElement(By.id("atb-button-arrow")).click();// --->
				// Back to main page to draw
				driver.switchTo().window(tabs2.get(0));

				WebElement elementOfOrigin = driver.findElement(
						By.cssSelector("body > div.container > div:nth-child(2) > div.col-md-9 > blockquote > p"));
				if (element.isDisplayed()) {
					Actions arrowPen = new Actions(driver);
					Action drawArrowAction = arrowPen.moveToElement(elementOfOrigin, 600, 150).clickAndHold()
							.moveByOffset(135, 105).release().build();
					drawArrowAction.perform();
				}
			}

			Thread.sleep(10000);

		}

		// Stopping the recording
		// Switch to the frame with the Recursive Button
		System.out.println("Switching to the Recursive Frame..");
		driver.switchTo().frame(driver.findElement(By.id("recursive-social-plugin")));

		WebElement rcrscUI = driver.findElement(By.xpath("//div[contains(@class, 'ui recording-ui')]"));
		if (rcrscUI.isDisplayed()) {
			System.out.println("Back to recursive UI, will now click the stop record button...");
			WebElement stopRec = driver.findElement(By.linkText("Stop"));
			Actions actionStopRec = new Actions(driver);
			actionStopRec.moveToElement(stopRec).build().perform();

			Robot uiBtnStop = new Robot();
			uiBtnStop.mouseMove(190, 1670);
			Thread.sleep(3000);
			actionStopRec.click(stopRec).build().perform();

			Thread.sleep(3000);
			// Summary
			if (driver.findElement(By.xpath("//input[contains(@class, 'summary-input form-control input-sm')]"))
					.isDisplayed()) {
				System.out.println("Found the summary input holder..");
				driver.findElement(By.xpath("//input[contains(@class, 'summary-input form-control input-sm')]"))
						.sendKeys("Zendesk Ticket UI Sep22");
				Thread.sleep(3000);
			}
			// Details
			if (driver.findElement(By.xpath("//textarea[contains(@class, 'details-input form-control input-sm')]"))
					.isDisplayed()) {
				System.out.println("Found the details input holder..");
				driver.findElement(By.xpath("//textarea[contains(@class, 'details-input form-control input-sm')]"))
						.sendKeys("Zendesk Ticket 0920.");
				Thread.sleep(3000);
			}

			if (driver.findElement(By.xpath("//input[contains(@class, 'name-input form-control input-sm')]"))
					.isDisplayed()) {
				System.out.println("Found the name input holder..");
				driver.findElement(By.xpath("//input[contains(@class, 'name-input form-control input-sm')]"))
						.sendKeys("Nancy");
				Thread.sleep(3000);
			}

			// Email
			if (driver.findElement(By.xpath("//input[contains(@class, 'email-input form-control input-sm')]"))
					.isDisplayed()) {
				System.out.println("Found the email input holder..");
				driver.findElement(By.xpath("//input[contains(@class, 'email-input form-control input-sm')]"))
						.sendKeys("nancy@email.com");
				Thread.sleep(3000);
			}

			System.out.println("renderingProgress in progress..");
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
						Thread.sleep(6000);
						WebElement previewbuttonAppears = driver
								.findElement(By.cssSelector("button[class='btn btn-xs btn-primary btnPreviewVideo']"));
						if (previewbuttonAppears.isDisplayed()) {
							System.out.println("Preview Button appeared..");
							rendFinish = true;
							submitBtnAppears = true;
							break;

						}
						if (driver.findElement(By.linkText("Submit Ticket")).isDisplayed()) {
							System.out.println("Submit button enabled..");

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
				System.out.println("Submit button enabled..");
				Thread.sleep(1000);

				driver.findElement(By.linkText("Submit Ticket")).click();

			}
			Thread.sleep(9000);

			System.out.println("Submitted dialog box appears..");
			Robot submittedDialogBox = new Robot();
			submittedDialogBox.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

}
