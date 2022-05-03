package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Change_Password_new_password_is_not_matching extends TestBase {
	 String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	boolean Report=false;
    int index=1;
	@Test(description="Verify Change Password (in which confirm new password is not matching with New password before clicking on submit button )")
	public void VerifyChange_Password_new_password_is_not_matching() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);		
			//=============calling method from page===================
			login.clickedonChangePassword();		
			String newpassword=Utility.Random_StringGenerator(3)+Utility.Random_StringGeneratorLowerCase(5)+Utility.Random_NumberGenerator(5);
			login.enterChangePasswordforErrormesage(ObjectReader.reader.getPassword(), newpassword, "deeeef");
			String testscenario="Verify Change Password (in which confirm new password is not matching with New password before clicking on submit button )";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============Verify Error message===============
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_Change_Password_new_password_is_not_matching", "pass", "Verify Change Password (in which confirm new password is not matching with New password before clicking on submit button )");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "Verify_Change_Password_new_password_is_not_matching", "fail", "Verify Change Password (in which confirm new password is not matching with New password before clicking on submit button )");	
			}
		}
		}
	
		
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
