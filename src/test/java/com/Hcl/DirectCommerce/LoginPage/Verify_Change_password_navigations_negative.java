package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

@Test
public class Verify_Change_password_navigations_negative extends TestBase {
	
	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	public void VerifyChange_password_navigations_negative() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			
			//=============calling method from page===================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), "",account);
			//==========calling excel sheet method =================
			String testscenario="Verify Change password navigations (negative scenario)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============calling method from page===================
			login.clickedonChangePassword();
			//==========success message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_Change_password_navigations_negative", "pass", "Verify Change password navigations (negative scenario)");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport( "Verify_Change_password_navigations_negative", "fail", "Verify Change password navigations (negative scenario)");	
			}
		}
		}
	         //============this test case inserted here for not locked the user account
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
