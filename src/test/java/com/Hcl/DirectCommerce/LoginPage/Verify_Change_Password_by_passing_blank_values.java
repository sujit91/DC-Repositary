package com.Hcl.DirectCommerce.LoginPage;
//***********************************************************************
//modified by       : sujit jena
//date=02-04-2020
//***********************************************************************

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Change_Password_by_passing_blank_values extends TestBase{
	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test
	public void VerifyChangePasswordbypassingblankvalues() throws Throwable{
		try {
			Utility.wait5Seconds();
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), "gffffyfy",account);
			login.clickedonChangePassword();
			Utility.wait5Seconds();
			login.clickOnchangePassword();
			String testscenario="Verify_Change_Password_by_passing_blank_values";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//==========validate Error message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			//==========validate Error message for without confirm and new password===============
			login.enterChangePasswordDetails(ObjectReader.reader.getPassword(), "","");
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage2").toString());
			//============verify by passing current and new password==============
			String newpassword=Utility.Random_StringGenerator(3)+Utility.Random_StringGeneratorLowerCase(5)+Utility.Random_NumberGenerator(5);
			login.enterChangePasswordDetails(ObjectReader.reader.getPassword(), newpassword,"");
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage2").toString());
			
			CustomizeReport("Verify_Change_Password_by_passing_blank_values", "pass", "Verify Change Password (by passing blank values)");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport("Verify_Change_Password_by_passing_blank_values", "fail", "Verify Change Password (by passing blank values)");	
			}
		}
		}
	
	//============this test case inserted here for not locked the user account
	
	@Test
	public void login() throws Throwable{
	
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
