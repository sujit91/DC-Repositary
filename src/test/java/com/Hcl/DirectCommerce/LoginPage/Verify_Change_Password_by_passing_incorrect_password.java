package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

@Test
public class Verify_Change_Password_by_passing_incorrect_password extends TestBase{
	boolean Report=false;
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	public void VerifyChangePasswordbypassingincorrectpassword() throws Throwable{
		try {
			Utility.wait5Seconds();
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String testscenario="Verify_Change_Password_by_passing_incorrect_password";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============calling method from page===================
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), "gffffyfy",account);
			login.clickedonChangePassword();
			String newpassword=Utility.Random_StringGenerator(3)+Utility.Random_StringGeneratorLowerCase(5)+Utility.Random_NumberGenerator(5);
			System.out.println(newpassword);
			login.enterChangePasswordDetails("dfddgddgd", newpassword,newpassword);
			//==========validate Error message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_Change_Password_by_passing_incorrect_password", "pass", "Verify Change Password (by passing incorrect password)");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport( "Verify_Change_Password_by_passing_incorrect_password", "fail", "Verify Change Password (by passing incorrect password)");	
			}
		}
		}
	
	//============this test case inserted here for not locked the user account
	
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
