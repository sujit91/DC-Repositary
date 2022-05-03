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

public class addCustomer extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public addCustomer(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	@FindBy(xpath = "//input[@name='sourceCode']")
	WebElement sourcecode;
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;
	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='street']")
	WebElement street;
	@FindBy(xpath = "//input[@id='zipcode']")
	WebElement zipcode;
	@FindBy(xpath = "//input[@id='email']")
	WebElement email;
	@FindBy(xpath = "//input[@id='day']")
	WebElement day;

	@FindBy(xpath = "//div[@id='select-title']")
	WebElement select;

	public void provideSourceCode(String Sourcecode) throws Throwable {
		try {
			sourcecode.clear();
			Utility.WaitForElementPresent(sourcecode, 500);
			sourcecode.sendKeys(Sourcecode);
			log.info("Sourcecode has been provided");
			Utility.WaitForElementPresent(sourcecode, 400);
			logExtentReport("sourcecode has been provided");

		} catch (Exception e) {
			logExtentReportFail("sourcecode has not been provided");
			Assert.fail("sourcecode has not been provided");
		}
	}

	public void providefirstName(String FirstName) throws Throwable {
		try {
			firstName.clear();
			Utility.WaitForElementPresent(firstName, 500);
			firstName.sendKeys(FirstName);
			log.info("firstName has been provided");
			Utility.WaitForElementPresent(firstName, 400);
			logExtentReport("firstName has been provided");

		} catch (Exception e) {
			logExtentReportFail("firstName has not been provided");
			Assert.fail("firstName has not been provided");
		}
	}

	public void providelastName(String LastName) throws Throwable {
		try {
			lastName.clear();
			Utility.WaitForElementPresent(lastName, 500);
			lastName.sendKeys(LastName);
			log.info("lastName has been provided");
			Utility.WaitForElementPresent(lastName, 400);
			logExtentReport("lastName has been provided");

		} catch (Exception e) {
			logExtentReportFail("lastName has not been provided");
			Assert.fail("lastName has not been provided");
		}
	}

	public void providestreetName(String Street) throws Throwable {
		try {
			street.clear();
			Utility.WaitForElementPresent(street, 500);
			street.sendKeys(Street);
			log.info("street has been provided");
			Utility.WaitForElementPresent(street, 400);
			logExtentReport("street has been provided");

		} catch (Exception e) {
			logExtentReportFail("street has not been provided");
			Assert.fail("street has not been provided");
		}
	}

	public void providezipcode(String Zipcode) throws Throwable {
		try {
			zipcode.clear();
			Utility.WaitForElementPresent(zipcode, 500);
			zipcode.sendKeys(Zipcode);
			log.info("zipcode has been provided");
			Utility.WaitForElementPresent(zipcode, 400);
			logExtentReport("zipcode has been provided");

		} catch (Exception e) {
			logExtentReportFail("zipcode has not been provided");
			Assert.fail("zipcode has not been provided");
		}
	}

	public void provideEmail(String Email) throws Throwable {
		try {
			email.clear();
			Utility.WaitForElementPresent(email, 500);
			email.sendKeys(Email);
			log.info("Email has been provided");
			Utility.WaitForElementPresent(email, 400);
			logExtentReport("Email has been provided");

		} catch (Exception e) {
			logExtentReportFail("Email has not been provided");
			Assert.fail("Email has not been provided");
		}
	}

	public void provideday(String Day) throws Throwable {
		try {
			day.clear();
			Utility.WaitForElementPresent(email, 500);
			day.sendKeys(Day);
			log.info("day has been provided");
			Utility.WaitForElementPresent(day, 400);
			logExtentReport("day has been provided");

		} catch (Exception e) {
			logExtentReportFail("day has not been provided");
			Assert.fail("day has not been provided");
		}
	}

	public void selectTitleDropdown(String Value) throws Exception {
		try {
			WebElement element = select;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-title']//following::li[text()='" + Value + "']")).click();
		} catch (Exception e) {
			Assert.fail(" User is not able to title dropdown");
			throw e;
		}
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

	@FindBy(xpath = "//div[@id='select-status']")
	WebElement status;

	public void selectstatusDropdown(String Value) throws Exception {
		try {
			WebElement element = status;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-status']//following::li[text()='" + Value + "']")).click();
			logExtentReport("User is able select status dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select status dropdown");
			Assert.fail("User is not able select status dropdown");
			throw e;
		}
	}
	
	@FindBy(xpath = "//span[text()='Submit']")
	WebElement submit;
	
	public void clickOnSubmit() throws Exception {
		try {
			WebElement element = submit;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			logExtentReport("User is able to click on submit button");
		} catch (Exception e) {
			logExtentReportFail("User is not able to click on submit button");
			Assert.fail("User is not able to click on submit button");
			throw e;
		}
	}
	public void verifycustomerSearchPage() {
		
		try {
			
			
			if (driver.findElement(By.xpath("//span[text()='Top Results']")).isDisplayed()) {
				logExtentReport("page is successfully navigated to add a new customer page");
			} else {
				logExtentReport("page is not successfully navigated to add a new customer page");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReport("page is not successfully navigated to add a new customer page");
			Assert.fail();
		}
	}
	
	@FindBy(xpath = "//div[@id='select-action']")
	WebElement action;

	public void selectactionDropdown(String Value) throws Exception {
		try {
			WebElement element = action;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-action']//following::li[text()='" + Value + "']")).click();
			logExtentReport("User is able select action dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select action dropdown");
			Assert.fail("User is not able select action dropdown");
			throw e;
		}
	}

	@FindBy(xpath = "//div[@id='select-type']")
	WebElement type;

	public void selecttypeDropdown(String Value) throws Exception {
		try {
			WebElement element = type;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-type']//following::li[text()='" + Value + "']")).click();
			logExtentReport("User is able select custtype dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select custtype dropdown");
			Assert.fail("User is not able select custtype dropdown");
			throw e;
		}
	}

	@FindBy(xpath = "//div[@id='select-rent']")
	WebElement rent;

	public void selectrentDropdown(String Value) throws Exception {
		try {
			WebElement element = rent;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-rent']//following::li[text()='" + Value + "']")).click();
			logExtentReport("User is able select rent dropdown");
		} catch (Exception e) {
			logExtentReportFail("User is not able select rent dropdown");
			Assert.fail("User is not able select rent dropdown");
			throw e;
		}
	}

	@FindBy(xpath = "//input[@name='searchText']//following::span[text()='Add as New Customer']")
	WebElement addnewcust;

	public void ClickAddCustbtwIfavailable() {

		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//input[@name='searchText']//following::span[text()='Add as New Customer']"));
		if (dynamicElement.size() != 0) {
			// If list size is non-zero, element is present
			System.out.println("Element present");
			WebElement element = addnewcust;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("User is able click on addnew cust button");
		} else {
			// Else if size is 0, then element is not present
			System.out.println("Element not present");
			logExtentReport("add new customer button is not available");
		}

	}

}
