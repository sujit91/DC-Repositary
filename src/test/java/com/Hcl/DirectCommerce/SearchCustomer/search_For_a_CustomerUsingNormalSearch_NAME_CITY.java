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

public class search_For_a_CustomerUsingNormalSearch_NAME_CITY extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\CustomerSearch\\CustomerSearch.xlsx";
    String testscenario="search_For_a_CustomerUsingNormalSearch_NAME_CITY";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="search_For_a_CustomerUsingNormalSearch_NAME_CITY")
    public void CS_007() throws Throwable{
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
			String Fname=keyvalue.get("Fname,city").toString();
			String tab1=keyvalue.get("tab1").toString();
			dashboard.clickonbutton(tab1);
			dashboard.provideSearchFieldValueAndClickSearch(Fname);
			String Errormessage=keyvalue.get("Errormessage").toString();
			dashboard.verifyDifferentHomepage(Errormessage);
			
		CustomizeReport("search_For_a_CustomerUsingNormalSearch_NAME_CITY", "pass", "search_For_a_CustomerUsingNormalSearch_NAME_CITY");
		Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("search_For_a_CustomerUsingNormalSearch_NAME_CITY", "fail", "search_For_a_CustomerUsingNormalSearch_NAME_CITY");	
		}
	}
			
    }}
