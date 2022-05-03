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

public class customerInformation extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public customerInformation(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	
	@FindBy(xpath = "//input[@name='sourceCode']")
	WebElement Sourcecode;
	
	@FindBy(xpath = "//input[@name='searchText']")
	WebElement ProductsearchTexbax;
	
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
			Utility.wait5Seconds();
			Actions action = new Actions(driver);	 
			action.sendKeys(Keys.ENTER).build().perform();
			logExtentReport("user able to to press Enter");
		} catch (Exception e) {
			logExtentReportFail("User is not able to press Enter");
			Assert.fail(" User is not able to press Enter");
			throw e;
		}
	}
	
public void provideSourceCode(String source) {
	
	try {		
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//input[@name='sourceCode']"));
			if (dynamicElement.size() != 0) {
				// If list size is non-zero, element is present
				Utility.wait3Seconds();
				Sourcecode.sendKeys(source);
				PressTAB();
				Utility.wait3Seconds();
				logExtentReport("user able to Enter sourcecode");
			} else {
				// Else if size is 0, then element is not present
				logExtentReport("User is not able Enter sourcecode");
			}	
	} catch (Exception e) {
		// TODO: handle exception
		logExtentReportFail("User is not able Enter sourcecode");
		Assert.fail("User is not able Enter sourcecode");
	}
			
}	
public void provideProduct(String product) {
	try {
		ProductsearchTexbax.sendKeys(product);
		PressEnter();
		Utility.wait20Seconds();
		logExtentReport("user able to Enter sourcecode");
	} catch (Exception e) {
		// TODO: handle exception
		logExtentReportFail("User is not able Enter sourcecode");
		Assert.fail("User is not able Enter sourcecode");
	}			
}	

@FindBy(xpath = "//div[@id='select-selectedPaymethod']")
WebElement selectpaymethod;
@FindBy(xpath = "(//span[text()='No'])[2]")
WebElement installmentNO;

public void selectPaymethod(String method) throws Exception {
	try {
		Utility.wait5Seconds();
		WebElement element = selectpaymethod;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Utility.wait5Seconds();
		driver.findElement(By.xpath("//div[@id='select-selectedPaymethod']//following::li[text()='" + method + "']")).click();
		logExtentReport("User is able select paymethod dropdown");
		Utility.wait5Seconds();
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("(//span[text()='No'])[2]"));
		if (dynamicElement.size() != 0) {
			// If list size is non-zero, element is present
			System.out.println("Element present");
			WebElement element1 = installmentNO;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			logExtentReport("User is able click on No installment button");
		} else {
			// Else if size is 0, then element is not present
			logExtentReport("User is not able click on No installment button");
		}
		List<WebElement> dynamicElement1 = driver
				.findElements(By.xpath("//input[@id='termsdays']"));
		if (dynamicElement1.size() != 0) {
		    driver.findElement(By.xpath("//input[@id='termsdays']")).click();
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//label[text()='Pay method']"))).doubleClick().perform();
			
		} else {
			logExtentReport("User is not able click on No element");
		}
	} catch (Exception e) {
		logExtentReportFail("User is not able select paymethod dropdown");
		Assert.fail("User is not able select paymethod dropdown");
		throw e;
	}
}



/*Note =during additional tender paymethod,after selecting the ship method EndOrder
button is not getting Enabled so here ,i am clicking outside to enable the End order button*/
//==select ship method====
@FindBy(xpath = "//div[@id='select-shipMethod']")
WebElement shipmethod;
public void selectshipMethod(String method) throws Exception {
	try {
		Utility.wait5Seconds();
		WebElement element = shipmethod;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Utility.wait5Seconds();
		driver.findElement(By.xpath("//div[@id='select-shipMethod']//following::li[text()='" + method + "']")).click();
		Utility.wait3Seconds();
		List<WebElement> dynamicElement1 = driver
				.findElements(By.xpath("//input[@name='phValue']"));
		if (dynamicElement1.size() != 0) {
			driver.findElement(By.xpath("//input[@name='phValue']")).sendKeys(Keys.ENTER);
			driver.findElement(By.xpath("//input[@name='phValue']")).sendKeys(Keys.TAB);
			executor.executeScript("arguments[0].click();", element);
			driver.findElement(By.xpath("//div[@id='select-shipMethod']//following::li[text()='" + method + "']")).click();
			
			logExtentReport("User is able to click on outside element");	
		} else {
			logExtentReport("User is not able to click on outside element");
		}
		
		logExtentReport("User is able select shipmethod dropdown");
	} catch (Exception e) {
		logExtentReportFail("User is not able select shipmethod dropdown");
		Assert.fail("User is not able select shipmethod dropdown");
		throw e;
	}
}

//====click the EndOrder button====
@FindBy(xpath = "//span[text()='End Order']")
WebElement Endorder;

public void clickOnEndOrder() {
	try {
		Utility.wait5Seconds();
		if (Endorder.isDisplayed()) {
			Endorder.click();
			Utility.wait10Seconds();
			logExtentReport("user able to click on Endorder button");
		} else {
			logExtentReportFail("user unable to click on Endorder button");
			Assert.fail();
		}
	} catch (Exception e) {
		logExtentReportFail("user unable to click on Endorder button");
		Assert.fail();
	}
}

//====click the submit order button====
@FindBy(xpath = "//span[text()='Submit Order']")
WebElement submit;

public void clickOnSubmit() {
	try {
		Utility.wait5Seconds();
		if (submit.isDisplayed()) {
			submit.click();
			logExtentReport("user able to click on submit button");
		} else {
			logExtentReportFail("user unable to click on submit button");
			Assert.fail();
		}
	} catch (Exception e) {
		logExtentReportFail("user unable to click on submit button");
		Assert.fail();
	}
}

//====click the orderoutside  button====

public void prepaidOrder(String Options) {
	try {
		Utility.wait5Seconds();
		if (driver.findElement(By.xpath("//h2[text()='Order Entry']//following::span[text()='"+ Options +"']")).isDisplayed()) {
			driver.findElement(By.xpath("//h2[text()='Order Entry']//following::span[text()='"+ Options +"']")).click();
			logExtentReport("user able to click on ousideorderEntry button");
		} else {
			logExtentReportFail("user unable to click on ousideorderEntry button");
			Assert.fail();
		}
	} catch (Exception e) {
		logExtentReportFail("user unable to click on ousideorderEntry button");
		Assert.fail();
	}
}




//====click the submit order button====
@FindBy(xpath = "//span[text()='Ok']")
WebElement OKbutton;

public void clickOnOk() {
	try {
		Utility.wait5Seconds();
		Utility.fluentlyWaitForElement(OKbutton, 60);
		if (OKbutton.isDisplayed()) {
			OKbutton.click();
			Utility.wait5Seconds();
			logExtentReport("user able to click on ok button");
		} else {
			logExtentReportFail("user unable to click on Ok button");
			Assert.fail();
		}
	} catch (Exception e) {
		logExtentReportFail("user unable to click on Ok button");
		Assert.fail();
	}
}
public void clickOnOkCCprocess() {
	try {
		Utility.wait10Seconds();
		Utility.fluentlyWaitForElement(OKbutton, 60);
		if (OKbutton.isDisplayed()) {
			OKbutton.click();
			logExtentReport("user able to click on ok button");
		} else {
			logExtentReportFail("user unable to click on Ok button");
			Assert.fail();
		}
	} catch (Exception e) {
		logExtentReportFail("user unable to click on Ok button");
		Assert.fail();
	}
}

public String getOrderNumber() {
	Utility.wait5Seconds();
	String ordernumber=null;
	try {		
		List<WebElement> dynamicElement1 = driver
				.findElements(By.xpath("//div[text()='Order No']//following::span[1]"));
		if (dynamicElement1.size() != 0) {
		     ordernumber=driver.findElement(By.xpath("//div[text()='Order No']//following::span[1]")).getText();
			logExtentReport("order number from the web is"+ordernumber);
		} else {
			logExtentReport("User is not able get the order number");
		}
	} catch (Exception e) {
		logExtentReportFail("User is not able get the order number");
		Assert.fail();
	}
	return ordernumber;
	
	
}

	// click the customer OrderDetails tab
	public void clickOnOrderdetailTab() {
		try {
			Utility.wait10Seconds();
			if (driver.findElement(By.xpath("//p[text()='Order Details']")).isDisplayed()) {
				driver.findElement(By.xpath("//p[text()='Order Details']")).click();
				logExtentReport("user able to click on OrderDetails tab");
			} else {
				logExtentReportFail("user unable  to click on Customer OrderDetails tab");
				Assert.fail();
			}
		} catch (Exception e) {
			logExtentReportFail("user unable  to click on Customer OrderDetails tab");
		}
	}
	
	
	// click the customer OrderDetails tab
		public void clickOnPaymentdetailTab() {
			try {
				Utility.wait5Seconds();
				if (driver.findElement(By.xpath("//p[text()='Payment Details']")).isDisplayed()) {
					driver.findElement(By.xpath("//p[text()='Payment Details']")).click();
					logExtentReport("user able to click on Payment Details tab");
				} else {
					logExtentReportFail("user unable  to click on Customer Payment Details tab");
					Assert.fail();
				}
			} catch (Exception e) {
				logExtentReportFail("user unable  to click on Customer Payment Details tab");
			}
		}
	
		
		// click the customer Shipping information tab
				public void clickOnShippingdetailTab() {
					try {
						Utility.wait5Seconds();
						if (driver.findElement(By.xpath("//p[text()='Shipping Information']")).isDisplayed()) {
							driver.findElement(By.xpath("//p[text()='Shipping Information']")).click();
							logExtentReport("user able to click on Shipping Information tab");
						} else {
							logExtentReportFail("user unable  to click on Customer Shipping Information tab");
							Assert.fail();
						}
					} catch (Exception e) {
						logExtentReportFail("user unable  to click on Customer Shipping Information tab");
					}
				}
				
				@FindBy(xpath = "//span[text()='Modify Order']")
				WebElement modify;

				public void clickOnModify() {
				try {
				Utility.wait20Seconds();
				Utility.fluentlyWaitForElement(submit, 60);
				if (modify.isDisplayed()) {
				modify.click();
				logExtentReport("User able to click on Modify button");
				Utility.wait10Seconds();
				} else {
				logExtentReportFail("User unable to click on Modify button");
				Assert.fail();
				}
				} catch (Exception e) {
				logExtentReportFail("User unable to click on Modify button");
				Assert.fail();
				}
				}
				
				
						
						@FindBy(xpath = "(//*[@id='modal-title'])[2]")
						WebElement modifycomment;
						
						
						@FindBy(xpath = "//*[@id='writeComments']")
						WebElement writecomment;
						
						@FindBy(xpath = "//span[text()='Add']")
						WebElement add;
						
						@FindBy(xpath = "//span[text()='Save Comments']")
						WebElement savecomment;
				
				public void addModifyOrderComment() {
					try {
					Utility.wait20Seconds();
					Utility.fluentlyWaitForElement(submit, 60);
					if (modifycomment.isDisplayed()) {
						writecomment.sendKeys("Testing");
						add.click();
						savecomment.click();						
					logExtentReport("User able to add comments");
					Utility.wait10Seconds();
					} else {
						logExtentReport("User not able to add comments");
					
					}
					} catch (Exception e) {
					logExtentReportFail("User not able to add comments");
					
					}
					}
				
				
				@FindBy(xpath = "(//button[@aria-label='Delete Customer'])[1]")
				WebElement deletecustomer1;
				
				

				@FindBy(xpath = "//h2[text()='Capture Demand ?']//following::span[text()='No']")
				WebElement capturedemand;
				
				public void deleteCustomer() throws Exception {
					try {
						deletecustomer1.click();
						capturedemand.click();
						logExtentReport("clicked on captured demand No");
					} catch (Exception e) {
						logExtentReportFail("not able to clicked on captured demand No");
						Assert.fail(" not able to clicked on captured demand No");
						throw e;
					}
				}
						
			
				
				@FindBy(xpath = "//p[text()='Please select product.']")
				WebElement Message;
				
				public void verifymessage(String message) throws Exception {
					try {
						String str=Message.getText();
						if (str==message) {
							logExtentReport("error message is displayed");
						} else {
							logExtentReport("error message is not displayed");
						}
						
					} catch (Exception e) {
						logExtentReport("error message is not displayed");
						Assert.fail(" error message is not displayed");
						throw e;
					}
				}
				
				@FindBy(xpath = "//h2[text()='Product is Out of Stock']//following::span[text()='Drop Ship']")
				WebElement forcedropship;
				
				public void clickonDropship() throws Exception {
					try {
						forcedropship.click();
						logExtentReport("clicked on forcedropship");
					} catch (Exception e) {
						logExtentReportFail("clicked on forcedropship");
						Assert.fail(" clicked on forcedropship");
						throw e;
					}
				}
				
				
				@FindBy(xpath = "//span[text()='Add Comment']")
				WebElement addcomments;
				
				
		
		public void addComment() {
			try {
			Utility.wait20Seconds();
			if (addcomments.isDisplayed()) {
				Utility.wait10Seconds();
				addcomments.click();
				writecomment.sendKeys("Testing");
				add.click();
				savecomment.click();						
			logExtentReport("User able to add comments");
			Utility.wait10Seconds();
			} else {
			logExtentReportFail("User not able to add comments");
			
			}
			} catch (Exception e) {
			logExtentReportFail("User not able to add comments");
			
			}
			}
		
		@FindBy(xpath = "//input[@id='phValue']")
		WebElement phoverride;
		public void enterPhoverride(String ph,String reason) throws Exception {
			try {
				Utility.wait5Seconds();
				phoverride.sendKeys(ph);
				phoverride.sendKeys(Keys.TAB);	
				driver.findElement(By.xpath("//h2[text()='PH Override Reason']//following::div[text()='" + reason + "']")).click();
				clickOnOk();
				logExtentReport("User is able select phoverirde reason");
			} catch (Exception e) {
				logExtentReportFail("User is unable select phoverirde reason");
				throw e;
			}
		}

}
