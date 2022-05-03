package com.Hcl.DirectCommerce.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.aventstack.extentreports.ExtentTest;

public class OrderRecap extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	
	
	
		@FindBy(xpath = "//div[text()='Sales Tax']//following::span[1]")
		WebElement saletax;
		public void getSaletaxvalue(String value) {
			
			String Actualstatus=saletax.getText();
			if (Actualstatus.contains(value)) {
				
				logExtentReport("Saletax is verified successfully,its Actual value displayed in UI is"+Actualstatus);
			} else {
				
				logExtentReportFail("saletax displayed is  wrong,its Actual value displayed in UI is"+Actualstatus);
				Assert.fail();
			}
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}