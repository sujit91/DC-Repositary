package com.Hcl.DirectCommerce.uiActions;

import java.util.List;

import org.apache.log4j.Logger;
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

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.utility.Utility;
import com.aventstack.extentreports.ExtentTest;

public class PaymentDetails extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public PaymentDetails(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	
	public void PressTAB() throws Exception {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();
			logExtentReport("user able to to press Tab");
		} catch (Exception e) {
			logExtentReportFail("User is not able to press Tab");
			Assert.fail(" User is not able to press Tab");
			throw e;
		}
	}
	
	public void PressEnter() throws Exception {
		try {
			Utility.wait10Seconds();
			Actions action = new Actions(driver);	 
			action.sendKeys(Keys.ENTER).build().perform();
			logExtentReport("user able to to press Enter");
		} catch (Exception e) {
			logExtentReportFail("User is not able to press Enter");
			Assert.fail(" User is not able to press Enter");
			throw e;
		}
	}

	@FindBy(xpath = "//p[text()='Addtional Tender']//following::input[1]")
	WebElement tindercheckbox;
	@FindBy(xpath = "//div[@id='select-selectedTendorType']")
	WebElement tendertypedropdown;
	public void selectTenderType(String tendertype) throws Exception {
		try {
			Utility.wait10Seconds();
			WebElement element = tindercheckbox;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			WebElement element1 = tendertypedropdown;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-selectedTendorType']//following::li[text()='" + tendertype + "']")).click();
			logExtentReport("User is able select selectedTendorType dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select selectedTendorType dropdown");
			Assert.fail("User is not able select selectedTendorType dropdown");
			throw e;
		}
	}
	
	@FindBy(xpath = "//p[text()='Addtional Tender']//following::input[2]")
	WebElement additionalcheckbox;
	@FindBy(xpath = "//div[@id='select-disc1']")
	WebElement disc1;
	public void Additionaldisc1(String dispercent) throws Exception {
		try {
			Utility.wait10Seconds();
			WebElement element = additionalcheckbox;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			WebElement element1 = disc1;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-disc1']//following::li[contains(text(),'" + dispercent + "']")).click();
			logExtentReport("User is able select discount dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select discount dropdown");
			Assert.fail("User is not able select discount dropdown");
			throw e;
		}
	}
	
	
	
	@FindBy(xpath = "//input[@id='codenumber']")
	WebElement giftcoupontextbox;
	
	public void ProvidegiftCoupon(String coupon) throws Exception {
		try {
			Utility.wait10Seconds();
			giftcoupontextbox.sendKeys(coupon);
			PressTAB();
			Utility.wait10Seconds();
			logExtentReport("User is able to provide giftcoupon");
		} catch (Exception e) {
			logExtentReportFail("User is not able to provide giftcoupon");
			Assert.fail("User is not able to provide giftcoupon");
			throw e;
		}
	}
	
	
	
	@FindBy(xpath = "//span[text()='Balance']")
	WebElement balancebutton;
	@FindBy(xpath = "//span[text()='Ok']")
	WebElement Okbutton;
	@FindBy(xpath = "//span[text()='Add']")
	WebElement AddButton;

	public String getCurrentBalance() {
		Utility.wait10Seconds();
		String balance = null;
		try {
			List<WebElement> dynamicElement1 = driver.findElements(By.xpath("//span[text()='Balance']"));
			if (dynamicElement1.size() != 0) {
				balancebutton.click();
				Utility.wait10Seconds();
				balance = driver.findElement(By.xpath("//p[contains(text(),'Current balance')]")).getText();
				Okbutton.click();
				logExtentReport("User is able get the gift card CurrentBalance" + balance);
			} else {
				logExtentReport("User is not able get the gift card CurrentBalance");
			}
		} catch (Exception e) {
			logExtentReportFail("User is not able get the gift card CurrentBalance");
			Assert.fail();
		}
		return balance;

	}

	
	@FindBy(xpath = "//input[@name='giftCard']")
	WebElement giftcardtextbox;
	
	public void ProvidegiftCard(String card) throws Exception {
		try {
			Utility.wait10Seconds();
			giftcardtextbox.sendKeys(card);
			AddButton.click();
			Utility.wait10Seconds();
			logExtentReport("User is able to provide giftcard");
		} catch (Exception e) {
			logExtentReportFail("User is not able to provide giftcard");
			Assert.fail("User is not able to provide giftcard");
			throw e;
		}
	}
	
	
	@FindBy(xpath = "//input[@id='giftamount']")
	WebElement amounttext;
	public void ProvideAmount(String amount) throws Exception {
		try {
			Utility.wait10Seconds();
			amounttext.clear();
			amounttext.sendKeys(amount);
			PressTAB();
			Utility.wait10Seconds();
			logExtentReport("User is able to provide giftcard");
		} catch (Exception e) {
			logExtentReportFail("User is not able to provide giftcard");
			Assert.fail("User is not able to provide giftcard");
			throw e;
		}
	}
	
	
	@FindBy(xpath = "//input[@id='remitamount']")
	WebElement rentamount;
	public void enterRemitamount(String amount) throws Exception {
		try {
			Utility.wait5Seconds();
			rentamount.clear();
			rentamount.sendKeys(amount);
			PressTAB();
			logExtentReport("User is able select phoverirde reason");
		} catch (Exception e) {
			logExtentReportFail("User is unable select phoverirde reason");
			throw e;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}