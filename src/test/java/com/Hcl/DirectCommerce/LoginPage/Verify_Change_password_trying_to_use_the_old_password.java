package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Change_password_trying_to_use_the_old_password extends TestBase {
	boolean Report = false;
	int index = 1;
	String sFilePath = System.getProperty("user.dir") + "\\TestData\\Login\\Login.xlsx";
	@Test(description = "Verify Change password ( by trying to use the old password)")
	public void VerifyChange_password_trying_to_use_the_old_password() throws Throwable {
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			// ===========page initialization===================================
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			String account=Utility.getObject("Account");
			login.giveUsernamePassword(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword(),account);
			// =============calling method from page===================
			login.clickedonChangePassword();

			login.enterChangePasswordforErrormesage(ObjectReader.reader.getPassword(),
					ObjectReader.reader.getPassword(), ObjectReader.reader.getPassword());
			String testscenario = "Verify Change Password (in which confirm new password is not matching with New password before clicking on submit button )";
			login.clickOnSubmit();
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue = (HashMap<Object, Object>) Excel_Reader.getrowvalue(sFilePath, "Sheet1", testscenario);
			// =============Verify Error message===============
			login.VerifychangePasswordErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_Change_password_trying_to_use_the_old_password", "pass",
					"Verify Change password ( by trying to use the old password) ");
			Report = true;
		} finally {
			if (Report == false) {
				CustomizeReport( "Verify_Change_password_trying_to_use_the_old_password", "fail",
						"Verify Change password ( by trying to use the old password) ");
			}
		}
	}

	// ============this test case inserted here for not locked the user account
	@Test(description = "Verify the Login (Correct User Name and password)")
	public void Tc2() throws Throwable {
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
