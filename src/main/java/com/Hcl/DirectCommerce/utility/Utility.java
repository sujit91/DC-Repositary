package com.Hcl.DirectCommerce.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;

public class Utility extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	public static SoftAssert s_assert = new SoftAssert();
	public Utility(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public static WebDriver driver;
	static Properties properties;
	public static void loadData() throws Exception{
		properties=new Properties();
		File f=new File(System.getProperty("user.dir")+ "\\Configurations\\config.properties");
		FileReader obj=new FileReader(f);
		properties.load(obj);
		
	}

	public static String getObject(String Data) throws Exception{
		loadData();
		String data=properties.getProperty(Data);
		return data;
		
	}
	
	/****
	 * quitAlldrivers
	 * 
	 * @author sujit
	 */
	public static void quitAlldrivers() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM Chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void WaitForElementPresent(WebElement element, int time) throws Exception {
		try{	
		WebDriverWait newWait = new WebDriverWait(driver, time);
		newWait.until(ExpectedConditions.visibilityOf(element));	
		}catch(Exception e){
			
		}
	}
	
	public static void wait1Min(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 100000){

		}		
	}

	public static void wait20Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 20000){

		}		
	}  
	public static void wait40Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 40000){

		}		
	}  

	public static void wait5Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 5000){

		}		
	}  
	public static void wait3Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 3000){

		}		
	}  
	
	
	public static void wait10Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 10000){

		}		
	}  

	public static void waitTime(int n){
		long start = new Date().getTime();
		int x = n*1000;
		while(new Date().getTime() - start < x){

		}		
	}
	
	public static void SelectTextByVisibleText(WebElement objname, String text) throws Exception {
		try {
			Select select = new Select((objname));

			select.selectByVisibleText(text);

		} catch (Exception e) {
			System.out.println("'" + objname + "' is not present or text '" + text + "' is not selected");
			Assert.fail("'" + objname + "' is not present or text '" + text + "' is not selected");
			throw e;
		}

	}
	
	 public static WebDriver LaunchBrowser(){
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriver driver = new ChromeDriver(cap);
			driver.manage().window().maximize();
			return driver;
		}
	 
	 public static void WaitForObjectToBeClickable(WebElement element, int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}

		public static void WaitForObjectToBePresent(WebElement element, int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
	 public static void Click(WebElement objname) throws Exception {
			try {
				WaitForObjectToBeClickable(objname, 300);
				WaitForElementPresent(objname, 300);
				String objText = objname.getText();
				if (objname.isDisplayed())
					objname.click();
				
				logExtentReport("Clicking on " + objText);
				s_assert.assertTrue(true);
			} catch (Exception e) {
				System.out.println("element '" + objname + "' is not displayed");
				logExtentReport("Unable to Click on the" + objname);
				s_assert.assertTrue(false);	
				Assert.fail("element '" + objname + "' is not displayed");

				throw e;
			}
		}
		    
		    /*#############################################################
		    '    
		    '    METHOD NAME        : fileUpload
		    '
		    '    DESCRIPTION        : This method is executed an autoIt executable to upload a file
		    '
		    '    ARGUMENTS          : filePath
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      :
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
		    
		    
		public static void fileUpload(String filepath) throws Exception {
			try {
				StringSelection s = new StringSelection(filepath);

				System.out.println("Upload file path copied to clipboard: " + s.toString());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
				String autoITExecutable = System.getProperty("user.dir") + "//execs//sendFilePathToOpenWindow.exe " + filepath;
				Process p = Runtime.getRuntime().exec(autoITExecutable);
				p.waitFor();
				logExtentReport("AutoiIt Script should run successfully.");
			} catch (Exception e) {
				logExtentReport("An error occured while running the script.");
				Assert.fail("An error occured while running the script.");
			}
		}
		    
		    /*#############################################################
		    '    
		    '    METHOD NAME        : moveToElement
		    '
		    '    DESCRIPTION        : This method is a copy from GenericMethods class it
		    '							will move the cursor to given webelement
		    '
		    '    ARGUMENTS          : filePath
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
		    
			public static void moveToElement(WebElement element) throws Exception {
				Actions action = new Actions(driver);
				try {
					action.moveToElement(element).build().perform();
					logExtentReport("Verify hovering over " + element.getText() + " is possible");
					s_assert.assertTrue(true);
				} catch (Exception e) {
					logExtentReport("Test Step failed,Verify hovering over " + element.getText());
					s_assert.assertTrue(false);
					e.printStackTrace();
				}
				Thread.sleep(3000);
			}

		    /*#############################################################
		    '    
		    '    METHOD NAME        : validateAscendingOrder
		    '
		    '    DESCRIPTION        : This method is checking if given list is in ascending order
		    '
		    '    ARGUMENTS          : List containing elements
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
			
		public static boolean verifyAscendingOrder(List<String> listOfData) throws Exception {
			try {
				boolean ascending = true;
				boolean checkifString = true;
				try {
					int a = Integer.parseInt(listOfData.get(0));
					checkifString = false;
				} catch (Exception e) {
					checkifString = true;
				}

				if (!checkifString) {
					for (int i = 1; i < listOfData.size() && ascending; i++) {
						int valuea = Integer.parseInt(listOfData.get(i));
						int valueb = Integer.parseInt(listOfData.get(i - 1));
						ascending = ascending && valuea >= valueb;
					}
				} else {
					for (int i = 1; i < listOfData.size() && ascending; i++) {
						ascending = ascending && listOfData.get(i).compareTo(listOfData.get(i - 1)) >= 0;
					}
				}
				if (ascending) {
					logExtentReport("Date should get validated successfully");
					s_assert.assertTrue(true);
					return true;
				}
				return false;
			} catch (Exception e) {				
				logExtentReport("Given data is neither ascending nor descending");
				s_assert.assertTrue(false);
				return false;
			}
		}

			/*#############################################################
		    '    
		    '    METHOD NAME        : validateDescendingOrder
		    '
		    '    DESCRIPTION        : This method is checking if given list is in descending order
		    '
		    '    ARGUMENTS          : List containing elements
		    '
		    '    AUTHOR             : sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
			
			public static boolean verifyDescendingOrder(List<String> listOfData) throws Exception{
				try{
					
					boolean descending = true;
					boolean checkifString = true;
					try {
						int a = Integer.parseInt(listOfData.get(0));
						checkifString = false;
					} catch (Exception e) {
						checkifString = true;
					}

					if (!checkifString) {
						for (int i = 1; i < listOfData.size() && descending; i++) {
							int valuea = Integer.parseInt(listOfData.get(i));
							int valueb = Integer.parseInt(listOfData.get(i - 1));
							descending = descending && valuea <= valueb;
						}
					}else{
					for(int i=1; i<listOfData.size() && descending; i++){
						descending = descending && listOfData.get(i).compareTo(listOfData.get(i-1)) <= 0;
					}
					}
					if(descending){
						logExtentReport("Given dates are in descending order.");
						s_assert.assertTrue(true);
						return true;
					}
					return false;
				}catch(Exception e){
					logExtentReport("Given data is neither ascending nor descending order.");
					s_assert.assertTrue(false);
					return false;
				}
			}
			
			/*#############################################################
		    '    
		    '    METHOD NAME        : isVisible
		    '
		    '    DESCRIPTION        : This method is used to check if the given WebElement is visible on the page or not.
		    '
		    Ѡ     ARGUMENTS         : WebElement
		    '
		    '    AUTHOR             : sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
			
			public static boolean isVisible(WebElement element) throws Exception{
				try{
					if(element.isDisplayed()){
						return true;
					}else{
						return false;
					}
				}catch(Exception e){
					return false;
				}
			}

			/*#############################################################
		    '    
		    '    METHOD NAME        : checkIfDatesInAscendingOrder
		    '
		    '    DESCRIPTION        : This method is used to check if given list of dates are in ascending order or not..
		    '
		    Ѡ     ARGUMENTS         : WebElement
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
			
		public static boolean checkIfDatesInAscendingOrder(List<String> originalStringData, String DateFormat) throws Exception {
			try {
				DateFormat formatter = new SimpleDateFormat(DateFormat);
				List<Date> temp = new ArrayList<Date>();
				for (int i = 0; i < originalStringData.size(); i++) {
					Date date = (Date)formatter.parse(originalStringData.get(i));
					temp.add(date);
				}
				
				
				List<Date> original = new ArrayList<Date>();
				
				for (int i=0; i< temp.size() ; i++) {
					  original.add((Date)temp.get(i));
					}
				
				Collections.sort(temp);
				List<Date> expected = temp;
				if(expected.equals(original)){
					logExtentReport("Given dates are in descending order.");
					s_assert.assertTrue(true);
					return true;
				}
			} catch (Exception e) {
				logExtentReport("Error occured while verifying ascending order of Dates");
				Assert.fail("Error occured while verifying ascending order of dates.");
			}
			return false;
		}
		
		/*#############################################################
	    '    
	    '    METHOD NAME        : checkIfDatesInDescendingOrder
	    '
	    '    DESCRIPTION        : This method is used to check if given list of dates are in descending order or not..
	    '
	    Ѡ     ARGUMENTS         : WebElement
	    '
	    '    AUTHOR             :  sujit jena
	    '
	    '    CREATION DATE      : 
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */
		
		public static boolean checkIfDatesInDescendingOrder(List<String> originalStringData, String DateFormat)
				throws Exception {
			try {
				DateFormat formatter = new SimpleDateFormat(DateFormat);
				List<Date> temp = new ArrayList<Date>();
				for (int i = 0; i < originalStringData.size(); i++) {
					Date date = (Date) formatter.parse(originalStringData.get(i));
					temp.add(date);
				}

				List<Date> original = new ArrayList<Date>();

				for (int i = 0; i < temp.size(); i++) {
					original.add((Date) temp.get(i));
				}
				Collections.sort(temp, Collections.reverseOrder());
				List<Date> expected = temp;
				if (expected.equals(original)) {
					logExtentReport("Dates are in descending order.");
					s_assert.assertTrue(true);
					return true;
				}
			} catch (Exception e) {
				logExtentReport("Error occured while verifying descending order of Dates");
				Assert.fail("Error occured while verifying descending order of dates.");
			}
			return false;
		}
		
		/*#############################################################
	    '    
	    '    METHOD NAME        : checkIfFileExists
	    '
	    '    DESCRIPTION        : This method will check if the filepath given is corresponding to any existing file
	    '
	    Ѡ     ARGUMENTS         : Filepath
	    '
	    '    AUTHOR             :  sujit jena
	    '
	    '    CREATION DATE      : 
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */
		
		public static boolean checkIfFileExists(String filePath) throws Exception{
			try{
				File fileToFind = new File(filePath);
				if(fileToFind.exists()){
					logExtentReport("Checking if file exists.,File found " + filePath);
					s_assert.assertTrue(true);
					return true;
				}else{
					logExtentReport("Checking if file exists.,File " + filePath + " does not exist.");
					s_assert.assertTrue(false);
					return false;
				}
			}catch(Exception e){
				logExtentReport("An error occured while searching for the file.");
				Assert.fail("An error occured while searching for the file.");
				return false;
			}
		}
		
		/*#############################################################
	    '    
	    '    METHOD NAME        : generateTextFile
	    '
	    '    DESCRIPTION        : This method will generate a text file for given path
	    '
	    Ѡ     ARGUMENTS         : Filepath
	    '
	    '    AUTHOR             :  sujit jena
	    '
	    '    CREATION DATE      : 
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */
		
		public static void generateFileRandomData(String filePath, int sizeinMB) throws Exception{
			try{
				RandomAccessFile file =new RandomAccessFile(filePath,"rw");
				try {
			           if (file.length() <= sizeinMB*1024*1024) {
			               file.setLength(sizeinMB*1024*1024);
			           }
			           System.out.println(file.length());
			       } finally { 
			           file.close();
			       }
				logExtentReport("File with Random data should get generated." + filePath);
				s_assert.assertTrue(true);
				
			}catch(Exception e){
				logExtentReport("An error occured while creating a file on given path.");
				s_assert.assertTrue(true);
				Assert.fail("An error occured while creating the text file.");
			}
		}
		
		/*#############################################################
	    '    
	    '    METHOD NAME        : waitTillInvisibility
	    '
	    '    DESCRIPTION        : This method waits untile given element becomes invisible from current page
	    '
	    Ѡ     ARGUMENTS         : WebElement, timeTowait
	    '
	    '    AUTHOR             :  sujit jena
	    '
	    '    CREATION DATE      :
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */
		
		public static void waitTillInvisibility(WebElement element) throws Exception{
			try{
				boolean checkIfVisible = true;
				
				while(checkIfVisible){
					System.out.println("Element still visible. Waiting for 5 Seconds....");
					wait5Seconds();
					checkIfVisible = isVisible(element);
				}
				
			}catch(Exception e){
				logExtentReport("An error occured while waiting for element to become invisible.");
				s_assert.assertTrue(true);
				Assert.fail("An error occured while waiting for element to become invisible.");
			}
		}
		
		/*#############################################################
	    '    
	    '    METHOD NAME        : markCheckbox
	    '
	    '    DESCRIPTION        : This method marks a checkbox as selected
	    '
	    Ѡ     ARGUMENTS         : WebElement, timeTowait
	    '
	    '    AUTHOR             :  sujit jena
	    '
	    '    CREATION DATE      : 
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */

		public static void markCheckbox(WebElement checkBox) throws Exception {
			try {

				Utility.WaitForElementPresent(checkBox, 300);
				if (!checkBox.isSelected()) {
					Click(checkBox);
				}

			} catch (Exception e) {
				logExtentReport("An error occured while clicking on the checkbox.");
				s_assert.assertTrue(false);
				Assert.fail("An error occured while clicking on the checkbox.");
			}

		}
		/*#############################################################
	    '    
	    '    METHOD NAME        : securityWarningAccept
	    '
	    '    DESCRIPTION        : This method is used to Handle Security warning pop-up using Auto-IT
	    '
	    Ѡ     ARGUMENTS         : NA
	    '
	    '    AUTHOR             : 
	    '
	    '    CREATION DATE      : 
	    '-------------------------------------------------------------------        
	    '            R E V I S I O N    H I S T O R Y
	    '------------------------------------------------------------------    
	    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
	    '------------------------------------------------------------------
	    '
	    '#############################################################       */
	    public static void securityWarningAccept() throws Exception {
	           try {
	           
	                  String autoITExecutable = System.getProperty("user.dir") + "//Execs//SecWarn_popup.exe";
	                  Utility.wait5Seconds();
	                  Process p = Runtime.getRuntime().exec(autoITExecutable);
	                   Utility.wait20Seconds();
	                  p.waitFor();
	                  logExtentReport("AutiIt Script should run successfully.");
	  				s_assert.assertTrue(true);
	           } catch (Exception e) {
	                  logExtentReport("An error occured while running the script.");
		  				s_assert.assertTrue(false);
	                  Assert.fail("An error occured while running the script.");
	           }
	    }
			/*#############################################################
		    '    
		    '    METHOD NAME        : fluentlyWaitForElement
		    '
		    '    DESCRIPTION        : This method will fluently wait for given time until the element becomes visible
		    '
		    '    ARGUMENTS         : WebElement
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      :  
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */
			
			public static boolean fluentlyWaitForElement(final WebElement Element, int timeinSeconds){
				try{
					
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						    .withTimeout(timeinSeconds, TimeUnit.SECONDS)
						    .pollingEvery(1, TimeUnit.SECONDS)
						    .ignoring(NoSuchElementException.class);

						wait.until(new Function<WebDriver, WebElement>() 
						{
						  public WebElement apply(WebDriver driver) {
						  return driver.findElement((By) Element);
						  }
						});
						
					return true;
				}catch(Exception e){
					
					return false;
				}
			}

			/*#############################################################
		    '    
		    '    METHOD NAME        : modifyCheckbox
		    '
		    '    DESCRIPTION        : This method changes current state of a checkbox.
		    '						  If it is checked it will uncheck it and if it is
		    '						  unchecked it will check it.
		    '
		    '    ARGUMENTS         : WebElement, timeTowait
		    '
		    '    AUTHOR             :  sujit jena
		    '
		    '    CREATION DATE      : 
		    '-------------------------------------------------------------------        
		    '            R E V I S I O N    H I S T O R Y
		    '------------------------------------------------------------------    
		    '    REVISED DATE    :     REVISED BY     :    CHANGE DESCRIPTION
		    '------------------------------------------------------------------
		    '
		    '#############################################################       */

			public static void modifyCheckbox(WebElement checkBox) throws Exception {
				try {
					Utility.WaitForElementPresent(checkBox, 300);
					Click(checkBox);
				} catch (Exception e) {
					logExtentReport("An error occured while clicking on the checkbox.");
	  				s_assert.assertTrue(false);
					Assert.fail("An error occured while clicking on the checkbox.");
				}
			}
		public static void WaitForAletToBePresent(int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.alertIsPresent());
		}
		public static void WaitForElementToBeVisible(WebElement element, int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
		}	
		
		/**
		 * Function to Generate the Random String generation
		 * 
		 * @param length
		 * @return : Random string
		 * @throws Exception
		 */
		public static String Random_StringGenerator(int length) throws Exception {
			String Random = "";
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			for (int i = 0; i < length; i++) {
				double a = Math.random();
				int x = (int) (a * 100);

				if (x > 25 && x < 52) {
					x = x - 26;
				} else if (x > 51 && x < 78) {
					x = x - 52;
				} else if (x > 77 && x < 99) {
					x = x - 78;
				} else {
					x = 1;
				}

				char letter = abc.charAt(x);
				Random = Random + String.valueOf(letter);
			}
			System.out.println(Random);
			return Random;

		}

		/**
		 * Method to generate Random number
		 * 
		 * @param length
		 * @return : randomNumber
		 * @throws Exception
		 */
		public static String Random_NumberGenerator(int length) throws Exception {
			double number, value;
			int len;
			number = Math.random();
			StringBuffer s = new StringBuffer(length);
			s.append("1");
			for (int k = 0; k < length; k++) {
				s.append("0");
			}
			value = Double.parseDouble(s.toString());

			number = number * value;
			int random = (int) (number);
			String randomno = Integer.toString(random);
			len = randomno.length();

			if (len < length) {
				int diff = length - len;
				StringBuffer s1 = new StringBuffer(randomno);
				for (int k = 0; k < diff; k++) {
					s1.append("0");
				}
				randomno = s1.toString();
			}
			return randomno;
		}
		
		/**
		 * Function to Generate the Random String generation
		 * 
		 * @param length
		 * @return : Random string
		 * @throws Exception
		 */
		public static String Random_StringGeneratorLowerCase(int length) throws Exception {
			String Random = "";
			String abc = "abcdefghijklmnopqrstuvwxyz";

			for (int i = 0; i < length; i++) {
				double a = Math.random();
				int x = (int) (a * 100);

				if (x > 25 && x < 52) {
					x = x - 26;
				} else if (x > 51 && x < 78) {
					x = x - 52;
				} else if (x > 77 && x < 99) {
					x = x - 78;
				} else {
					x = 1;
				}

				char letter = abc.charAt(x);
				Random = Random + String.valueOf(letter);
			}
			// System.out.println(Random);
			return Random;

		}
		/**
		 * Function to Generate the Random special character
		 * 
		 * @return : Random string
		 * @throws Exception
		 */
		public static String randomChar(){
		    char spl[] = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray();
		    Random r = new Random();
		    int Low = 0;
		    int High = spl.length + 1;
		    int Result = r.nextInt(High-Low) + Low;
		    String s=Character.toString(spl[Result]);
		    return s;

		}
		
		// 16 Assertions to verified when this method is called
		public static void assertAll() {
			s_assert.assertAll();
		}
		
		
		/***
		 * ReplaceValue
		 * 
		 * @param strMainString
		 * @param StrOldWord
		 * @param StrNewWord
		 * @return : Returns the string after replacing with new value
		 * @author : sujit
		 */
		public static String ReplaceValue(String strMainString, String StrOldWord, String StrNewWord) {
			String strOutput = strMainString.replace(StrOldWord, StrNewWord);
			return strOutput;
		}
		
		/***
		 * remove first 2 character
		 * @return : Returns the string after replacing with new value
		 * @author : sujit
		 */
		public static String removeCharacter(String header) {
			header = header.startsWith("PO") || header.startsWith("PI")|| header.startsWith("RA")|| header.startsWith("MB")|| header.startsWith("OX")|| header.startsWith("CN")||header.startsWith("AP")|| header.startsWith("DP")|| header.startsWith("GN")|| header.startsWith("IV")|| header.startsWith("PT")|| header.startsWith("SN") ? header.substring(2) : header;
			System.out.println(header);
			return header;
		}
		/***
		 * remove last 4 character
		 * @return : Returns the string after replacing with new value
		 * @author : sujit
		 */
		public static String removedigit(String header) {
			String str =header.substring(0, header.length()-4);			
			System.out.println(str);
			return str;
		}
		
		
		public static void clearCacheChrome() throws Exception{
			try {
				driver.navigate().to("chrome://settings/clearBrowserData");
				driver.findElement(By.cssSelector("*/deep/ #clearBrowsingDataConfirm")).click();
				Utility.wait5Seconds();
				  logExtentReport("Successfully clear the cookies");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logExtentReport("Unable to clear cookies");
				e.printStackTrace();
			}
		}
		
		
		
		


}
