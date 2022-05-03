package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Reset_Password_by_passing_username_which_doestnot_exist extends TestBase {

	boolean Report=false;
    int index=1;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify Reset Password (by passing username which doestnot exist)")
    public void VerifyReset_Password_by_passing_username_which_doestnot_exist() throws Throwable{
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization====================================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			//===========provide invalide username==============================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword("dweew", ObjectReader.reader.getPassword(),account);
			//=============calling method from page=============================
			login.clickedonResetPassword();
			String newpassword=Utility.Random_StringGeneratorLowerCase(5);
			//=============verify reset message=================================
			String testscenario="Verify Reset Password (by passing username which doestnot exist)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			login.enterResetPasswordDetails(getObject("domainname"), getObject("Activedirectoryusername"), getObject("activedirectorypass"), newpassword, newpassword);
			login.clickOnSubmit();
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_Reset_Password_by_passing_username_which_doestnot_exist", "pass", "Verify Reset Password (by passing username which doestnot exist)");
			Report=true;
		} finally {
			if (Report==false) {
			CustomizeReport( "Verify_Reset_Password_by_passing_username_which_doestnot_exist", "fail", "Verify Reset Password (by passing username which doestnot exist)");	
			}
		}
		}
	
	
	//============this test case inserted here for not lock the user account
	
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
