package com.Hcl.DirectCommerce.LoginPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Reset_password_navigations_negative_scenario extends TestBase{
	boolean Report=false;
   
	@Test(description="Verify Reset password navigations (negative scenario)")
    public void VerifyResetpassword_navigations_negative_scenario() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			
			//=============calling method from page===================
			login.clickedonResetPassword();
			//==========success message===============================
			login.verifyResetpasswordScreen();
	
			CustomizeReport( "Verify_Reset_password_navigations_negative_scenario", "pass", "Verify Reset password navigations (negative scenario)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "Verify_Reset_password_navigations_negative_scenario", "fail", "Verify Reset password navigations (negative scenario)");	
			}
		}
		}
	

}
