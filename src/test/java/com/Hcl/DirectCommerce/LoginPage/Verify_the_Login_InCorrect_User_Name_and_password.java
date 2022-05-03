package com.Hcl.DirectCommerce.LoginPage;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_the_Login_InCorrect_User_Name_and_password extends TestBase{

    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Login\\Login.xlsx";
	@Test(description="Verify the Login (InCorrect User Name and password)")
    public void VerifytheLoginInCorrectUserNameAndpassword() throws Throwable{
		boolean Report=false;
		try {
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String testscenario="Verify the Login (InCorrect User Name and password)";
			HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			//=============calling method from page===================
			String account=Utility.getObject("Account");
			login.logintoApplication("abc", "abc",account);
			//==========Error message===============================
			login.VerifyErrorMessage(keyvalue.get("ErrorMessage1").toString());
			CustomizeReport( "Verify_the_Login_InCorrect_User_Name_and_password", "pass", "Verify the Login (InCorrect User Name and password)");
			Report=true;
		} finally {
			// TODO: handle exception
			if (Report==false) {
				CustomizeReport( "Verify_the_Login_InCorrect_User_Name_and_password", "fail", "Verify the Login (InCorrect User Name and password)");	
			}
		}
		}  
}
