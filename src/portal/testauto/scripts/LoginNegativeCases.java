package portal.testauto.scripts;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import functions.DataFileLoad;
import functions.WaitLoopFunction;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

@RunWith(Parameterized.class)

public class LoginNegativeCases {
	public WebDriver driver;
	public WaitLoopFunction waits;
	public WebElement email;
	public WebElement password;
	public WebElement login;
	public Properties prop;
	public DataFileLoad file;
	public String strfilepath;
	private String emailLogin;
	private String passwordLogin;
	private String expectedErrorMsg;
	private List<WebElement> errorMsg;

	public LoginNegativeCases(String emailLogin, String passwordLogin, String expectedErrorMsg) {
		this.emailLogin = emailLogin;
		this.passwordLogin = passwordLogin;
		this.expectedErrorMsg = expectedErrorMsg;
	}

	@Parameters
	public static Collection<Object[]> data() throws FileNotFoundException, IOException, BiffException {

		Object[][] data = new Object[5][3];

		FileInputStream fs = new FileInputStream(new File("./Datapool/RecursiveData.xls"));
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));

		Workbook wb = Workbook.getWorkbook(fs, ws);

		Sheet s = wb.getSheet(0);

		for (int i = 2; i < 7; i++) {
			data[i - 2][0] = s.getCell(0, i).getContents();
			data[i - 2][1] = s.getCell(1, i).getContents();
			data[i - 2][2] = s.getCell(3, i).getContents();
		}

		return Arrays.asList(data);
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/Chrome/chromedriver.exe");
		driver = new ChromeDriver();
		waits = new WaitLoopFunction();
		file = new DataFileLoad();
		prop = new Properties();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));

	}

	@Test
	public void test() throws Exception {

		driver.get(prop.getProperty("base_url"));
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		enterUsername();
		enterPassword();
		clickLogin();
		assertErrorMessage();

	}

	@After
	public void close() {
		driver.close();
	}

	public void enterUsername() {
		// strfilepath = "./Datapool/RecursiveData.xls";
		// emailLogin = file.HA_GF_readXL(index, "Email", strfilepath);
		System.out.println(emailLogin);
		email = driver.findElement(By.id(prop.getProperty("Txt_Login_Username")));
		email.clear();
		email.sendKeys(emailLogin);
	}

	public void enterPassword() {
		// passwordLogin = file.HA_GF_readXL(ind, "Password", strfilepath);
		System.out.println(passwordLogin);
		password = driver.findElement(By.id(prop.getProperty("Txt_Login_Password")));
		password.clear();
		password.sendKeys(passwordLogin);
	}

	public void clickLogin() throws Exception {
		Thread.sleep(2000);
		login = driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login")));
		login.click();

	}

	private void assertErrorMessage() throws Exception {
		// expectedErrorMsg = file.HA_GF_readXL(in, "Error Massage",
		// strfilepath);
		errorMsg = driver.findElements(By.xpath(".//*[@class='alert__text']"));
		for (WebElement msg : errorMsg) {
			if (!msg.getText().isEmpty()) {
				String actualMsg = msg.getText();
				System.out.println("Expected error message is " + expectedErrorMsg);
				System.out.println("Actual error message is " + actualMsg);
				assertEquals(expectedErrorMsg, actualMsg);
			}

		}

		Thread.sleep(3000);
	}

}
