package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class VerifyResetPassword extends TestBase{
	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
    
	@Test(description="Verify Reset Password (by providing the new password which doesn’t meet the required criteria)")
    public void Tc16() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			//=============calling method from page===================
			login.clickedonResetPassword();
			String newpassword=Utility.Random_StringGeneratorLowerCase(5);
			//==========verify reset message=======================
			String testscenario="Verify_Change_Password_by_providing_the_new_password";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			login.enterResetPasswordDetails(getObject("domainname"), getObject("Activedirectoryusername"), getObject("activedirectorypass"), newpassword, newpassword);
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport("VerifyResetPassword", "pass", "Verify Reset Password (by providing the new password which doesn’t meet the required criteria)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "VerifyResetPassword", "fail", "Verify Reset Password (by providing the new password which doesn’t meet the required criteria)");	
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
