package com.Hcl.DirectCommerce.utility;

import java.util.Calendar;
import java.util.Date;

import org.testng.TestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author sujit jena
 *
 */
public class ExtentManager  {
	private static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentReports getInstance(String filePrefix){
		if(extent == null){
			Calendar cal = Calendar.getInstance();   
			   Date time=cal.getTime();
			   String timestamp=time.toString();
			   String systime=timestamp.replace(":", "-");
			   String location = ResourceHelper.getResourcePath("\\ExtentReport\\"+filePrefix+ " " + systime + "_" +TestNG.DEFAULT_OUTPUTDIR+".html");
			   return createInstance(location);
		}
		else{
			return extent;
		}
	}
	
	public static ExtentReports createInstance(String fileName){
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	// Log Test status, Test name and Test details
		public static void logStatus(LogStatus testStatus, String testStepName, String testDetails) {

			// Log test status
			test.log(testStatus, testStepName, testDetails);
		}
	
	/*public static ExtentReports extent;
	public static ExtentTest test;

	// Start Test Case
	public void startTest(String testCaseName) {

		Create ExtentReports object passing location and report name as argument. Notice a new Result Log folder will be created inside project and the report name will be TestReport.html
		Calendar cal = Calendar.getInstance();   
		   Date time=cal.getTime();
		   String timestamp=time.toString();
		   String systime=timestamp.replace(":", "-");
		String location = ResourceHelper.getResourcePath("\\ExtentReport\\"+testCaseName+ " " + systime + "_" +org.testng.internal.Constants.PROP_OUTPUT_DIR+".html");
		extent = new ExtentReports(location);

		// Add details to our report
		extent.addSystemInfo("Selenium Version", "3.0.1").addSystemInfo("Environment", "QA");

		// Create ExtentTest passing test case name and description
		test = extent.startTest(testCaseName, "Our First Test Report");
	}

	// Log Test status, Test name and Test details
	public void logStatus(LogStatus testStatus, String testStepName, String testDetails) {

		// Log test status
		test.log(testStatus, testStepName, testDetails);
	}

	// Capture screenshot and log into report
	public void screenshotLog(LogStatus logStatus, String testStepName, String screenShotPath) {

		// Attach screenshots
		test.log(logStatus, testStepName + test.addScreenCapture(screenShotPath));
	}

	// End Test Case
	public void endTest() {

		// End test
		extent.endTest(test);
		extent.flush();
		extent.close();
	}*/
}


