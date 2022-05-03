package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Reset_Password_bypassing_BlankValues_B extends TestBase{

	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify Reset Password (by passing blank values)")
    public void VerifyReset_Password_bypassing_BlankValues_B() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===================================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//===========provide invalid username==============================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			//=============calling method from page============================
			login.clickedonResetPassword();
			String newpassword=Utility.Random_StringGeneratorLowerCase(5);
			//=============verify reset message================================
			String testscenario="Verify Reset Password (by passing blank values)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============== Enter Domain Name, Username, Active Directory Password  and click on submit====
			login.enterResetPasswordDetails(getObject("domainname"),getObject("Activedirectoryusername"),getObject("activedirectorypass"),"","");
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage2").toString());
			//=============Enter  Domain Name, Username, Active Directory Password, New Password and click on submit==
			login.enterResetPasswordDetails(getObject("domainname"),getObject("Activedirectoryusername"),getObject("activedirectorypass"),newpassword,"");
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage2").toString());
			
			CustomizeReport("Verify_Reset_Password_bypassing_BlankValues_B", "pass", "Verify_Reset_Password_bypassing_BlankValues_B");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport("Verify_Reset_Password_bypassing_BlankValues_B", "fail", "Verify_Reset_Password_bypassing_BlankValues_B");	
			}
		}
		}
	
	  // ============this test case inserted here for not locked the user account
	@Test(description = "Verify the Login (Correct User Name and password)")
	public void Tc() throws Throwable {
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
