package com.Hcl.DirectCommerce.CustomerCreation;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.DBConnection.Dbscript;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.uiActions.addCustomer;
import com.Hcl.DirectCommerce.utility.Utility;

public class create_Customer_TWO extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="create_Customer_TWO";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="create_Customer_TWO")
    public void CC02() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//=============calling method from page===================
			login.logintoApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			//==========success message===============================
			login.Verifylogoutbutton();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"customercreation", testscenario);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String message1=keyvalue.get("TabName1").toString();
			dashboard.clickonbutton(message1);
			dashboard.clickonAddicon();
			addCustomer addcustomer=PageFactory.initElements(driver,addCustomer.class);
			String source=keyvalue.get("sourcecode").toString();
			addcustomer.provideSourceCode(source);
			addcustomer.PressTAB();
			String title=keyvalue.get("title").toString();
			String FirstName=keyvalue.get("FirstName").toString();
			String LastName=keyvalue.get("LastName").toString();
			String Day=keyvalue.get("Day").toString();
			String Email=keyvalue.get("Email").toString();
			String Street=keyvalue.get("Street").toString();
			String Zipcode=keyvalue.get("Zipcode").toString();
			addcustomer.selectTitleDropdown(title);
			addcustomer.providefirstName(FirstName);
			addcustomer.providelastName(LastName);
			addcustomer.provideday(Day);
			addcustomer.provideEmail(Email);
			addcustomer.providestreetName(Street);
			addcustomer.providezipcode(Zipcode);
			addcustomer.PressTAB();
				
			String rent=keyvalue.get("rent").toString();
			String type=keyvalue.get("type").toString();
			String status=keyvalue.get("status").toString();
			String action=keyvalue.get("action").toString();
			addcustomer.selectrentDropdown(rent);
			addcustomer.selectstatusDropdown(status);
			addcustomer.selecttypeDropdown(type);
			addcustomer.selectactionDropdown(action);
			addcustomer.clickOnSubmit();
			
			addcustomer.verifycustomerSearchPage();
			Utility.wait10Seconds();
			addcustomer.ClickAddCustbtwIfavailable();
			Utility.wait10Seconds();
			String dbQuery=keyvalue.get("query").toString();
			String columnName=keyvalue.get("column1").toString();
			String columnName1=keyvalue.get("column2").toString();
			
			String fname=Dbscript.EmailValidationsSQL(dbQuery, columnName);
			String lname=Dbscript.EmailValidationsSQL(dbQuery, columnName1);
			if (fname.contains(FirstName)&&lname.contains(LastName)) {
				logExtentReport("user is created successfully");
			} else {
				logExtentReportFail("user is not created successfully");
				Assert.fail();
			}

		CustomizeReport("create_Customer_TWO", "pass", "create_Customer_TWO");
		Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("create_Customer_TWO", "fail", "create_Customer_TWO");	
		}
	}
			
    }}


