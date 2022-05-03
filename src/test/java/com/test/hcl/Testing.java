package com.test.hcl;


import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.utility.Utility;

public class Testing extends TestBase{
	static boolean Report=false;
	
	public static void main(String str[]) throws IOException {
		
		try {
			
			
			CustomizeReport("Verify_Change_Password_by_passing_blank_values", "pass", "Verify Change Password (by passing blank values)");
			Report=true;
		} finally {
			if (Report==false) {
				CustomizeReport("Verify_Change_Password_by_passing_blank_values", "fail", "Verify Change Password (by passing blank values)");	
			}
		}
		}
}

