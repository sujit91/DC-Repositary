package com.Hcl.DirectCommerce.Orders_with_backorder_items;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Hcl.DirectCommerce.DBConnection.Dbscript;
import com.Hcl.DirectCommerce.browserConfiguration.config.ObjectReader;
import com.Hcl.DirectCommerce.excelReader.Excel_Reader;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.CustomerSearch;
import com.Hcl.DirectCommerce.uiActions.Dashboard;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.Hcl.DirectCommerce.uiActions.OrderDetails;
import com.Hcl.DirectCommerce.uiActions.customerInformation;
import com.Hcl.DirectCommerce.utility.Utility;

public class Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\OrderManagement\\Orders_with_backorder_items.xlsx";
    String testscenario="Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder")
    public void T2_OUTOFSTOCK_OA() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available============
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Orders_with_backorder_items", testscenario);
			String dbQuery=keyvalue.get("DbQuery").toString();
			String columnName=keyvalue.get("columnName").toString();		
			String qty=Dbscript.EmailValidationsSQL(dbQuery, columnName);		
			//=============calling method from page========
			login.logintoApplication(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword(),account);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String tab1=keyvalue.get("Tabname").toString();
			dashboard.clickonbutton(tab1);
			String customername=keyvalue.get("CustomerName").toString();
			dashboard.provideSearchFieldValueAndClickSearch(customername);
			CustomerSearch cust=PageFactory.initElements(driver,CustomerSearch.class);			
			cust.clickOnFirstCustomer(customername);
			customerInformation customerInfn=PageFactory.initElements(driver,customerInformation.class);
			customerInfn.clickOnOrderdetailTab();
			String Sourcecode=keyvalue.get("Sourcecode").toString();
			customerInfn.provideSourceCode(Sourcecode);
			String ProductName=keyvalue.get("ProductName").toString();
			customerInfn.provideProduct(ProductName);
			
			//=====product availability popup validation==========
			OrderDetails OrderDetails=PageFactory.initElements(driver,OrderDetails.class);
			String availavleqty=OrderDetails.productAvailabilityPopup();
			
			//====verify status=======
			String verifyStatus=keyvalue.get("verifyStatus").toString();
			OrderDetails.verifyStatus(verifyStatus);
			//===modify the qty======
			OrderDetails.modifyQuantity("5");
		    OrderDetails.productAvailabilityPopupNo();
		    
			
		    CustomizeReport("Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder", "pass", "Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder", "fail", "Place_a_phone_order_for_T2_Out_of_stock_item_and_override_the_quantity_and_select_No_to_backorder");	
		}
	}
			
    }}



