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

public class Search_for_a_customer_using_invalid_ZipAddress extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="Search_for_a_customer_using_invalid_ZipAddress";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="Search_for_a_customer_using_invalid_ZipAddress")
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
		    CustomizeReport("Search_for_a_customer_using_invalid_ZipAddress", "pass", "Search_for_a_customer_using_invalid_ZipAddress");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Search_for_a_customer_using_invalid_ZipAddress", "fail", "Search_for_a_customer_using_invalid_ZipAddress");	
		}
	}
			
    }}
