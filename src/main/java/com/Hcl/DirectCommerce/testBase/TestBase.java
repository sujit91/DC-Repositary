package com.Hcl.DirectCommerce.testBase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.browserConfiguration.config.PropertyReader;
import com.Hcl.DirectCommerce.browserconfig.BrowserType;
import com.Hcl.DirectCommerce.browserconfig.ChromeBrowser;
import com.Hcl.DirectCommerce.browserconfig.FirefoxBrowser;
import com.Hcl.DirectCommerce.browserconfig.IExploreBrowser;
import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.utility.ExtentManager;
import com.Hcl.DirectCommerce.utility.JavaScriptHelper;
import com.Hcl.DirectCommerce.utility.ResourceHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
/**
 * 
 * @author sujit jena
 *
 */
public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectery;
	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		extent = ExtentManager.getInstance(this.getClass().getSimpleName());
	}
	
	/*@BeforeTest
	public void beforeTest() throws Exception{
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResourcePath("\\ScreenShot"));
		setUpDriver(ObjectReader.reader .getBrowserType());
		driver.get("chrome://settings/clearBrowserData");
		 Thread.sleep(10000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		test = extent.createTest(getClass().getSimpleName());
	}*/
	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws Exception{
		extent = ExtentManager.getInstance(this.getClass().getSimpleName());
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResourcePath("\\ScreenShot"));
		setUpDriver(ObjectReader.reader .getBrowserType());
		/*driver.get("chrome://settings/clearBrowserData");
		 Thread.sleep(10000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);*/
		test = extent.createTest(getClass().getSimpleName());
		test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started***************");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		}
		/*else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+" is pass");
			String imagePath = captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		}*/
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************"+result.getName()+"Finished***************");
		extent.flush();
		if(driver!=null){
			driver.close();
		}
	}
	
	/*@AfterTest
	public void afterTest(ITestResult result) throws Exception{
		if(driver!=null){
			driver.quit();
		}
	}*/
	
	public WebDriver getBrowserObject(BrowserType btype) throws Exception{
		
		try{
			switch(btype){
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);
				
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver not Found: "+btype.name());
			}
		}
		catch(Exception e){
			log.info(e.getStackTrace());
			throw e;
		}
	}
	
	public void setUpDriver(BrowserType btype) throws Exception{
		driver = getBrowserObject(btype);
		log.info("Initialize Web driver: "+driver.hashCode());
		/*WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);*/
		driver.manage().window().maximize();
	}
	
	public String captureScreen(String fileName, WebDriver driver){
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectery+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}

	
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		new JavaScriptHelper(driver).zoomInBy60Percentage();
		 String screen = captureScreen("", driver);
		 new JavaScriptHelper(driver).zoomInBy100Percentage();
		 try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	public static void logExtentReportFail(String s1){
		test.log(Status.FAIL, s1);
	}
	
	public void getApplicationUrl(String url){
		driver.get(url);
		logExtentReport("navigating to ..."+url);
	}

	public static String CustomizeReport1(String testCaseName,String testCaseStatus,String description) throws IOException {
		int index = 1;
		String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String userDirector = System.getProperty("user.dir");
		String resultFile = userDirector + "\\CustomizeReport\\TestReport.html";	
		File file = new File(resultFile);
		System.out.println(file.exists());
		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>TestAutomationReport</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>TestAutomationReport</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (index == 1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write("<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='1'>"+testCaseName+"</font></b></td>");
			/*bw1.write("</tr>");
			bw1.write("<tr>");*/
			bw1.write("<td colspan='2' align='left'><b><font color='#000000' face='Tahoma' size='2'>Execution Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>" + start + " </font></td>");
			bw1.write("</tr>");	
			bw1.write("<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>TestCase Description </font></b></td>");
			bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
		bw1.write("<tr>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + description + "</font></b></td>" + "\n");
		if (testCaseStatus=="pass") {
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
		} else {
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
		}
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();
		return testCaseStatus;
	}
	
	
	
	
public static String CustomizeReport(String testCaseName,String testCaseStatus,String description) throws IOException {
		String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String userDirector = System.getProperty("user.dir");
		String resultFile = userDirector + "\\CustomizeReport\\TestReport.html";	
		File file = new File(resultFile);
		System.out.println(file.exists());
		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>");
			bw.write("<head><title>TestAutomationReport</title>");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<u><h1 align='center'>TestAutomationReport</h1></u>" + "\n");		
			bw.write("<table border=0 cellspacing=1 cellpadding=1>\n");
			bw.write("<tr>\n");
			bw.write("<table border=1 cellspacing=1 cellpadding=1 >\n");
			bw.write("<tr>");
			bw.write("<td align=left bgcolor=#99FF33><FONT COLOR=#001933 FACE=Arial SIZE=2><b>ScriptName</b></td>\n");
			bw.write("<td width=25% align= center  bgcolor=#99FF33><FONT COLOR=#001933 FACE= Arial  SIZE=2><b>TestCase Description</b></td>\n");
			bw.write("<td width=25% align=left bgcolor=#99FF33><FONT COLOR=#001933 FACE=Arial SIZE=2><b>Execution Start Time</b></td>\n");		
			bw.write("<td  align= center  bgcolor=#99FF33><FONT COLOR=#001933 FACE= Arial  SIZE=2><b>Result</b></td>\n");
			bw.write("</tr>" + "\n");
			bw.flush();
			bw.close();
		}
		    BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		bw1.write("<td  width=100% align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>"+testCaseName+"</b></td>\n");
		bw1.write("<td  width=100% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>" + description + "</b></td>\n");
		bw1.write("<td  width=25% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>" + start + "</b></td>\n");
		if (testCaseStatus=="pass") {
		bw1.write("<td  align= center  bgcolor=#33FF33><FONT COLOR=#001933 FACE= Arial  SIZE=2><b>" + testCaseStatus + "</b></td>\n");
		} else {
		bw1.write("<td  align= center  bgcolor=#FF3333><FONT COLOR=#001933 FACE= Arial  SIZE=2><b>" + testCaseStatus + "</b></td>\n");
		}
		bw1.write("</tr>");
		bw1.flush();
		bw1.close();
		return testCaseStatus;
	}
	
	
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
	
}