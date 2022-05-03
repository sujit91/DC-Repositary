package com.Hcl.DirectCommerce.OrderLifeCycle_DiffProducts;

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

public class Place_an_order_for_K1_product extends TestBase{
    String sFilePath = System.getProperty("user.dir")+"\\TestData\\OrderManagement\\OrderLifeCycle_DiffProducts.xlsx";
    String testscenario="Place_an_order_for_K1_product";
    HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
    
    @Test(description="Place_an_order_for_K1_product")
    public void K1_OA() throws Throwable{
		boolean Report=false;
		try {	
			getApplicationUrl(ObjectReader.reader.getUrl());
			//===========page initialization===================
			LoginPage login=PageFactory.initElements(driver,LoginPage.class);
			String account=Utility.getObject("Account");
			//====fatch the item qty Available============
			keyvalue=(HashMap<Object, Object>)Excel_Reader.getrowvalue(sFilePath,"OrderLifeCycle_DiffProducts", testscenario);
			//==GET QTY AVAILABLE FOR AUTO R1 01========
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
			

			//======validation pending============================
			
			//======click on payment details======================
			customerInfn.clickOnPaymentdetailTab();
			String Paymethod=keyvalue.get("Paymethod").toString();
			customerInfn.selectPaymethod(Paymethod);
			//===provide shiping details=======
			customerInfn.clickOnShippingdetailTab();
			String ShippingAddress=keyvalue.get("ShippingAddress").toString();
			customerInfn.selectshipMethod(ShippingAddress);
			customerInfn.clickOnEndOrder();
			//===order number from the portal is========
			String ordernumber=customerInfn.getOrderNumber();
			customerInfn.clickOnSubmit();
			customerInfn.clickOnOk();		
		    CustomizeReport("Place_an_order_for_K1_product", "pass", "Place_an_order_for_K1_product");
		    Report=true;
	} finally {
		if (Report==false) {
			CustomizeReport("Place_an_order_for_K1_product", "fail", "Place_an_order_for_K1_product");	
		}
	}
			
    }}



