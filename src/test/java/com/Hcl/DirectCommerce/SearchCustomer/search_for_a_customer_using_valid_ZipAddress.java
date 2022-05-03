package com.Hcl.DirectCommerce.SearchCustomer;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.CustomerSearch;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class search_for_a_customer_using_valid_ZipAddress extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="search_for_a_customer_using_valid_ZipAddress";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="search_for_a_customer_using_valid_ZipAddress")
    public void CS_012() throws Throwable{
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
			CustomerSearch cust=PageFactory.initElements(driver,CustomerSearch.class);
			cust.validateCustomer(dropdownvalue);		
		    CustomizeReport("search_for_a_customer_using_valid_ZipAddress", "pass", "search_for_a_customer_using_valid_ZipAddress");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("search_for_a_customer_using_valid_ZipAddress", "fail", "search_for_a_customer_using_valid_ZipAddress");	
		}
	}
			
    }}
