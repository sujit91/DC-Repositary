package com.Hcl.DirectCommerce.ApiMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.Hcl.DirectCommerce.logger.LoggerHelper;
import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.uiActions.LoginPage;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class reusableAPIMethodsPage extends TestBase {

	WebDriver driver;
	
	/**
	* Constructor to hand off WebDriver.
	*/
	public reusableAPIMethodsPage (WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	public static ExtentTest logger;

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
		
	
	public Map<String, String> getValueFromStringIntoMap(String Response) {
		Map<String, String> map = new HashMap<String, String>();
		String list1 = Response.replaceAll("\"","");
		
		 
		 String s1=list1.replace("{", "").replace("}", "");
		/*String list = Response.replaceAll(" ","");
		String list1 = list.replaceAll(":"," ");
		String list2 = list1.replaceAll(" ","");
		String list3 = list2.replaceAll(",","");
*/		String array[] = s1.split(",");
		 
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		/*for (int i = 0; i < array.length; i++) {
			String arr[]=array.
			map.put(array[i], array[i + 1]);
			System.out.println(map);
			i = i + 1;
		}*/
		Map<String, String> hashMap
        = new HashMap<String, String>();
		for (String part : array) {
			  
            // split the student data by colon to get the
            // name and roll number
            String stuData[] = part.split(":");
  
            String key = stuData[0].trim();
            String value = stuData[1].trim();
  
            // Add to map
            hashMap.put(key, value);
            System.out.println(hashMap);
        }
  String orderno=hashMap.get("orderNumber");
		return hashMap;

	}
	
	
	public Map<String, String> getValueFromArrayOfStringIntoMap(String Response) {
		Map<String, String> map = new HashMap<String, String>();
		String list = Response.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\[", "").replaceAll("\\]", "");
		String list1 = list.replaceAll(":", " ");
		String list3 = list1.replaceAll("\"|\"", "");
		String array[] = list3.split(",");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
			String innerarray[] = array[i].split(" ");
			for (int j = 0; j < 1; j++) {
				map.put(innerarray[j], innerarray[j + 1]);
				System.out.println(map);
			}
		}
		return map;

	}

	
	public List<String> GetListfromJson(String responseBody, String node, String filenamefromjson)
			throws JSONException {
		JSONObject object1 = new JSONObject(responseBody);
		JSONArray values = object1.getJSONArray(node);
		ArrayList<Object> l = new ArrayList<Object>();
		for (int i = 0; i < values.length(); i++) {
			JSONObject animal = values.getJSONObject(i);
			String filename = animal.getString(filenamefromjson);
			l.add(filename);
			System.out.println(l);
		}
		// ============here remove the space between file=============
		String[] firstArray = new String[l.size()];
		firstArray = l.toArray(firstArray);
		for (int i = 0; i < firstArray.length; i++)
			firstArray[i] = firstArray[i].trim();
		List<String> listofFile = Arrays.asList(firstArray);
		Collections.sort(listofFile);
		return listofFile;

	}

	
	public List<String> GetSpecificFieldfromJson(String colname, String responseBody) throws JSONException {
		String[] str = responseBody.split("\\n");
		System.out.println(str[0]);
		String[] headerArr = str[0].split(",");
		HashMap<String, Integer> headerMapIndex = new HashMap<String, Integer>();
		for (int i = 0; i < headerArr.length; i++) {
			headerMapIndex.put(headerArr[i], new Integer(i));
		}
		System.out.println(headerMapIndex);
		int colindex = ((Integer) headerMapIndex.get(colname)).intValue();
		List<String> vallist = new ArrayList<String>();
		for (int i = 1; i < str.length; i++) {
			String[] cols = str[i].trim().split(",");
			vallist.add(cols[colindex]);
		}
		// ============here the space between file will remove==============
		String[] firstArray = new String[vallist.size()];
		firstArray = vallist.toArray(firstArray);
		for (int i = 0; i < firstArray.length; i++){
			firstArray[i] = firstArray[i].trim();
			}
		List<String> listofFile = Arrays.asList(firstArray);
		Collections.sort(listofFile);
		System.out.println(colname + "===========================" + vallist);
		return listofFile;

	}
	
	public String retrurnordernumber() throws Exception {
			
			reusableAPIMethodsPage APIMethods = PageFactory.initElements(driver, reusableAPIMethodsPage.class);		
            String Username=getObject("Username");
            logExtentReport("login page username: "+Username);
            String grant_type=getObject("grant_type");
            logExtentReport("login page grant_type: "+grant_type);
            String Password=getObject("Password");
            logExtentReport("login page Password: "+Password);
            String scope=getObject("scope");
            logExtentReport("login page scope: "+scope);
			// Logging into the VE using admin creds and generating SESSION_ID
			
			RestAssured.baseURI = "https://fl1npdvwinvm67:8091/token";
			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation().contentType("application/x-www-form-urlencoded; charset=utf-8").formParam("userName",Username)
			          .formParam("password", Password)
			          .formParam("grant_type", grant_type)
			          .formParam("scope", scope);
			Response response = httpRequest.request(Method.POST);
			String responseBody = response.getBody().asString();	
			 logExtentReport("responseBody: "+responseBody);
			 Map<String, String> m=APIMethods.getValueFromArrayOfStringIntoMap(responseBody);			 
			String token=m.get("access_token");
			logExtentReport("access_token: "+token);
			String appServerToken=m.get("appServerToken");
			logExtentReport("appServerToken: "+appServerToken);
			System.out.println(appServerToken);
			String refreshtoken=m.get("refresh_token");
			logExtentReport("refreshtoken: "+refreshtoken);
			System.out.println(refreshtoken);
			System.out.println(token);
			
				
			//=================Endorder Api================
			
			String body="{\n   \"selectedTenders\":{\n      \"selectedExternalCoupons\":[\n         \n      ],\n      \"selectedExternalGifts\":[\n         \n      ],\n      \"selectedGiftCard\":[\n         \n      ],\n      \"selectedCoupons\":[\n         \n      ],\n      \"selectedGiftCertificate\":[\n         \n      ],\n      \"selectedPoints\":{\n         \n      }\n   },\n   \"orderHold\":\"\",\n   \"sourceCode\":\"TIPF    TIPF    \",\n   \"addedItems\":[\n      {\n         \"itemCount\":\"001\",\n         \"edpNo\":\"000443185\",\n         \"id\":1,\n         \"itemNo\":\"REG R1              \",\n         \"division\":\"01\",\n         \"price\":\"30.00\",\n         \"shipMethod\":\"  \",\n         \"customizationRequired\":\" \",\n         \"customizationCode\":\"    \",\n         \"taxExcemption\":\"  \",\n         \"associate\":\" \",\n         \"events\":\"002\",\n         \"discountCode\":\" \",\n         \"company\":\"01\",\n         \"phExcel\":\" \",\n         \"restrictShip\":\" \",\n         \"autoHold\":\" \",\n         \"minimumPrice\":0,\n         \"ftc\":\" \",\n         \"dimWeight\":0,\n         \"phCat\":\" \",\n         \"expiredCode\":\" \",\n         \"posDept\":\"    \",\n         \"dropShip\":\" \",\n         \"foceDropShip\":false,\n         \"unitCount\":\"  \",\n         \"ticketNoDiscTbl\":\"    \",\n         \"ticketNoFiller\":\"    \",\n         \"contProg\":\"      \",\n         \"coSharable\":\" \",\n         \"spec4\":\" \",\n         \"style\":\"            \",\n         \"isPromoItem\":false,\n         \"itemAddedForPromoCode\":\"\",\n         \"giftWraperType\":\"  \",\n         \"taxCode\":\"               \",\n         \"consumable\":\" \",\n         \"lease\":\" \",\n         \"srLeavel1\":\"    \",\n         \"srLeavel2\":\"    \",\n         \"srLeavel3\":\"    \",\n         \"title\":\"  \",\n         \"userId\":\"SHARMILA\",\n         \"status1\":\"R\",\n         \"status2\":\"1\",\n         \"parentComponent\":\"\",\n         \"advantageFlag\":\" \",\n         \"componentFlag\":\"Y\",\n         \"shipToCount\":0,\n         \"lineNo\":0,\n         \"quantity\":1,\n         \"isPriceOverride\":false,\n         \"priceOverrideReason\":null,\n         \"standardPrice\":\"30.00\",\n         \"originalPrice\":30,\n         \"oeStatus\":null,\n         \"itemStatusCode\":null,\n         \"cost\":null,\n         \"source\":null,\n         \"fullPrice\":0,\n         \"createDate\":null,\n         \"multiPricing\":null,\n         \"phAmount\":null,\n         \"insertQuantity\":null,\n         \"multiGroup\":null,\n         \"offer\":null,\n         \"dueQuantity\":null,\n         \"futureDate\":null,\n         \"serialNoRequired\":null,\n         \"extendedDollars\":null,\n         \"customise\":null,\n         \"subWithFlag\":null,\n         \"insert2\":null,\n         \"pctFlag\":null,\n         \"imPrice\":null,\n         \"reserveQuantity\":null,\n         \"cancelledFlag\":\"\",\n         \"overrideRestriction\":\"\",\n         \"isCommentLineItemData\":false,\n         \"isDiscountApplied\":false,\n         \"isCustomizationLineItem\":false,\n         \"isModifyItem\":false,\n         \"isSkipCustomizationClicked\":false,\n         \"selectedShipToForSpecificItems\":[\n            \n         ],\n         \"selectedDummyShipToForSpecificItems\":[\n            \n         ],\n         \"subItems\":null,\n         \"t24Response\":{\n            \"itemCount\":\"\",\n            \"edpNo\":\"000443185\",\n            \"id\":0,\n            \"itemNo\":\"REG R1\",\n            \"division\":\"01\",\n            \"price\":30,\n            \"shipMethod\":\"\",\n            \"customizationRequired\":\"\",\n            \"customizationCode\":\"\",\n            \"taxExcemption\":\"\",\n            \"associate\":\"\",\n            \"events\":\"\",\n            \"discountCode\":\"\",\n            \"company\":\"01\",\n            \"phExcel\":\"\",\n            \"restrictShip\":\"\",\n            \"autoHold\":\"\",\n            \"minimumPrice\":36,\n            \"ftc\":\"\",\n            \"dimWeight\":0,\n            \"phCat\":\"\",\n            \"expiredCode\":\"\",\n            \"posDept\":\"\",\n            \"dropShip\":\"\",\n            \"foceDropShip\":false,\n            \"unitCount\":\"\",\n            \"ticketNoDiscTbl\":\"\",\n            \"ticketNoFiller\":\"\",\n            \"contProg\":\"\",\n            \"coSharable\":\"\",\n            \"spec4\":\"\",\n            \"style\":\"\",\n            \"isPromoItem\":false,\n            \"itemAddedForPromoCode\":\"\",\n            \"giftWraperType\":\"\",\n            \"taxCode\":\"\",\n            \"consumable\":\"\",\n            \"lease\":\"\",\n            \"srLeavel1\":\"\",\n            \"srLeavel2\":\"\",\n            \"srLeavel3\":\"\",\n            \"title\":\"\",\n            \"userId\":\"\",\n            \"status1\":\"Regular active item\",\n            \"status2\":\"\",\n            \"parentComponent\":\"\",\n            \"advantageFlag\":\"\",\n            \"componentFlag\":\"\",\n            \"shipToCount\":0,\n            \"lineNo\":0,\n            \"quantity\":0,\n            \"isPriceOverride\":false,\n            \"priceOverrideReason\":null,\n            \"standardPrice\":0,\n            \"originalPrice\":0,\n            \"oeStatus\":\"\",\n            \"itemStatusCode\":\"R1\",\n            \"cost\":\"0000030.0000\",\n            \"source\":\"\",\n            \"fullPrice\":30,\n            \"createDate\":\"20190926\",\n            \"multiPricing\":\"Y\",\n            \"phAmount\":\"0000.00\",\n            \"insertQuantity\":\"0000\",\n            \"multiGroup\":\"\",\n            \"offer\":\"\",\n            \"dueQuantity\":\"000000000\",\n            \"futureDate\":\"\",\n            \"serialNoRequired\":\"\",\n            \"extendedDollars\":\"\",\n            \"customise\":\"\",\n            \"subWithFlag\":\"\",\n            \"insert2\":\"\",\n            \"pctFlag\":\"\",\n            \"imPrice\":\"000003000\",\n            \"reserveQuantity\":\"000000\",\n            \"cancelledFlag\":\"\",\n            \"overrideRestriction\":\"\",\n            \"isCommentLineItemData\":false,\n            \"isDiscountApplied\":false,\n            \"isCustomizationLineItem\":false,\n            \"isModifyItem\":false,\n            \"isSkipCustomizationClicked\":false,\n            \"selectedShipToForSpecificItems\":[\n               \n            ],\n            \"selectedDummyShipToForSpecificItems\":[\n               \n            ],\n            \"subItems\":null,\n            \"t24Response\":null,\n            \"commentLineItemData\":null,\n            \"customizationLineItem\":null,\n            \"restictItem\":null,\n            \"errorResponse\":null,\n            \"associatedTo\":\"\",\n            \"associatedItem\":null\n         },\n         \"commentLineItemData\":[\n            {\n               \"prompt\":\"FNAME_COMMENT:      \",\n               \"length\":16,\n               \"value\":\"                                                       \",\n               \"kitIndicator\":\" \",\n               \"compNumber\":\"00\",\n               \"custmzType\":\"  \",\n               \"listCode\":\"      \",\n               \"validListType\":\" \"\n            },\n            {\n               \"prompt\":\"LNAME_COMMENT:      \",\n               \"length\":16,\n               \"value\":\"                                                       \",\n               \"kitIndicator\":\" \",\n               \"compNumber\":\"00\",\n               \"custmzType\":\"  \",\n               \"listCode\":\"      \",\n               \"validListType\":\" \"\n            },\n            {\n               \"prompt\":\"PHONENO_COMMENT:    \",\n               \"length\":16,\n               \"value\":\"                                                       \",\n               \"kitIndicator\":\" \",\n               \"compNumber\":\"00\",\n               \"custmzType\":\"  \",\n               \"listCode\":\"      \",\n               \"validListType\":\" \"\n            },\n            {\n               \"prompt\":\"EMAIL_COMMENT:      \",\n               \"length\":47,\n               \"value\":\"                                                       \",\n               \"kitIndicator\":\" \",\n               \"compNumber\":\"00\",\n               \"custmzType\":\"  \",\n               \"listCode\":\"      \",\n               \"validListType\":\" \"\n            },\n            {\n               \"prompt\":\"INITIALS_COMMENT:   \",\n               \"length\":3,\n               \"value\":\"                                                       \",\n               \"kitIndicator\":\" \",\n               \"compNumber\":\"00\",\n               \"custmzType\":\"  \",\n               \"listCode\":\"      \",\n               \"validListType\":\" \"\n            }\n         ],\n         \"customizationLineItem\":null,\n         \"restictItem\":[\n            \n         ],\n         \"errorResponse\":\"\",\n         \"associatedTo\":\"\",\n         \"associatedItem\":[\n            \n         ],\n         \"isAccordianClicked\":true\n      }\n   ],\n   \"deletedItems\":[\n      \n   ],\n   \"orderComments\":[\n      \n   ],\n   \"assignedOrderNumber\":\"\",\n   \"payMethod\":{\n      \"selectedPayMethodType\":\"OAOA\",\n      \"payMethodType\":\"OA\",\n      \"PayGroup\":\"OA\",\n      \"expmm\":\"\",\n      \"expyy\":\"\",\n      \"cardno\":\"\",\n      \"ccnum\":\"\"\n   },\n   \"shipMethodPageData\":{\n      \"shipMethod\":\" \",\n      \"phOverride\":true,\n      \"phValue\":\"\",\n      \"expediteOrder\":false,\n      \"disablexpeditOrder\":true,\n      \"noInsurance\":false,\n      \"saturdayDelivery\":false,\n      \"thirdPartyUPS\":\"\",\n      \"thirdPartyFedex\":\"\",\n      \"recipient\":\" \",\n      \"date\":\"\"\n   },\n   \"shippingMethod\":{\n      \"name\":\"02 UPS GROUND\",\n      \"value\":\"02\"\n   },\n   \"selectedShipToCustomer\":[\n      \n   ],\n   \"buyerCustomer\":{\n      \"srXRefType\":\"\",\n      \"srXRefCustNo\":\"\",\n      \"srXRefDelete\":\"\",\n      \"srMi\":\"\",\n      \"srCompName\":\"JSDA\",\n      \"srRef2\":\"\",\n      \"srRef1\":\"\",\n      \"srCity\":\"JSDA\",\n      \"srState\":\"FL\",\n      \"srZip\":\"33445\",\n      \"srCountry\":\"USA\",\n      \"srDPhone\":\"\",\n      \"srNPhone\":\"\",\n      \"srFPhone\":\"\",\n      \"srEmail\":\"\",\n      \"srTitle\":\"\",\n      \"srCustType\":\"REGULAR\",\n      \"srStatus\":\"Customer\",\n      \"srCustNo\":\"0000360719\",\n      \"hdrCompany\":\"01\",\n      \"hdrRequestID\":\"\",\n      \"hdrDivision\":\"01\",\n      \"srStreet\":\"uyguyg\",\n      \"srAction\":\"Active\",\n      \"srActionCode\":\"A\",\n      \"srNoPromote\":\"\",\n      \"srRentCode\":\"\",\n      \"srGender\":\"\",\n      \"srGenderCode\":\"\",\n      \"srLanguageCode\":\"\",\n      \"srLanguage\":\"\",\n      \"srLngCODLimit\":9999,\n      \"srLngCreditLimit\":0,\n      \"srTermsDays\":30,\n      \"srTermsPercent\":5,\n      \"srContract\":\"\",\n      \"srRegistryFlag\":\"2019061\",\n      \"srTotalPotential\":\"000320414.52\",\n      \"srCustEdp\":34326,\n      \"srCountryCode\":\"0000\",\n      \"srStatusCode\":\"C\",\n      \"srRent\":\"\",\n      \"srSource\":\"\",\n      \"srUpdateWebPass\":\"\",\n      \"srUPSType\":\"C\",\n      \"srUSPS\":\"Y\",\n      \"srFname\":\"SUJIT\",\n      \"srLname\":\"jena\",\n      \"isFirstTimeAddCustomer\":false,\n      \"srWorthVal\":0,\n      \"srRecencyVal\":\"1 y\",\n      \"srFrequencyVal\":1,\n      \"srMonetaryVal\":12,\n      \"srOrigSourceVal\":\"SUJIT   SUJIT\",\n      \"srNoRent\":\"\",\n      \"srNoPromoteCode\":\" \",\n      \"srTitleCode\":\"\",\n      \"srCustTypeCode\":\"01\",\n      \"memberStatus\":\" \",\n      \"availableCredits\":\"0000000.00\",\n      \"termsDays\":\"30\",\n      \"termsPercent\":\"5.00\",\n      \"srOldCustNo\":\"          \",\n      \"srUpdateFrom\":\" \",\n      \"srFirstDate\":\"06/18/19\",\n      \"srLastDate\":\"06/11/20\",\n      \"srAvgOrder\":\"12\"\n   },\n   \"orderNumber\":\"\",\n   \"additionalInformationScreenData\":{\n      \"taxExcempt\":\"1\",\n      \"releaseCode\":\" \",\n      \"suppressLetter\":\" \",\n      \"holdComplete\":\"1\",\n      \"backOrder\":\"0\",\n      \"desc1\":\" \",\n      \"desc2\":\" \",\n      \"releaseDate\":\" \"\n   },\n   \"paymentdetails\":{\n      \"selectedinstallmentBilling\":\"\",\n      \"isCvvSecCodeRender\":false,\n      \"IsRequiredPONumber\":true,\n      \"PONumber\":\"\",\n      \"selectedReleaseCode\":\" \",\n      \"releaseCode\":[\n         {\n            \"label\":\"\",\n            \"name\":\"Select\",\n            \"value\":\" \",\n            \"childrens\":null\n         }\n      ],\n      \"releaseDate\":\"2021-12-28\",\n      \"creditmemo\":\"\",\n      \"available\":\"0.00\",\n      \"selectedPaymethod\":\"OAOA\",\n      \"selectedCardType\":\"BL\",\n      \"cardNumber\":\"\",\n      \"dummyCardNumber\":\"\",\n      \"isCardNumberFocus\":false,\n      \"expireDate\":\"\",\n      \"amount\":\"\",\n      \"cvv\":\"\",\n      \"selectedTendorType\":\"Promo\",\n      \"redeemAmount\":\"\",\n      \"pointbalance\":\"\",\n      \"addtionalTender\":false,\n      \"addtionalDiscounts\":false,\n      \"ssnlast\":\"\",\n      \"birthday\":\"\",\n      \"remitamount\":\"\",\n      \"termsdays\":\"30\",\n      \"terms\":\"5.00\",\n      \"acct\":\"\",\n      \"bank\":\"\",\n      \"ordertransid\":\"\",\n      \"authtranstid\":\"\",\n      \"card\":\"\",\n      \"codenumber\":\"\",\n      \"promoCode\":\"\",\n      \"selectedExternalCoupon\":\"amount\",\n      \"externalCouponType\":\"amount\",\n      \"externalCouponRef\":\"\",\n      \"externalCouponValue\":\"\",\n      \"giftCard\":\"\",\n      \"giftCardPin\":\"\",\n      \"giftamount\":\"\",\n      \"disc1Key\":\"\",\n      \"disc2Key\":\"\",\n      \"disc1\":\" \",\n      \"disc2\":\" \",\n      \"disc1Disabled\":false,\n      \"disc2Disabled\":false,\n      \"disc1List\":[\n         {\n            \"label\":null,\n            \"name\":\"Select\",\n            \"value\":\" \",\n            \"childrens\":null\n         }\n      ],\n      \"disc2List\":[\n         {\n            \"label\":null,\n            \"name\":\"Select\",\n            \"value\":\" \",\n            \"childrens\":null\n         }\n      ],\n      \"billupfront\":false,\n      \"checkNumber\":\"\",\n      \"allowDuplicatePO\":false\n   },\n   \"sendLetterData\":{\n      \"customizeItem\":\"\",\n      \"sendLetterCode\":\"\",\n      \"sendLetterType\":\"\"\n   },\n   \"cancelTypeOnEditAfterOrderRecap\":\"\",\n   \"cancelReasonCode\":\"\",\n   \"promoCode\":[\n      \n   ],\n   \"additionalinformationDetails\":{\n      \"phOverride\":false,\n      \"phValue\":\"\",\n      \"actualPh\":false,\n      \"noinsurance\":false,\n      \"selectedPhReasonValue\":\"\"\n   },\n   \"selectedThirdParty\":{\n      \"tabName\":\"\",\n      \"shipTo\":{\n         \n      }\n   },\n   \"modifyOrderOrderNumber\":\"\",\n   \"isModifyOrder\":false,\n   \"raOrderNo\":\"\",\n   \"raNumber\":\"\",\n   \"nprscreen\":\"\",\n   \"nprcallType\":\"\",\n   \"bReleaseOOB\":false,\n   \"customAPIOrderEntryDataUrl\":\"\",\n   \"IsDataLogReq\":\"True\",\n   \"onlineAuthProcess\":[\n      \n   ],\n   \"holdReasonComments\":[\n      \n   ]\n}\n";
			RestAssured.baseURI = "https://fl1npdvwinvm67:8091/api/web/orderentry";
			RequestSpecification httpRequest1 = RestAssured.given().relaxedHTTPSValidation().contentType("application/json");
			Header h1= new Header("apptoken", appServerToken);
		    Header h2 = new Header("authorization", "bearer"+' '+token );
		    Header h3 = new Header("refresh_token", refreshtoken);
		    Header h4 = new Header("Content-Type", "application/json");
			List<Header> list = new ArrayList<Header>();
		    list.add(h1);
		    list.add(h2);
		    list.add(h3);
		    list.add(h4);
		    Headers header = new Headers(list);
		    logExtentReport("header added to endorder api:"+list);
		    httpRequest1.headers(header).body(body);
			Response response1 = httpRequest1.request(Method.POST);
			System.out.println(response1);
			 logExtentReport("response1 from endorder api is:"+response1);
			
			String responseBody1 = response1.getBody().asString();
			System.out.println(responseBody1);
			logExtentReport("responseBody from endorder api is:"+responseBody1);
			int responseStatus1 = response1.getStatusCode();	
			System.out.println(responseStatus1);	
			logExtentReport("responseStatus1 from endorder is :"+responseStatus1);
			Map<String, String> m1=APIMethods.getValueFromStringIntoMap(responseBody1);		
			String orderNumber=m1.get("orderNumber");		
			System.out.println(orderNumber);
			logExtentReport("orderNumber from endorder api is:"+orderNumber);
			int index1=body.indexOf("orderNumber")+13; 
			logExtentReport("index of order number to be inserted is:"+index1);
	        // Create a new string
	        String newString = new String();
	  
	        for (int i = 0; i < body.length(); i++) {
	  
	            // Insert the original string character
	            // into the new string
	            newString += body.charAt(i);
	  
	            if (i == index1) {
	  
	                // Insert the string to be inserted
	                // into the new string
	                newString += orderNumber;
	          
	            }			
	        }			
			System.out.println(newString);
			logExtentReport("new string to be put as body is:"+newString);  
			//=================submitorder Api================		
			RestAssured.baseURI = "https://fl1npdvwinvm67:8091/api/web/orderentry";
			RequestSpecification httpRequest2 = RestAssured.given().relaxedHTTPSValidation().contentType("application/json");
			Header R1= new Header("apptoken", appServerToken);
		    Header R2 = new Header("authorization", "bearer"+' '+token );
		    Header R3 = new Header("refresh_token", refreshtoken);
		    Header R4 = new Header("Content-Type", "application/json");
			List<Header> list1 = new ArrayList<Header>();
		    list1.add(R1);
		    list1.add(R2);
		    list1.add(R3);
		    list1.add(R4);
		    Headers header1 = new Headers(list1);
		    logExtentReport("new header to be inserted as:"+list1);  
		    httpRequest2.headers(header1).body(newString);
			Response response2 = httpRequest2.request(Method.POST);
			System.out.println(response2);
			logExtentReport("response from submit order:"+response2);  
			String responseBody2 = response2.getBody().asString();
			System.out.println(responseBody2);
			logExtentReport("responsebody from submit order:"+responseBody2); 
			int responseStatus2 = response2.getStatusCode();	
			System.out.println(responseStatus2);	
			logExtentReport("response status from submit order:"+responseStatus2); 
			Map<String, String> m2=APIMethods.getValueFromStringIntoMap(responseBody2);		
			String orderNumber1=m2.get("orderNumber");			
			System.out.println(orderNumber1);	
			logExtentReport("order number from submit order is:"+orderNumber1); 
		    return orderNumber1;
	}


	public void validate() throws Exception {
		try {
			// TODO
		} catch (Exception ex) {			
			throw new Exception("TODO", ex);
		}
	}
	
	public void UserDefinedMethod() throws Exception{
		throw new Exception("Not Implemented Method");
	}
}