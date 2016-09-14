package saleforce.testauto.scripts;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dummycoderetriver {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://recursivelabs.crm.dynamics.com/";
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }

  @Test
  public void testDummycoderetriver() throws Exception {
    driver.get(baseUrl + "/main.aspx#246574255");
   Thread.sleep(3000);
    driver.findElement(By.id("cred_userid_inputtext")).clear();
    driver.findElement(By.id("cred_userid_inputtext")).sendKeys("testing1@recursivelabs.net");
    driver.findElement(By.id("cred_password_inputtext")).clear();
    driver.findElement(By.id("cred_password_inputtext")).sendKeys("Adactin123");
    driver.findElement(By.cssSelector("button#cred_sign_in_button")).click();
   // WebDriverWait wait = new WebDriverWait(driver, 60);
    //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#nav_cases > span.nav-rowLabel")));
    driver.findElement(By.cssSelector("#nav_cases > span.nav-rowLabel")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | contentIFrame1 | ]]
    driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label")).click();
    driver.findElement(By.xpath("//table[@id='crmGrid_gridBar']/tbody/tr/th[7]/table/tbody/tr/td/a/nobr/label")).click();
    driver.findElement(By.id("gridBodyTable_primaryField_{24F58C23-3A6F-E611-80DC-C4346BAC4B14}_0")).click();
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
