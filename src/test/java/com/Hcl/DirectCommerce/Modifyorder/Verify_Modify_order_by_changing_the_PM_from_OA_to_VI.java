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

public class Verify_Modify_order_by_changing_the_PM_from_OA_to_VI extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\modifyorder.xlsx";
    String testscenario="Verify_Modify_order_by_changing_the_PM_from_OA_to_PX";
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
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"ModifyOrder2", testscenario);
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
			//======click on payment details====
			OrderDetails OrderDetails=PageFactory.initElements(driver,OrderDetails.class);
			customerInfn.clickOnPaymentdetailTab();
			String Paymethod=keyvalue.get("Paymethod").toString();
			customerInfn.selectPaymethod(Paymethod);
			String Cardnumber=keyvalue.get("Cardnumber").toString();
			OrderDetails.EnterVisaCardnumber(Cardnumber);
			String Expirydate=keyvalue.get("Expirydate").toString();
			OrderDetails.ExpiryDatetextfield(Expirydate);
			
			String cvvnumber=keyvalue.get("cvvnumber").toString();
			OrderDetails.Cvvtextfield(cvvnumber);	
			//===provide shiping details=======
			customerInfn.clickOnShippingdetailTab();
			
			customerInfn.clickOnModify();
			
			
			customerInfn.clickOnSubmit();
			customerInfn.addModifyOrderComment();
			//====validation from DB and UI============
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
			//====validate card number validation=========
			String Encryptquery=keyvalue.get("Encryptquery").toString();
			String EncryptColumn=keyvalue.get("EncryptColumn").toString();
			String exeQuery6 = Utility.ReplaceValue(Encryptquery, "order", ordernumber);
			String Encryptvalue=Dbscript.EmailValidationsSQL(exeQuery6, EncryptColumn);
			
			if (!Encryptvalue.equals(Cardnumber)) {	
				logExtentReport("card number is encrypted");
			} else {
				logExtentReportFail("card number is not encrypted");
				
			}
			//====validate VENDOR============		
			String ONLINE_CC_AUTH_IN=keyvalue.get("ONLINE_CC_AUTH_IN").toString();
			String VENDOR=keyvalue.get("VENDOR").toString();
			String exeQuery7 = Utility.ReplaceValue(ONLINE_CC_AUTH_IN, "order", ordernumber);
		    String VENDORCOLUMN=keyvalue.get("VENDORCOLUMN").toString();
			String VENDORTYPE=Dbscript.EmailValidationsSQL(exeQuery7, VENDORCOLUMN);
			
			if (VENDORTYPE.contains(VENDOR)) {	
				logExtentReport("VENDOR IS VALIDATED SUCCESSFULLY");
			} else {
				logExtentReportFail("VENDOR IS NOT VALIDATED SUCCESSFULLY");
			}
			//====RECORD validation=========		
			String RECORD=keyvalue.get("RECORD_TYPE_COLUMN").toString();
			String exeQuery8 = Utility.ReplaceValue(ONLINE_CC_AUTH_IN, "order", ordernumber);
		    String RECORD_TYPE_COLUMN=keyvalue.get("RECORD_TYPE_COLUMN").toString();
			String RECORDTYPE=Dbscript.EmailValidationsSQL(exeQuery8, RECORD_TYPE_COLUMN);
			
			if (RECORDTYPE.contains(RECORD)) {	
				logExtentReport("RECORD IS VALIDATED SUCCESSFULLY");
			} else {
				logExtentReportFail("RECORD IS NOT VALIDATED SUCCESSFULLY");
			}
			
			//====DI_NETWORK_REF_ID_COLUMN validation=========		
			String exeQuery9 = Utility.ReplaceValue(ONLINE_CC_AUTH_IN, "order", ordernumber);
		    String DI_NETWORK_REF_ID_COLUMN=keyvalue.get("DI_NETWORK_REF_ID_COLUMN").toString();
			String DI_NETWORK_REF_ID=Dbscript.EmailValidationsSQL(exeQuery9, DI_NETWORK_REF_ID_COLUMN);
			
			if (DI_NETWORK_REF_ID!=null) {	
				logExtentReport("DI_NETWORK_REF_ID IS VALIDATED SUCCESSFULLY");
			} else {
				logExtentReportFail("DI_NETWORK_REF_ID IS NOT VALIDATED SUCCESSFULLY");
			}
			
		    CustomizeReport("Verify_Modify_order_by_changing_the_PM_from_OA_to_PX", "pass", "Verify_Modify_order_by_changing_the_PM_from_OA_to_PX");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Verify_Modify_order_by_changing_the_PM_from_OA_to_PX", "fail", "Verify_Modify_order_by_changing_the_PM_from_OA_to_PX");	
		}
	}
			
    }}

