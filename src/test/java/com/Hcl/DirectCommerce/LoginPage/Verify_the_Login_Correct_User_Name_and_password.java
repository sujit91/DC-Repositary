package com.Hcl.DirectCommerce.LoginPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_the_Login_Correct_User_Name_and_password extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify the Login (Correct User Name and password)")
    public void VerifytheLoginCorrectUserNameandpassword() throws Throwable{
		boolean Report=false;
	try {
		getApplicationUrl(ObjectReader.reader.getUrl());
		//===========page initialization==========================
		LoginPage login=PageFactory.initElements(driver,LoginPage.class);
		String account=Utility.getObject("Account");
		//=============calling method from page===================
		login.logintoApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
		//==========success message===============================
		login.VerifyLoginSuccessful();
		CustomizeReport( "Verify_the_Login_Correct_User_Name_and_password", "pass", "Login test with valid credentials");
		Report=true;
	} finally {
		// TODO: handle exception
		if (Report==false) {
			CustomizeReport( "Verify_the_Login_Correct_User_Name_and_password", "fail", "Login test with valid credentials");	
		}
		
	}
}


}
