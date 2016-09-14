package dynamics.testauto.scripts;

import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportingSample {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		String extentReportFile = "C:\\Selenium\\report\\MicroSoftDynamicsRecTS.html";
		String extentReportImage = "C:\\Selenium\\report\\extentReportImage.png";
 
		// Create object of extent report and specify the report file path.
		ExtentReports extent = new ExtentReports(extentReportFile, true);
 
		// Start the test using the ExtentTest class object.
		ExtentTest extentTest = extent.startTest("My First Test",
				"Verify WebSite Title");
 
		// Launch the FireFox browser.
		WebDriver driver = new FirefoxDriver();
 
		driver.manage().window().maximize();
 
		extentTest.log(LogStatus.INFO, "Browser Launched");
 
		// Open application.
		driver.get("http://www.techbeamers.com/");
 
		extentTest.log(LogStatus.INFO, "Navigated to www.techbeamers.com");
 
		// get title.
		String title = driver.getTitle();
 
		extentTest.log(LogStatus.INFO, "Get the WebSite title");
 
		// Verify title.
		Assert.assertTrue(title.contains("Selenium Webdriver"));
 
		extentTest.log(LogStatus.PASS, "Title verified");
 
		// In case you want to attach screenshot then use below method
		// We used a random image but you've to take screenshot at run-time
		// and specify the error image path.
		extentTest.log(
				LogStatus.INFO,
				"Error Snapshot : "
						+ extentTest.addScreenCapture(extentReportImage));
 
		// Close application.
		driver.quit();
 
		extentTest.log(LogStatus.INFO, "Browser closed");
 
		// close report.
		extent.endTest(extentTest);
 
		// writing everything to document.
		extent.flush();
	}

}
