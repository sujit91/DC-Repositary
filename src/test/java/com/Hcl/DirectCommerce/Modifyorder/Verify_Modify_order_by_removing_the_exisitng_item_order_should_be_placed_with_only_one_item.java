package com.Hcl.DirectCommerce.Modifyorder;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.ApiMethod.reusableAPIMethodsPage;
import com.Hcl.DirectCommerce.DBConnection.Dbscript;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.CS_OrderDetails;
import com.Hcl.DirectCommerce.uiActions.CustomerSearch;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.uiActions.customerInformation;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\modifyorder.xlsx";
    String testscenario="Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    String ordernumber="0";
   
    @Test(priority=0)
    public void returnOrder() throws Exception
    {
       System.out.println("Returning Method");
       reusableAPIMethodsPage APIMethods = PageFactory.initElements(driver, reusableAPIMethodsPage.class);	
	    ordernumber=APIMethods.retrurnordernumber();
		System.out.println(ordernumber);
    }
    
    @Test(priority=1)
    public void R1_OA() throws Throwable{
		boolean Report=false;
		try {		
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available=======
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"ModifyOrder", testscenario);
			String dbQuery=keyvalue.get("DbQuery").toString();
			String columnName=keyvalue.get("columnName").toString();
			
			
			
			//=============calling method from page===================
			login.logintoApplication(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword(),account);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String tab1=keyvalue.get("Tabname").toString();
			
			
			dashboard.clickonbutton(tab1);
			//String customername=keyvalue.get("CustomerName").toString();
			String dropdown=keyvalue.get("dropdown").toString();
			dashboard.selectSearchDropdown(dropdown);			
			dashboard.provideSearchFieldValueAndClickSearch(ordernumber);
	
			CustomerSearch cust=PageFactory.initElements(driver,CustomerSearch.class);			
			cust.clickOnFirstCustomerwithoutname();
			customerInformation customerInfn=PageFactory.initElements(driver,customerInformation.class);
			//====execute query for unlock the order======
			String deletequery=getObject("deletequery");
			Dbscript.Executequery(deletequery);	
			CS_OrderDetails CSOrderDetails=PageFactory.initElements(driver,CS_OrderDetails.class);
			CSOrderDetails.orderoptionClick();
			CSOrderDetails.modifyoptionClick();
			
			
			customerInfn.clickOnOrderdetailTab();
			String ProductName=keyvalue.get("ProductName").toString();
			//customerInfn.provideProduct(ProductName);
			
		    customerInfn.deleteCustomer();
			//======click on payment details====
			customerInfn.clickOnPaymentdetailTab();
			//===provide shiping details=======
			customerInfn.clickOnShippingdetailTab();
			//customerInfn.clickOnEndOrder();
			//===order number from the portal is========
			//String ordernumber1=customerInfn.getOrderNumber();
			customerInfn.clickOnModify();
			String Errormessage=keyvalue.get("Errormessage").toString();
			customerInfn.verifymessage(Errormessage);
			
			
			
		    CustomizeReport("Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item", "pass", "Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item", "fail", "Verify_Modify_order_by_removing_the_exisitng_item_order_should_be_placed_with_only_one_item");	
		}
	}
			
    }}

