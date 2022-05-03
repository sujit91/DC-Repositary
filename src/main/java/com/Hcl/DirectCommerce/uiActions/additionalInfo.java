package com.Hcl.DirectCommerce.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.utility.Utility;
import com.aventstack.extentreports.ExtentTest;

public class additionalInfo extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	
	public void clickOnAdditionalInfoTab() {
		try {
			Utility.wait5Seconds();
			if (driver.findElement(By.xpath("//p[text()='Additional Info']")).isDisplayed()) {
				driver.findElement(By.xpath("//p[text()='Additional Info']")).click();
				logExtentReport("User able to click on Additional Info tab");
			} else {
				logExtentReportFail("User unable  to click on Additional Info tab");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("User unable  to click on Additional Info tab");
		}
	}
	
	@FindBy(id="select-backOrder")
	WebElement backorderdropdown;
	
	public void clickbackorderdropdown()
	{
		try {
			Utility.wait5Seconds();
			if (backorderdropdown.isDisplayed()) {
				backorderdropdown.click();
				logExtentReport("User able to click on Backorder dropdown");
			} else {
				logExtentReportFail("User unable  to click on Backorder dropdown");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("User unable  to click on Backorder dropdown");
		}
	}
	public void selectbackorderoption(String text)
	{
		try {
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='menu-backOrder']//following::li[text()='" + text + "']")).click();
				logExtentReport("User able to select the option- "+text);
			
			}
		catch (Exception e) {
			logExtentReportFail("User unable to select the option:"+text);
		}
	}
	
	public void selectTaxoption(String text)
	{
		try {
			Utility.wait5Seconds();
			driver.findElement(By.xpath("//div[@id='select-taxExcempt']")).click();
			driver.findElement(By.xpath("//div[@id='select-taxExcempt']//following::li[text()='" + text + "']")).click();
				logExtentReport("User able to select the option- "+text);
			
			}
		catch (Exception e) {
			logExtentReportFail("User unable to select the option:"+text);
		}
	}
	
}
