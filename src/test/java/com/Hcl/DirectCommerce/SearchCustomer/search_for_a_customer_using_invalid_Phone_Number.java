package com.Hcl.DirectCommerce.SearchCustomer;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class search_for_a_customer_using_invalid_Phone_Number extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="search_for_a_customer_using_invalid_Phone_Number";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="search_for_a_customer_using_invalid_Phone_Number")
    public void CS_013() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//=============calling method from page===================
			login.logintoApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"SearchCustomer", testscenario);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String tab1=keyvalue.get("tab1").toString();
			dashboard.clickonbutton(tab1);
			String dropdown=keyvalue.get("dropdown").toString();
			dashboard.selectSearchDropdown(dropdown);
			String dropdownvalue=keyvalue.get("dropdownvalue").toString();
			dashboard.provideSearchFieldValueAndClickSearch(dropdownvalue);
			String Errormessage=keyvalue.get("Errormessage").toString();
			dashboard.verifyDifferentHomepage(Errormessage);
		    CustomizeReport("search_for_a_customer_using_invalid_Phone_Number", "pass", "search_for_a_customer_using_invalid_Phone_Number");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("search_for_a_customer_using_invalid_Phone_Number", "fail", "search_for_a_customer_using_invalid_Phone_Number");	
		}
	}
			
    }}
