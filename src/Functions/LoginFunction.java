package functions;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFunction {

	public WebElement email;
	public WebElement password;
	public WebElement login;
	public Properties prop;
	public DataFileLoad file;
	public String strfilepath;
	public String emailLogin;
	public String passwordLogin;

	public void Login(WebDriver driver) throws Exception {
		prop = new Properties();
		file = new DataFileLoad();
		prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		strfilepath = "./Datapool/RecursiveData.xls";

		emailLogin = file.HA_GF_readXL(1, "Email", strfilepath);
		passwordLogin = file.HA_GF_readXL(1, "Password", strfilepath);

		email = driver.findElement(By.id(prop.getProperty("Txt_Login_Username")));
		email.clear();
		email.sendKeys(emailLogin);

		password = driver.findElement(By.id(prop.getProperty("Txt_Login_Password")));
		password.clear();
		password.sendKeys(passwordLogin);
		Thread.sleep(2000);
		login = driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login")));
		login.click();

	}

}
