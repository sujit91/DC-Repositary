package com.Hcl.DirectCommerce.LoginPage;
import java.util.HashMap;

/**
 * 
 * @author sujit jena
 * date=21/5/2019
 *TC6=Verify Change Password (by passing incorrect password)
 *TC5=Verify Change Password Error Message
 *TC8=Verify Change Password (by providing the new password which doesn’t meet the required criteria)
 *TC7=Verify Change/Reset password navigations (by passing blank username)
 */
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_ChangeReset_password_navigations extends TestBase{
	boolean Report=false;
    
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify Change/Reset password navigations (without any username/password)")
    public void VerifyChangeReset_password_navigations() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//==========calling excel sheet method =================
			String testscenario="Verify Change/Reset password navigations (without any username/password)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============calling method from page===================
			login.clickedonChangePassword();
			//==========success message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			login.clickedonResetPassword();
			//======verify reset password error message============
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_ChangeReset_password_navigations", "pass", "Verify Change/Reset password navigations (without any username/password)");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport( "Verify_ChangeReset_password_navigations", "fail", "Verify Change/Reset password navigations (without any username/password)");	
			}
		}
		}
	
	//============this test case inserted here for not locked the user account
	
		@Test(description="Verify the Login (Correct User Name and password)",priority=17)
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
