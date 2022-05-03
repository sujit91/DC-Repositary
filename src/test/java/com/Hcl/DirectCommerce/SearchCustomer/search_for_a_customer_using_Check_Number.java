package com.Hcl.DirectCommerce.SearchCustomer;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.DBConnection.Dbscript;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.CustomerSearch;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class search_for_a_customer_using_Check_Number extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="search_for_a_customer_using_Check_Number";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="search_for_a_customer_using_Check_Number")
    public void CS_022() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch db details from sql server for a auto po number=======
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"SearchCustomer", testscenario);
			String dbQuery=keyvalue.get("dbQuery").toString();
			String columnName=keyvalue.get("columnName").toString();		
			String AutoPonumber=Dbscript.EmailValidationsSQL(dbQuery, columnName);	
			//===remove first 2 character=======================
			String autoPono=Utility.removeCharacter(AutoPonumber).trim();		
			String columnName3=keyvalue.get("columnName3").toString();
			String dbQuery2=keyvalue.get("dbQuery2").toString();
			String order=Dbscript.EmailValidationsSQL(dbQuery2, columnName3);	
			String ordernumber=Utility.removedigit(order);
			String dbQuery3=keyvalue.get("dbQuery3").toString();
			String dbQuery4=keyvalue.get("dbQuery4").toString();
			String columnName1=keyvalue.get("columnName1").toString();
			String columnName2=keyvalue.get("columnName2").toString();
			String exeQuery = Utility.ReplaceValue(dbQuery3, "order", ordernumber);
			String exeQuery2 = Utility.ReplaceValue(dbQuery4, "order", ordernumber);		
			String NAME=Dbscript.EmailValidationsSQL(exeQuery, columnName1);
			String ZIP=Dbscript.EmailValidationsSQL(exeQuery2, columnName2);
			//=============calling method from page===================
			login.logintoApplication(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword(),account);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String tab1=keyvalue.get("tab1").toString();
			dashboard.clickonbutton(tab1);
			String dropdown=keyvalue.get("dropdown").toString();
			dashboard.selectSearchDropdown(dropdown);
			dashboard.provideSearchFieldValueAndClickSearch(autoPono);
			CustomerSearch cust=PageFactory.initElements(driver,CustomerSearch.class);
			Utility.wait5Seconds();
			cust.validateCustomer(NAME.trim());
			cust.validateCustomer(ZIP.trim());
		    CustomizeReport("search_for_a_customer_using_Check_Number", "pass", "search_for_a_customer_using_Check_Number");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("search_for_a_customer_using_Check_Number", "fail", "search_for_a_customer_using_Check_Number");	
		}
	}
			
    }}
