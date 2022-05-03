package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Reset_Password_which_confirm_newpassword_notMatching extends TestBase {
	boolean Report=false;
    int index=1;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify Reset Password (in which confirm new password is not matching with New password after clicking on submit button)")
    public void VerifyResetPasswordwhichconfirmnewpasswordnotMatching() throws Throwable{
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
			String confirm=Utility.Random_StringGeneratorLowerCase(6);
			//=============verify reset message================================
			String testscenario="Verify Reset Password (in which confirm new password is not matching with New password after clicking on submit button)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			login.enterResetPasswordDetails(getObject("domainname"),getObject("Activedirectoryusername"),getObject("activedirectorypass"), newpassword, confirm);
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport("Verify_Reset_Password_which_confirm_newpassword_notMatching", "pass", "Verify Reset Password (in which confirm new password is not matching with New password after clicking on submit button)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "Verify_Reset_Password_which_confirm_newpassword_notMatching", "fail", "Verify Reset Password (in which confirm new password is not matching with New password after clicking on submit button)");	
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
