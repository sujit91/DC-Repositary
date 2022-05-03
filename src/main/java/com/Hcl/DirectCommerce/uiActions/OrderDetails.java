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

public class OrderDetails extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public OrderDetails(WebDriver driver) {
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

	
	@FindBy(xpath = "//h2[text()='Product Availability']")
	WebElement productAvailablePopup;
	@FindBy(xpath = "(//h2[text()='Product Availability']//following::span[text()='Yes'])[1]")
	WebElement YesButton;
	@FindBy(xpath = "//h2[text()='Product Availability']//following::b[1]")
	WebElement productQty;
	@FindBy(xpath = "(//h2[text()='Product Availability']//following::span[text()='No'])[1]")
	WebElement NoButton;
	@FindBy(xpath = "//span[text()='Ok']")
	WebElement Okbutton;
	
	public String productAvailabilityPopup() {
		String avaiableqty=null;
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Product Availability']"));
		if (dynamicElement.size() != 0) {
			
			 avaiableqty=productQty.getText();
			
			System.out.println("Element present");
			WebElement element1 = YesButton;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			logExtentReport("User is able click on Yes  button");
		} else {
	
			logExtentReportFail("popup is not available");
			//Assert.fail();
		}
		return avaiableqty;
		
		
	}
	public String productAvailabilityPopupNo() {
		String avaiableqty=null;
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Product Availability']"));
		if (dynamicElement.size() != 0) {
			
			 avaiableqty=productQty.getText();
			
			System.out.println("Element present");
			WebElement element1 = NoButton;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			logExtentReport("User is able click on No  button");
		} else {
	
			logExtentReportFail("popup is not available");
			//Assert.fail();
		}
		return avaiableqty;
		
		
	}
	
	public void productAvailabilityPopupYes() {
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Product Availability']"));
		if (dynamicElement.size() != 0) {
			
			
			System.out.println("Element present");
			WebElement element1 = YesButton;
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			Utility.wait5Seconds();
			logExtentReport("User is able click on Yes  button");
		} else {
	
			logExtentReportFail("popup is not available");
			//Assert.fail();
		}
		
	}
	@FindBy(xpath = "//div[text()='Total selected Quantity:']//following::input[1]")
	WebElement promptingR1;
	
	@FindBy(xpath = "//span[text()='Submit']")
	WebElement submit;
	public void selectPromptingitem(String qty) {
		Utility.wait10Seconds();
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//div[text()='Total selected Quantity:']//following::input[1]"));
		if (dynamicElement.size() != 0) {
			promptingR1.clear();
			promptingR1.sendKeys(qty);
			Utility.wait10Seconds();
			logExtentReport("User is provide prompting item qty");
			submit.click();
			Utility.wait10Seconds();
		} else {
		
			logExtentReportFail("User is unable to provide prompting item qty");
			Assert.fail();
		}
		
		
	}
	
	
	@FindBy(xpath = "//input[@name='quantity']")
	WebElement Eqtyfield;
	
	@FindBy(xpath = "//input[@name='price']")
	WebElement diaablepricefld;
	
	public void modifyQtyPopupE1(String qty) {
		Utility.wait10Seconds();
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Modify Quantity']"));		
		if (dynamicElement.size() != 0) {
			if (!diaablepricefld.isEnabled()) {
				logExtentReport("price field is disable");
			} else {
				logExtentReportFail("price field is enabled");
			}
			Eqtyfield.clear();
			Eqtyfield.sendKeys(qty);
			Utility.wait10Seconds();
			logExtentReport("User is provide modifyfield item qty");
			submit.click();
		} else {
			logExtentReportFail("User is unable to provide modifyfield item qty");
			Assert.fail();
		}
		
		
		
	}
	
	public void modifyQtyPopupE2(String qty) {
		Utility.wait10Seconds();
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Modify Quantity']"));		
		if (dynamicElement.size() != 0) {
			if (diaablepricefld.isEnabled()) {
				logExtentReport("price field is enabled");
			} else {
				logExtentReportFail("price field is disabled");
			}
			Eqtyfield.clear();
			Eqtyfield.sendKeys(qty);
			Utility.wait5Seconds();
			logExtentReport("User is provide modifyfield item qty");
			submit.click();
		} else {
			logExtentReportFail("User is unable to provide modifyfield item qty");
			Assert.fail();
		}
		
		
		
	}
	
	public void modifyQtyOnly(String qty) {
		Utility.wait10Seconds();
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//h2[text()='Modify Quantity']"));		
		if (dynamicElement.size() != 0) {
			if (diaablepricefld.isEnabled()) {
				logExtentReport("price field is enabled");
			} else {
				logExtentReportFail("price field is disabled");
			}
			Eqtyfield.clear();
			Eqtyfield.sendKeys(qty);
			Utility.wait5Seconds();
			logExtentReport("User is provide modifyfield item qty");
		} else {
			logExtentReportFail("User is unable to provide modifyfield item qty");
			Assert.fail();
		}
		
		
		
	}
	
	
	//====for status only null  this method will work out
	@FindBy(xpath = "//p[text()='Status']//following::p[1]")
	WebElement status;
	public void verifyStatus(String Status) {
		
		String Actualstatus=status.getText();
		if (Actualstatus.contains(Status)) {
			
			logExtentReport("Status is verified successfully,its Actual status displayed in UI is"+Actualstatus);
		} else {
			
			logExtentReportFail("Status displayed is  wrong,its Actual status displayed in UI is"+Actualstatus);
			//Assert.fail();
		}
		
	}
	
	
	@FindBy(xpath = "//p[text()='Available Quantity']//following::p[1]")
	WebElement avlqty;
	
	public void verifyAvailableQty(String qty) {
		
		String Actualqty=avlqty.getText().trim();
		
		if (Actualqty.startsWith("0")) {
			Actualqty = Actualqty.substring(1);
		} else {
		 Actualqty=avlqty.getText().trim();
		}
		if (Actualqty.contains(qty)) {
			
			logExtentReport("avlqty is verified successfully,its Actual qty displayed in UI is"+Actualqty);
		} else {
			
			logExtentReportFail("avlqty displayed is  wrong,its Actual qty displayed in UI is"+Actualqty);
			Assert.fail();
		}
		
	}
	
	
	
	@FindBy(xpath = "//div[text()='Quantity']//following::input[2]")
	WebElement istqtyfield;

	public void modifyQuantity(String qty) {
		try {
			Utility.wait10Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//div[text()='Quantity']//following::input[2]"));
			if (dynamicElement.size() != 0) {
				istqtyfield.click();
				istqtyfield.clear();
				istqtyfield.sendKeys(qty);
				PressTAB();
				Utility.wait10Seconds();
			} else {
				logExtentReportFail("User is unable to modify item qty");
			}
		} catch (Exception e) {
			logExtentReportFail("User is unable to modify item qty");
			Assert.fail();

		}
	}
	
	
	
	@FindBy(xpath = "//input[@id='cardNumber']")
	WebElement cardnumbertext;
	public void EnterVisaCardnumber(String cardnumber) {
		try {
			cardnumbertext.clear();
			cardnumbertext.sendKeys(cardnumber);
			Utility.wait5Seconds();
			logExtentReport("User is able to provide card number");
		} catch (Exception e) {
			logExtentReportFail("User is unable to provide card number");
			Assert.fail();
		
		}
	}

	@FindBy(xpath = "//input[@id='expireDate']")
	WebElement Expirytext;
	
	public void ExpiryDatetextfield(String cardnumber) {
		try {
			Expirytext.clear();
			Expirytext.sendKeys(cardnumber);
			Utility.wait5Seconds();
			logExtentReport("User is able to provide ExpiryDatetextfield");
		} catch (Exception e) {
			logExtentReportFail("User is unable to provide ExpiryDatetextfield");
			Assert.fail();
		
		}
	}
	
	
	@FindBy(xpath = "//input[@id='cvv']")
	WebElement cvvtext;
	
	public void Cvvtextfield(String cvvnumber) {
		List<WebElement> dynamicElement = driver
				.findElements(By.xpath("//input[@id='cvv']"));
		if (dynamicElement.size() != 0) {
			cvvtext.clear();
			cvvtext.sendKeys(cvvnumber);
			Utility.wait5Seconds();
			logExtentReport("User is provided cvv number");
			//submit.click();
		} else {
			logExtentReportFail("User is unable provided cvv number");
		}
	}
	
	@FindBy(xpath = "//div[text()='Unit Price']//following::input[1]")
	WebElement pricefield;
	public void PriceOverride(String qty) {
		try {
			Utility.wait5Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//div[text()='Unit Price']//following::input[1]"));
			if (dynamicElement.size() != 0) {
				pricefield.click();
				pricefield.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				Utility.wait5Seconds();
				pricefield.sendKeys(qty);
				PressTAB();
				Utility.wait5Seconds();
			} else {
				logExtentReportFail("User is unable to modify price of the item");
			}
		} catch (Exception e) {
			logExtentReportFail("User is unable to modify price of the item");
			Assert.fail();
		}
	}
	
	public void verifyPriceOverride(String value) {
		try {
			Utility.wait10Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//div[text()='Unit Price']//following::input[1]"));
			if (dynamicElement.size() != 0) {
				Utility.wait3Seconds();
				String text=pricefield.getAttribute("value");
				Double d=Double.valueOf(text);
				String s=String.valueOf(d).replaceFirst("\\.0+$", "");
	           if (s.equalsIgnoreCase(value)) {
	        	   logExtentReport("header price is divided properly");
			} else {
				logExtentReportFail("header price is not divided properly");
			}}			
		} catch (Exception e) {
			logExtentReportFail("header price is not divided properly");
			Assert.fail();
		}
	}
	
	@FindBy(xpath = "//div[text()='Unit Price']//following::input[2]")
	WebElement pricefieldinE2popup;
	public void PriceOverrideE2popup(String price) {
		try {
			Utility.wait10Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//div[text()='Unit Price']//following::input[2]"));
			if (dynamicElement.size() != 0) {
				pricefieldinE2popup.click();
				pricefieldinE2popup.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				Utility.wait5Seconds();
				pricefieldinE2popup.sendKeys(price);
				PressTAB();
				Utility.wait10Seconds();
			} else {
				logExtentReportFail("User is unable to modify price of the item in header");
			}
		} catch (Exception e) {
			logExtentReportFail("User is unable to modify price of the item in header");
			Assert.fail();
		}
	}
	
	@FindBy(xpath = "//input[@name='price']")
	WebElement pricefieldinpopup;
	public void PriceOverridePopup(String qty) {
		try {
			Utility.wait10Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//input[@name='price']"));
			if (dynamicElement.size() != 0) {
				pricefieldinpopup.click();
				Utility.wait3Seconds();
				pricefieldinpopup.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				Utility.wait3Seconds();
				pricefieldinpopup.sendKeys(qty);
				PressTAB();
				Utility.wait10Seconds();
			} else {
				logExtentReportFail("User is unable to modify price of the item");
			}
		} catch (Exception e) {
			logExtentReportFail("User is unable to modify price of the item");
			Assert.fail();
		}
	}
	
	
	
	public void PriceOverrideReason(String reason) {
		try {
			Utility.wait5Seconds();
			WebElement element = driver.findElement(By.xpath("//div[text()='" + reason + "']"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("User is able select price override reason");
			Okbutton.click();
			Utility.wait5Seconds();		
		} catch (Exception e) {
			logExtentReportFail("User is unable select price override reason");
			Assert.fail();

		}
	}
	
	public void PriceOverrideReasoninE2popup(String reason) {
		try {
			Utility.wait5Seconds();
			WebElement element = driver.findElement(By.xpath("//div[text()='" + reason + "']"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logExtentReport("User is able select price override reason");
			Utility.wait5Seconds();	
			submit.click();
			Utility.wait10Seconds();		
		} catch (Exception e) {
			logExtentReportFail("User is unable select price override reason");
			Assert.fail();

		}
	}
	
	public void PriceOverrideReasonPopup(String reason) {
		try {
			Utility.wait10Seconds();
			List<WebElement> dynamicElement = driver
					.findElements(By.xpath("//h2[text()='Price Override Reason']"));
			if (dynamicElement.size() != 0) {
				WebElement element = driver.findElement(By.xpath("//div[text()='" + reason + "']"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				logExtentReport("User is able select price override reason");
				Okbutton.click();
				Utility.wait5Seconds();	
				submit.click();
			} else {
				logExtentReportFail("User is unable select price override reason");
				submit.click();
			}
		} catch (Exception e) {
			logExtentReportFail("User is unable select price override reason");
			submit.click();

		}
	}
	
	
	
	
}
