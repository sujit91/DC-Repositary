
package com.Hcl.DirectCommerce.browserconfig;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Hcl.DirectCommerce.utility.ResourceHelper;

/**
 * 
 * @author sujit jena
 *
 */
public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions option = new ChromeOptions();
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		chrome.setJavascriptEnabled(true);		
		/*option.setCapability(ChromeOptions.CAPABILITY, chrome);	
		chrome.setCapability(ChromeOptions.CAPABILITY, option);*/
		//Linux
		/*if(System.getProperty("os.name").contains("Linux")){
			option.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}*/
		return option;
	}

	public WebDriver getChromeDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Mac")){
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath(""+"src\\main\\resources\\drivers\\chromedriver"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Window")){
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath(""+"src\\main\\resources\\drivers\\chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		/*else if(System.getProperty("os.name").contains("Linux")){
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}*/
		return null;
	}

}
