package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Reset_Password_bypassing_BlankValues_A extends TestBase {
	
	boolean Report=false;
    int index=1;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify Reset Password (by passing blank values)",priority=20)
    public void VerifyReset_Password_bypassing_BlankValues_A() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===================================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//===========provide invalid username==============================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			//=============calling method from page============================
			login.clickedonResetPassword();
			//=============verify reset message================================
			String testscenario="Verify Reset Password (by passing blank values)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//login.enterResetPasswordDetails(getObject("domainname"),getObject("Activedirectoryusername"),getObject("activedirectorypass"), ObjectReader.reader.getPassword(), ObjectReader.reader.getPassword());
			
			login.enterResetPasswordDetails("","","", "", "");
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			//=========Enter Domain Name and click on submit =======================
			login.enterResetPasswordDetails(getObject("domainname"),"","","","");
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage2").toString());
			//===============Enter Domain Name,  Active Directory Username and click on submit====
			login.enterResetPasswordDetails(getObject("domainname"),getObject("Activedirectoryusername"),"","","");
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage2").toString());
			CustomizeReport("Verify_Reset_Password_bypassing_BlankValues_A", "pass", "Verify Reset Password (by passing blank values)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport("Verify_Reset_Password_bypassing_BlankValues_A", "fail", "Verify Reset Password (by passing blank values)");	
			}
		}
		}
	
	     // ============this test case inserted here for not locked the user account
			@Test(description = "Verify the Login (Correct User Name and password)")
			public void Tc2() throws Throwable {
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
