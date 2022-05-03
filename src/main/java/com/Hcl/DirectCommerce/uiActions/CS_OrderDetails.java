package com.Hcl.DirectCommerce.uiActions;

import org.apache.log4j.Logger;
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

public class CS_OrderDetails extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;
	public static int newamount;
	public static Float thirdcardamount;
	@FindBy(xpath="//span[text()='Order Options ']")
	WebElement OrderOption;
	
	@FindBy(xpath="//li[text()='Modify Order']")
	WebElement ModifyOption;
	
	@FindBy(xpath="//p[text()='Insurance']//following::p[1]")
	static
	WebElement insurance;
	
	@FindBy(xpath="//p[text()='Insurance']")
	static
	WebElement insurancetxt;
	
	@FindBy(xpath="//p[text()='Total for order is ']//following::p[1]")
	static
	WebElement totalamt;
	
	@FindBy(xpath="//p[text()='Total for order is ']")
	static
	WebElement totalamttxt;

	public CS_OrderDetails(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	public void orderoptionClick()
	{
		Utility.wait20Seconds();
		OrderOption.click();
	}
	public void modifyoptionClick()
	{
		Utility.wait10Seconds();
		ModifyOption.click();
		Utility.wait10Seconds();
	}
	
	
}
