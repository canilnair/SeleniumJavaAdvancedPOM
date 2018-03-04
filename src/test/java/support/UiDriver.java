package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * File name   :UIDriver.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class UiDriver {
	Configurations configurations = Configurations.getInstance();
	WebDriver wDriver;
	EventFiringWebDriver eventDriver;
	/**
	 * 
	 * Method name  : getDriver
	 * Return types : WebDriver
	 * Description  :The method that returns the various driver instance based on the parameter
	 */
	public WebDriver getDriver(String driver){

		try{
			switch(driver){

			case "Firefox" :
				String geckoDriver=Settings.getInstance().getDriverEXEDir()+"geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", geckoDriver);
				wDriver = new FirefoxDriver();
				//return wDriver;
				break;

			case "Google Chrome" :
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--dns-prefetch-disable");
				option.addArguments("--start-maximized");
				String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				wDriver = new ChromeDriver(option);
				//return wDriver;
				break;

			case "IE":
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("requireWindowFocus", true);
				String ieDriver=Settings.getInstance().getDriverEXEDir()+"IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", ieDriver);
				wDriver = new InternetExplorerDriver(capabilities);
				//return wDriver;
				break;

			case "Edge":
				//return wDriver;

			case "Safari":
				//return wDriver;
			
			case "HtmlUnitDriver":
				wDriver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED);
				((HtmlUnitDriver) wDriver).setJavascriptEnabled(true);
				break;
				
			default :
				String geckoDriver1=Settings.getInstance().getDriverEXEDir()+"geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", geckoDriver1);
				wDriver = new FirefoxDriver();
				break;
			}
			
			return wDriver;
			
		}catch(Exception e){
			e.printStackTrace();
			wDriver = new FirefoxDriver();
			return wDriver;
		}
	}
	
	/**
	 * 
	 * Method name  : quitDriver
	 * Return types : boolean
	 * Description  : This method is to quit the driver instance after completing a test
	 */
	public boolean quitDriver(WebDriver wDriver){
		try{
			wDriver.quit();
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
