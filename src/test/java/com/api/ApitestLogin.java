package com.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.ApiMethod.reusableAPIMethodsPage;
import com.Hcl.DirectCommerce.DBConnection.Dbscript;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.CustomerSearch;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApitestLogin extends TestBase{
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="create_Customer_ONE")
    public void CC01() throws Throwable{
		boolean Report=false;
		try {		
			reusableAPIMethodsPage APIMethods = PageFactory.initElements(driver, reusableAPIMethodsPage.class);	
			String ordernumber=APIMethods.retrurnordernumber();
			System.out.println(ordernumber);
		CustomizeReport("ApitestLogin", "pass", "ApitestLogin");
		Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("ApitestLogin", "fail", "ApitestLogin");
		}
	}
    }
}
