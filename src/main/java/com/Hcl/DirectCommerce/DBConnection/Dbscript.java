package com.Hcl.DirectCommerce.DBConnection;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.Hcl.DirectCommerce.testBase.TestBase;
import com.Hcl.DirectCommerce.utility.ResourceHelper;

import oracle.jdbc.driver.OracleDriver;

public class Dbscript extends TestBase{
	String location = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	public static SoftAssert s_assert = new SoftAssert();
	public Dbscript(WebDriver driver){
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static Map<String, String> getConfigData() throws Exception {
		String configFile =ResourceHelper.getResourcePath("Configurations\\config.properties");
		HashMap<String, String> configData = new HashMap<String, String>();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(configFile);
		prop.load(fis);
		Set<Object> keys = prop.keySet();
		for (Object k : keys) {
			configData.put((String) k, prop.getProperty((String) k));
		}
		return configData;
	}
	
	
	public static String getValueOracle(String dbQuery,String columnName)  throws Exception {

		Map<String, String> configData = getConfigData();
		String result = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + configData.get("DBServerName") + ":" + configData.get("DBPort") + ":"
							+ configData.get("DBserviceName"),
							configData.get("DBUserName"), configData.get("DBPassword"));
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(dbQuery);
			while (resultset.next()) {
				result = resultset.getString(columnName).toString();
				break;
			}
			System.out.println("Result from DB for  ++++++++++++++++++++++++=" + result);
			logExtentReport("Result from DB for  ++++++++++++++++++++++++=" + result);
			resultset.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception::" + e.getMessage());
			logExtentReportFail("unable get value from db");
			throw e;
		}
		return result;
	}
	
	public static Map<String, Object> verifyEmailTestersMailBox(String query,String columnname) throws Exception {
		Map<String, String> configData = getConfigData();
		Map<String, Object> result = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + configData.get("DBServerName") + ":" + configData.get("DBPort") + ":"
							+ configData.get("DBserviceName"),
							configData.get("DBUserName"), configData.get("DBPassword"));
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				Blob blob = resultset.getBlob(columnname);
				String emailBody = new String(blob.getBytes(1, (int) blob.length()));
				result = new HashMap<String, Object>();
				//=========need to implement as per scenario=======
				result.put("", resultset.getString(""));
				result.put("", resultset.getString(""));
				result.put("", resultset.getString(""));
				result.put("", resultset.getString(""));
				result.put("", resultset.getString(""));
				result.put("", resultset.getDate(""));
				break;
			}
			resultset.close();
			connection.close();

		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	public static String EmailValidationsSQL(String dbQuery,String columnName)  throws Exception {

		                                                       
		Map<String, String> configData = getConfigData();
		String result = null;
		try {
			String url="jdbc:sqlserver://" + configData.get("DBsqlServerName")+ ":" + configData.get("DBsqlPort")+";"+ "DatabaseName="+configData.get("DBsqlDatabase");   
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection connection = DriverManager.getConnection(url,configData.get("DBsqlUserName"), configData.get("DBsqlPassword"));
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(dbQuery);
			while (resultset.next()) {
				result = resultset.getString(columnName).toString();
				break;
			}
			System.out.println("Result from DB for  ++++++++++++++++++++++++=" + result);
			logExtentReport("Result from DB for  ++++++++++++++++++++++++=" + result);
			resultset.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception::" + e.getMessage());
			logExtentReportFail("unable get value from db");
			throw e;
		}
		return result;
	}



public static void Executequery(String dbQuery)  throws Exception {

	Map<String, String> configData = getConfigData();
	try {
		String url="jdbc:sqlserver://" + configData.get("DBsqlServerName")+ ":" + configData.get("DBsqlPort")+";"+ "DatabaseName="+configData.get("DBsqlDatabase");   
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Connection connection = DriverManager.getConnection(url,configData.get("DBsqlUserName"), configData.get("DBsqlPassword"));
		Statement statement = connection.createStatement();
	    statement.executeUpdate(dbQuery);
		System.out.println();
		connection.close();
	} catch (Exception e) {
		System.out.println("Exception::" + e.getMessage());
		logExtentReportFail("unable get value from db");
		throw e;
	}
}

}
