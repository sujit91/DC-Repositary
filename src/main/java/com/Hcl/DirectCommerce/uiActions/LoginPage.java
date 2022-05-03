package com.Hcl.DirectCommerce.uiActions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.apache.log4j.Logger;

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.utility.Utility;
import com.aventstack.extentreports.ExtentTest;
/**
 * 
 * @author sujit jena
 *
 */
public class LoginPage extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft=new SoftAssert();
	
	public LoginPage(WebDriver driver){
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='username']")
	WebElement Username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement Password;
	
	@FindBy(xpath="//div[@class='login-button']")
	WebElement Login;
	
	@FindBy(xpath="//div[@id='select-selectedConnectoinConfig']")
	WebElement account;
	
	public void selectAccountDropdown(String Value) throws Exception{
		try {
			WebElement element =account;
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-selectedConnectoinConfig']//following::li[contains(text(),'" + Value + "')]")).click();
			} catch (Exception e) {
			Assert.fail(" User is not able to select dropdown");
			throw e;
		}
	}
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement logoutbutton;
	
       //======6.6.2019================
	@FindBy(xpath="//a[@class='change-password']")
	WebElement changepassword;
	
	@FindBy(xpath="//a[@class='forgot-password']")
	WebElement resetpassword;
	
	@FindBy(xpath="//input[@name='currentPassword']")
	WebElement currentPassword;
	
	@FindBy(xpath="//span[contains(text(),'Login')]//following::span[2]")
	WebElement invalidecred;
	
	@FindBy(xpath="//input[@name='newPassword']")
	WebElement newPassword;
	
	@FindBy(xpath="//input[@name='confirmNewPassword']")
	WebElement confirmpassword;
	
	@FindBy(xpath="(//input[@name='confirmNewPassword']//following::button)[1]")
	WebElement cancel;		
			
	@FindBy(xpath="//span[contains(text(),'Submit')]")
	WebElement submit;
	
	@FindBy(xpath="(//input[@name='confirmNewPassword']//following::button)[2]")
	WebElement changePasswordbtn;
	
	
	@FindBy(xpath="//input[@name='domainName']")
	WebElement domainName;
	
	@FindBy(xpath="//input[@name='activeDirectoryUserName']")
	WebElement activeDirectoryUserName;

	@FindBy(xpath="//input[@name='activeDirectoryPassword']")
	WebElement activeDirectoryPassword;
	
	@FindBy(xpath="(//input[@name='confirmNewPassword']//following::button)[2]//following::span[2]")
	WebElement changepassErrorMessage;
	
	@FindBy(xpath="//div[contains(text(),'JDA Direct Commerce')]")
	WebElement jadmessage;
	
	@FindBy(xpath="//span[contains(text(),'Login')]//following::img")
	WebElement img;
	public static ExtentTest logger;
	public void logintoApplication(String username, String password,String account) throws Throwable {
		try {
			Utility.wait10Seconds();
			selectAccountDropdown(account);
			Username.clear();
			Utility.WaitForElementPresent(Username, 500);
			Username.sendKeys(username);
			log.info("username has been provided");
			logExtentReport("username has been provided");
			Utility.WaitForElementPresent(Password, 400);
			Password.clear();
			Password.sendKeys(password);
			log.info("password has been provided");
			logExtentReport("password has been provided");
			Login.click();
			log.info("clicked in login button");
			logExtentReport("clicked in login button");
			Utility.wait10Seconds();
			Actions action = new Actions(driver);	 
			action.sendKeys(Keys.ENTER).build().perform();
			
			Utility.WaitForElementPresent(Login, 700);
		} catch (Exception e) {
			Assert.fail("An error occured while logging into portal, check if the credentials are correct.");
		}
	}
	
	public void giveUsernamePassword(String username, String password,String account) throws Throwable {
		try {
			Utility.wait10Seconds();
			selectAccountDropdown(account);
			Username.clear();
			Utility.WaitForElementPresent(Username, 500);
			Username.sendKeys(username);
			log.info("username has been provided");
			logExtentReport("username has been provided");
			Utility.WaitForElementPresent(Password, 400);
			Password.clear();
			Password.sendKeys(password);
			log.info("password has been provided");
			logExtentReport("password has been provided");
		} catch (Exception e) {
			Assert.fail("An error occured while logging into portal, check if the credentials are correct.");
		}
	}
	public void enterChangePasswordDetails(String currentpass, String newpass,String confirm) throws Throwable {
		try {
			Utility.wait5Seconds();
			currentPassword.clear();
			Utility.WaitForElementPresent(currentPassword, 500);
			currentPassword.sendKeys(currentpass);
			logExtentReport("currentpassword  has been provided");
			Utility.WaitForElementPresent(currentPassword, 400);
			newPassword.clear();
			newPassword.sendKeys(newpass);
			logExtentReport("newPassword has been provided");
			confirmpassword.clear();
			confirmpassword.sendKeys(confirm);
			logExtentReport("confirmpassword has been provided");
			changePasswordbtn.click();
			Utility.wait5Seconds();
			logExtentReport("clicked in submit button");
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	public void enterChangePasswordforErrormesage(String currentpass, String newpass,String confirm) throws Throwable {
		try {
			Utility.wait5Seconds();
			currentPassword.clear();
			Utility.WaitForElementPresent(currentPassword, 500);
			currentPassword.sendKeys(currentpass);
			logExtentReport("currentpassword  has been provided");
			Utility.WaitForElementPresent(currentPassword, 400);
			newPassword.clear();
			newPassword.sendKeys(newpass);
			logExtentReport("newPassword has been provided");
			confirmpassword.clear();
			confirmpassword.sendKeys(confirm);
			changePasswordbtn.click();
			logExtentReport("confirmpassword has been provided");
		} catch (Exception e) {
			Assert.fail();
		}
	}
	public void enterResetPasswordDetails(String domainname, String Activedirectoryusername,String activedirectorypass,String newpass,String coonfirm) throws Throwable {
		try {
			Utility.wait5Seconds();
			domainName.clear();
			Utility.WaitForElementPresent(domainName, 500);
			domainName.sendKeys(domainname);
			logExtentReport("domainName has been provided");
			activeDirectoryUserName.clear();
			Utility.WaitForElementPresent(activeDirectoryUserName, 500);
			activeDirectoryUserName.sendKeys(Activedirectoryusername);
			logExtentReport("activeDirectoryUserName has been provided");
			activeDirectoryPassword.clear();
			Utility.WaitForElementPresent(activeDirectoryPassword, 500);
			activeDirectoryPassword.sendKeys(activedirectorypass);
			logExtentReport("activeDirectoryPassword has been provided");
			newPassword.clear();
			newPassword.sendKeys(newpass);
			logExtentReport("newPassword has been provided");
			confirmpassword.clear();
			confirmpassword.sendKeys(coonfirm);
			changePasswordbtn.click();
			logExtentReport("confirmpassword has been provided");
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	
	public void verifyChangepasswordScreen() {
	if (currentPassword.isDisplayed()&&newPassword.isDisplayed()&&confirmpassword.isDisplayed()) {
		logExtentReport("change password field are displayed and navigated to change password page");
	} else {
		logExtentReportFail("change password field are not displayed");
		Assert.fail("unable to navigate change password field");
	}		
	}
	
	public void verifyHomePage() {
		if (Username.isDisplayed()&&Password.isDisplayed()&&changepassword.isDisplayed()&&resetpassword.isDisplayed()&&Login.isDisplayed()&&jadmessage.isDisplayed()&&img.isDisplayed()) {
			logExtentReport("Home page is validated successfully");
		} else {
			logExtentReportFail("in Home page all details are not displayed");
			Assert.fail("in Home page all details are not displayed");
		}
				
		}
	
	public void verifyResetpasswordScreen() {
		if (domainName.isDisplayed()&&activeDirectoryUserName.isDisplayed()&&activeDirectoryPassword.isDisplayed()) {
			logExtentReport("Reset password field are displayed and navigated to Reset password page");
		} else {
			logExtentReportFail("Reset password field are not displayed");
			Assert.fail("unable to navigate Reset password field");
		}
			
			
		}
	
	public void clickOnSubmit() {
		try {
		WebElement element =submit;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		logExtentReport("user able to click on submit button");
	} catch (Exception e) {
		logExtentReportFail("user unable to click on submit button");
		Assert.fail();
	}

	}
	public void clickOnchangePassword() {
		try {
		WebElement element =changePasswordbtn;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		logExtentReport("user able to click on chnagePassword button");
	} catch (Exception e) {
		logExtentReportFail("user unable to click on chnagePassword button");
		Assert.fail();
	}

	}
	
	public void clickOnCancel() {
		try {
		WebElement element =cancel;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		logExtentReport("user able to click on cancel button");
	} catch (Exception e) {
		logExtentReportFail("user unable to click on cancel button");
		Assert.fail();
	}

	}
	
	
	
	public void VerifyLoginSuccessful() throws Exception {
		try {
			Utility.wait5Seconds();
			if (logoutbutton.isDisplayed()) {
				WebElement element =logoutbutton;
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				Utility.WaitForElementPresent(logoutbutton, 500);
				Utility.wait5Seconds();
				logExtentReport("successfully loged in into portal");
			} else {
				logExtentReportFail("user unable to loged in into portal");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("user unable to loged in into portal");
			Assert.fail();
			
		}
	}
	
	public void Verifylogoutbutton() throws Exception {
		try {
			Utility.wait5Seconds();
			if (logoutbutton.isDisplayed()) {
				Utility.WaitForElementPresent(logoutbutton, 500);
				logExtentReport("successfully logged in into portal");
			} else {
				logExtentReportFail("user unable to logged in into portal");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("user unable to logged in into portal");
			Assert.fail();
			
		}
	}
	
	public void VerifyErrorMessage(String  message) throws Exception {
		try {
			Utility.wait5Seconds();
			if (invalidecred.getText().equalsIgnoreCase(message)) {
				Utility.WaitForElementPresent(logoutbutton, 500);
				logExtentReport("Error message displayed properlly"+invalidecred.getText());
			} else {
				logExtentReportFail("Error message displayed improperly:::"+invalidecred.getText());
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("user should not be logged in successfully");
			Assert.fail();
		}
	}
	
	
	public void clickedonChangePassword() throws Exception {
		try {
			WebElement element = changepassword;
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("user able to click on change password");
		} catch (Exception e) {
			logExtentReport("user unable to click on change password");
			Assert.fail();
			
		}
	}
	public void clickedonResetPassword() throws Exception {
		try {
			WebElement element = resetpassword;
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("user able to click on Reset password");
		} catch (Exception e) {
			logExtentReportFail("user unable to click on Reset password");
			Assert.fail();
			
		}
	}
	
	public void VerifychangePasswordErrorMessage(String message) throws Exception {
		try {
			Utility.wait10Seconds();
			if (changepassErrorMessage.getText().equalsIgnoreCase(message)) {
				logExtentReport("Error message verified succesfully and displayed as"+changepassErrorMessage.getText());
			} else {
				logExtentReportFail("Error  message properly not displayed:::"+changepassErrorMessage.getText());
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("Error message properly not displayed:::"+changepassErrorMessage.getText());
		}
	}
	
	
	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
		log("switched to the default Content");
		logExtentReport("switched to the default Content");
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
}
