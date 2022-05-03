package com.Hcl.DirectCommerce.LoginPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;

public class VerifytheLoginPage extends TestBase{
	
	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify the Login Page view of Direct Commerce Web Application")
    public void Tc1() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//=======verify home page=======================
			login.verifyHomePage();
			
			CustomizeReport( "VerifytheLoginPage", "pass", "Verify the Login Page view of Direct Commerce Web Application");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "VerifytheLoginPage", "fail", "Verify the Login Page view of Direct Commerce Web Application");	
			}
		}
		}
	
}
