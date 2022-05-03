package com.Hcl.DirectCommerce.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.utility.Utility;
import com.aventstack.extentreports.ExtentTest;

public class CustomerSearch extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public CustomerSearch(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	//==Validate any field in the customer search field

	public void validateCustomer(String customer) {
		String name = driver.findElement(By.xpath("(//span[contains(text(),'" + customer + "')])[1]")).getText();
		if (name.contains(customer)) {
			logExtentReport("customer is available" + customer);
		} else {
			logExtentReport("customer is not available" + customer);
			Assert.fail();
		}
	}
	
	
	//click the first customer
	
	public void clickOnFirstCustomer(String customer) {
		try {
			Utility.wait10Seconds();
			if (driver.findElement(By.xpath("(//span[contains(text(),'" + customer + "')])[1]")).isDisplayed()) {
				driver.findElement(By.xpath("(//span[contains(text(),'" + customer + "')])[1]")).click();
				logExtentReport("first customer has been clicked from search page");
			} else {
				logExtentReportFail("first customer unable to click or not available");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("first customer unable to click or not available");
		}
	}
	
		
			public void clickOnFirstCustomerwithoutname() {
				try {
					Utility.wait10Seconds();
					if (driver.findElement(By.xpath("(//span[text() = 'Name'])[1]//following::span[7]")).isDisplayed()) {
						driver.findElement(By.xpath("(//span[text() = 'Name'])[1]//following::span[7]")).click();
						logExtentReport("first customer has been clicked from search page");
					} else {
						logExtentReportFail("first customer unable to click or not available");
						Assert.fail();
					}
				} catch (Exception e) {
					logExtentReportFail("first customer unable to click or not available");
				}
			}

}

