package com.Hcl.DirectCommerce.LoginPage;
/**
 * 
 * @author sujit jena
 * date=21/5/2019
 *TC4=Verify the Login (Blank User Name or password)
 */
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
public class VerifytheLoginpageWithBlankField extends TestBase{
	
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify the Login (Blank User Name or password)")
    public void Tc4() throws Throwable{
		boolean Report=false;
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================/
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//=============calling method from page===================
			String account=Utility.getObject("Account");
			login.logintoApplication("","",account);
			
			String testscenario="Verify the Login (Blank User Name or password)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//==========Error message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "VerifytheLoginpageWithBlankField", "pass", "Verify the Login (Blank User Name or password)");
			Report=true;
		} finally {
			// TODO: handle exception
			if (Report==false) {
				CustomizeReport( "VerifytheLoginpageWithBlankField", "fail", "Verify the Login (Blank User Name or password)");	
			}
		}
		}
	//============this test case inserted here for not locked the user account
	
		@Test(description="Verify the Login (Correct User Name and password)")
	    public void Tc2() throws Throwable{
		
			String account=Utility.getObject("Account");
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//=============calling method from page===================
			login.logintoApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			//==========success message===============================
			login.VerifyLoginSuccessful();
	}
		

  }

