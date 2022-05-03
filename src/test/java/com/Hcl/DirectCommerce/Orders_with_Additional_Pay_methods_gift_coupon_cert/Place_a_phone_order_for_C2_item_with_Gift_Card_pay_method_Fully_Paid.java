package com.Hcl.DirectCommerce.Orders_with_Additional_Pay_methods_gift_coupon_cert;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

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

public class Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\OrderManagement\\OrderlifeCycle_Additional_Pay_methods.xlsx";
    String testscenario="Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid")
    public void C2_OA() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization==========================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available=======
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"OrderlifeCycle_Additional_Pay", testscenario);
	
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
			
			OrderDetails OrderDetails=PageFactory.initElements(driver,OrderDetails.class);
	        OrderDetails.productAvailabilityPopupYes();
	        //========validate the status=========================
	        
	        
	        
	        
			//======click on payment details====
			customerInfn.clickOnPaymentdetailTab();
			String Paymethod=keyvalue.get("Paymethod").toString();
			customerInfn.selectPaymethod(Paymethod);
			
			//===select additional tender========		
			PaymentDetails PaymentDetails = PageFactory.initElements(driver, PaymentDetails.class);
			String Tendertype = keyvalue.get("Tendertype").toString();
			String Coupon = keyvalue.get("Coupon").toString();
			PaymentDetails.selectTenderType(Tendertype);
			PaymentDetails.ProvidegiftCard(Coupon);
			PaymentDetails.getCurrentBalance();
			
			//===provide shiping details=======
			customerInfn.clickOnShippingdetailTab();
			String ShippingAddress=keyvalue.get("ShippingAddress").toString();
			customerInfn.selectshipMethod(ShippingAddress);
			customerInfn.clickOnEndOrder();
			//===order number from the portal is========
			String ordernumber=customerInfn.getOrderNumber();
			customerInfn.clickOnSubmit();
			customerInfn.clickOnOk();
		    CustomizeReport("Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid", "pass", "Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid", "fail", "Place_a_phone_order_for_C2_item_with_Gift_Card_pay_method_Fully_Paid");	
		}
	}
			
    }}


