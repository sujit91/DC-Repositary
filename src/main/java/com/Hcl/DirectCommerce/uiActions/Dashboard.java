package com.Hcl.DirectCommerce.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Dashboard extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public Dashboard(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	@FindBy(xpath = "//input[@name='searchText']")
	WebElement searchbox;

	@FindBy(xpath = "//input[@name='searchText']//following::button[1]")
	WebElement searchButton;

	public void Verifysearchbox() throws Exception {
		try {
			Utility.wait5Seconds();
			if (searchbox.isDisplayed()) {
				Utility.WaitForElementPresent(searchbox, 500);
				logExtentReport("search bbox is available");
			} else {
				logExtentReportFail("search bbox is not available");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("search box is not available");
			Assert.fail();

		}
	}

	public void provideSearchFieldValueAndClickSearch(String customer) throws Exception {
		try {
			Utility.wait10Seconds();
			if (searchbox.isDisplayed()) {
				searchbox.sendKeys(customer);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", searchButton);
				logExtentReport("valid customer page is navigated");
				Utility.wait5Seconds();
			} else {
				logExtentReportFail("valid customer page is not navigated");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("valid customer page is not navigated");
			Assert.fail();

		}
	}

	public void verifyDashboard(String message) {
		String actualmsg = " ";
		try {
			WebElement actvalue1 = driver.findElement(By.xpath("//p[contains(text(),'" + message + "')]"));
			actualmsg = actvalue1.getAttribute("innerText");
			logExtentReport("actual message is " + actualmsg);
			if (message.equalsIgnoreCase(actualmsg)) {
				logExtentReport("on dash board the tab present is" + actualmsg);
			} else {
				logExtentReport("on dash board the tab not present is" + actualmsg);
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReport("on dash board the tab not present is" + actualmsg);
			Assert.fail();
		}
	}

	public void verifyDifferentHomepage(String message) {
		Utility.wait5Seconds();
		try {
			if (driver.findElement(By.xpath("//span[contains(text(),'" + message + "')]")).isDisplayed()) {
				logExtentReport("successfully login into home page of" + message);
			} else {
				logExtentReport("unable to login homepage of " + message);
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReport("unable to login homepage of" + message);
			Assert.fail();
		}
	}

	public void VerifysearchDropdownDefaultValue(String option) throws Exception {
		String str = driver.findElement(By.xpath("//input[@name='searchText']//preceding::div[2]/div"))
				.getAttribute("innerText");

		if (str.contains(option)) {
			logExtentReport("default value shpuld be " + option);
			return;
		} else {
			logExtentReport("default value is not displayed" + option);
			Assert.fail();
		}
	}
	
	
	@FindBy(xpath = "//input[@name='searchText']//preceding::div[2]/div")
	WebElement dropdown;

	public void selectSearchDropdown(String Value) throws Exception {
		try {
			WebElement element = dropdown;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.wait5Seconds();
			driver.findElement(
					By.xpath("//input[@name='searchText']//preceding::div[2]/div//following::li[contains(text(),'"
							+ Value + "')]"))
					.click();
			logExtentReport("user able to select drop down value" + Value);
		} catch (Exception e) {
			logExtentReport("user un able to select drop down value" + Value);
			Assert.fail(" User is not able to select dropdown");
			throw e;
		}
	}

	public void verifyAddicon() {

		if (driver.findElement(By.xpath("//input[@name='searchText']//following::span[contains(text(),'a')]"))
				.isDisplayed()) {
			logExtentReport("Add icon is displayed");
			return;
		} else {
			logExtentReport("Add icon is not displayed");
			Assert.fail();
		}
	}

	public void verifyFilterField(String field) {

		if (driver
				.findElement(By.xpath(
						"//span[contains(text(),'Top Results')]//following::p[contains(text(),'" + field + "')]"))
				.isDisplayed()) {
			logExtentReport("field is available" + field);
			return;
		} else {
			logExtentReport("field is not available" + field);
			Assert.fail();
		}

	}

	public void clickonbutton(String field) {

		try {
			Utility.wait20Seconds();
			//Utility.fluentlyWaitForElement(driver.findElement(By.xpath("//p[contains(text(),'" + field + "')]")), 60);
			WebElement element = driver.findElement(By.xpath("//p[contains(text(),'" + field + "')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("user able to click on button" + field);
			Utility.wait5Seconds();
		} catch (Exception e) {
			logExtentReportFail("user unable to click on button" + field);
			Assert.fail();
		}
	}

	@FindBy(xpath = "(//input[@name='searchText']//following::button//*[name()='svg'])[2]")
	WebElement addicon;

	public void clickonAddicon() {

		try {
			WebElement element = addicon;
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();
			Utility.wait10Seconds();
			logExtentReport("user able to click add button");
			Utility.wait5Seconds();
		} catch (Exception e) {
			logExtentReportFail("user unable to click on add button");
			Assert.fail();
		}
	}

}
