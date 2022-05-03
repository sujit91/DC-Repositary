package com.Hcl.DirectCommerce.Order_with_Credit_Card_and_Gift_Certificate;

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
import com.Hcl.DirectCommerce.uiActions.PaymentDetails;
import com.Hcl.DirectCommerce.uiActions.customerInformation;
import com.Hcl.DirectCommerce.utility.Utility;

public class Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\Order_with_Credit_Card_and_Gift_Certificate.xlsx";
    String testscenario="Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate")
    public void T2_OA() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available=======
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"Sheet1", testscenario);
			String dbQuery=keyvalue.get("DbQuery").toString();
			String columnName=keyvalue.get("columnName").toString();
			
			String qty=Dbscript.EmailValidationsSQL(dbQuery, columnName);	
			
			//=============calling method from page===================
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
			
			
			//======click on payment details====
			customerInfn.clickOnPaymentdetailTab();
			String Paymethod=keyvalue.get("Paymethod").toString();
			customerInfn.selectPaymethod(Paymethod);
			
			
			//===select additional tender========
			
			PaymentDetails PaymentDetails=PageFactory.initElements(driver, PaymentDetails.class);
			String Tendertype=keyvalue.get("Tendertype").toString();
			String Coupon=keyvalue.get("Coupon").toString();
			PaymentDetails.selectTenderType(Tendertype);
			PaymentDetails.ProvidegiftCoupon(Coupon);
			
			//===provide shiping details=======
			customerInfn.clickOnShippingdetailTab();
			String ShippingAddress=keyvalue.get("ShippingAddress").toString();
			customerInfn.selectshipMethod(ShippingAddress);
			customerInfn.clickOnEndOrder();
			//===order number from the portal is========
			String ordernumber=customerInfn.getOrderNumber();
			customerInfn.clickOnSubmit();
			customerInfn.clickOnOk();
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
		    CustomizeReport("Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate", "pass", "Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate", "fail", "Place_Order_for_T2_item_with_Overpaid_with_Gift_Certificate");	
		}
	}
			
    }}


