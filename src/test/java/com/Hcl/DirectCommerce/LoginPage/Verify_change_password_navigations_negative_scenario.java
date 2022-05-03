package com.Hcl.DirectCommerce.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

@Test
public class Verify_change_password_navigations_negative_scenario extends TestBase{
	boolean Report=false;
    int index=1;
	
	public void Verifychange_password_navigations_negative_scenario() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			
			//=============calling method from page===================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), "",account);
			//=============calling method from page===================
			login.clickedonResetPassword();
			//==========success message===============================
			login.verifyResetpasswordScreen();
			CustomizeReport( "Verify_change_password_navigations_negative_scenario", "pass", "Verify_change_password_navigations_negative_scenario");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport( "Verify_change_password_navigations_negative_scenario", "fail", "Verify_change_password_navigations_negative_scenario");	
			}
		}
		}

}
