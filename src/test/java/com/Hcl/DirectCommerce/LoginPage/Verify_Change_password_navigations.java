package com.Hcl.DirectCommerce.LoginPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Change_password_navigations extends TestBase{
	
	boolean Report=false;
	@Test(description="Verify Change password navigations")
    public void VerifyChange_password_navigations() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);//=============calling method from page===================
			login.clickedonChangePassword();
			//==========success message===============================
			login.verifyChangepasswordScreen();
	
			CustomizeReport( "Verify_Change_password_navigations", "pass", "Verify Change password navigations (negative scenario)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "Verify_Change_password_navigations", "fail", "Verify Change password navigations (negative scenario)");	
			}
		}
		}
	
	
}
