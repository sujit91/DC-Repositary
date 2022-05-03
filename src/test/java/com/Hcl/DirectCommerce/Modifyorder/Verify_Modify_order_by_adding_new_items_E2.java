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
import com.Hcl.DirectCommerce.uiActions.OrderDetails;
import com.Hcl.DirectCommerce.uiActions.customerInformation;
import com.Hcl.DirectCommerce.utility.Utility;

public class Verify_Modify_order_by_adding_new_items_E2 extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\modifyorder.xlsx";
    String testscenario="Verify_Modify_order_by_adding_new_items_E2";
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
    public void E2_OA() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available=================
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"ModifyOrder", testscenario);
			//==GET QTY AVAILABLE FOR AUTO R1 01===============
			String dbQuery=keyvalue.get("DbQuery").toString();
			String columnName=keyvalue.get("columnName").toString();		
			String qtyR101=Dbscript.EmailValidationsSQL(dbQuery, columnName);	
			//==GET QTY AVAILABLE FOR AUTO R1 02========
			String dbQuery1=keyvalue.get("DbQuery1").toString();
			String qtyR102=Dbscript.EmailValidationsSQL(dbQuery1, columnName);
			
			//=============calling method from page========
			login.logintoApplication(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword(),account);
			Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
			String tab1=keyvalue.get("Tabname").toString();
			dashboard.clickonbutton(tab1);
			
			customerInformation customerInfn=PageFactory.initElements(driver,customerInformation.class);
			String dropdown=keyvalue.get("dropdown").toString();
			dashboard.selectSearchDropdown(dropdown);			
			dashboard.provideSearchFieldValueAndClickSearch(ordernumber);
	
			CustomerSearch cust=PageFactory.initElements(driver,CustomerSearch.class);			
			cust.clickOnFirstCustomerwithoutname();
			//====execute query for unlock the order======
			String deletequery=getObject("deletequery");
			Dbscript.Executequery(deletequery);	
			CS_OrderDetails CSOrderDetails=PageFactory.initElements(driver,CS_OrderDetails.class);
			CSOrderDetails.orderoptionClick();
			CSOrderDetails.modifyoptionClick();
			
			customerInfn.clickOnOrderdetailTab();
			
			String ProductName=keyvalue.get("ProductName").toString();
			customerInfn.provideProduct(ProductName);
			OrderDetails OrderDetails=PageFactory.initElements(driver, OrderDetails.class);
			//=========price override in header=============================
			String ModifyPrice=keyvalue.get("ModifyPrice").toString();
			OrderDetails.PriceOverridePopup(ModifyPrice);
			String overrideReason=keyvalue.get("overrideReason").toString();
			OrderDetails.PriceOverrideReasonPopup(overrideReason);
			
			
			//======click on payment details===========
			customerInfn.clickOnPaymentdetailTab();
			
			//=========provide shiping details================
			customerInfn.clickOnShippingdetailTab();
			
			//===order number from the portal is========
			customerInfn.clickOnModify();
			customerInfn.clickOnSubmit();
			customerInfn.addModifyOrderComment();
			//========status validation======
			String DbstatusQuery=keyvalue.get("DbstatusQuery").toString();
			String OrderStatus=keyvalue.get("OrderStatus").toString();
			String exeQuery = Utility.ReplaceValue(DbstatusQuery, "order", ordernumber);
		    String statusColumn=keyvalue.get("OrderStatusColumn").toString();
			String status=Dbscript.EmailValidationsSQL(exeQuery, statusColumn);
			
			if (status.contains(OrderStatus)) {	
				logExtentReport("Both Status is Verified");
			} else {
				logExtentReportFail("Both Status is not Same");
			}
			
			//======source code,company,div,status,pay method==============
			
			String Companyquery=keyvalue.get("Companyquery").toString();
			String Company=keyvalue.get("Company").toString();
			String exeQuery1 = Utility.ReplaceValue(Companyquery, "order", ordernumber);
		    String CompanyColumn=keyvalue.get("CompanyColumn").toString();
			String Companyname=Dbscript.EmailValidationsSQL(exeQuery1, CompanyColumn);
			if (Companyname.contains(Company)) {	
				logExtentReport("Both Companyname is same");
			} else {
				logExtentReportFail("Both Companyname is not same");
			}
			String Divisionquery=keyvalue.get("Divisionquery").toString();
			String Division=keyvalue.get("Division").toString();
			String exeQuery2 = Utility.ReplaceValue(Divisionquery, "order", ordernumber);
		    String DivisionColumn=keyvalue.get("DivisionColumn").toString();
			String DivisionName=Dbscript.EmailValidationsSQL(exeQuery2, DivisionColumn);
			
			if (DivisionName.contains(Division)) {	
				logExtentReport("Both Division is same");
			} else {
				logExtentReportFail("Both Division is not same");
			}
			
			String SourceQuery=keyvalue.get("SourceQuery").toString();
			String Sourcecode1=keyvalue.get("Sourcecode").toString();
			String exeQuery3 = Utility.ReplaceValue(SourceQuery, "order", ordernumber);
		    String SourceColumn=keyvalue.get("SourceColumn").toString();
			String Sourcecodestatus=Dbscript.EmailValidationsSQL(exeQuery3, SourceColumn);
			if (Sourcecodestatus.contains(Sourcecode1)) {	
				logExtentReport("Both Sourcecode is same");
			} else {
				logExtentReportFail("Both Sourcecode is not same");
			}
			
			String Paymethodquery=keyvalue.get("Paymethodquery").toString();
			String Paymethod1=keyvalue.get("Paymethod1").toString();
			String exeQuery4 = Utility.ReplaceValue(Paymethodquery, "order", ordernumber);
		    String PaymethodColumn=keyvalue.get("PaymethodColumn").toString();
			String Paymethodstatus=Dbscript.EmailValidationsSQL(exeQuery4, PaymethodColumn);
			
			if (Paymethodstatus.contains(Paymethod1)) {	
				logExtentReport("Both Paymethod is same");
			} else {
				logExtentReportFail("Both Paymethod is not same");
			}
			
			String StatusQuery=keyvalue.get("StatusQuery").toString();
			String Staus=keyvalue.get("Staus").toString();
			String exeQuery5 = Utility.ReplaceValue(StatusQuery, "order", ordernumber);
		    String StatusColumn=keyvalue.get("StatusColumn").toString();
			String orderstatus=Dbscript.EmailValidationsSQL(exeQuery5, StatusColumn);
			
			if (orderstatus.contains(Staus)) {	
				logExtentReport("Both Staus is same");
			} else {
				logExtentReportFail("Both Staus is not same");
			}
			
		    CustomizeReport("Verify_Modify_order_by_adding_new_items_E2", "pass", "Verify_Modify_order_by_adding_new_items_E2");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Verify_Modify_order_by_adding_new_items_E2", "fail", "Verify_Modify_order_by_adding_new_items_E2");	
		}
	}
			
    }}




