package training.selenium.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import training.selenium.utilities.CommonUtilities;;


public class BasePage {

	protected WebDriver driver;
	protected WebElement ele;
	protected List<WebElement> listEle;
	
	int attempts;
	
	public BasePage() {}
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebDriver getDriver() {
		if (driver==null)
			driver = connectDriver();
		return driver;
	}
		
	public WebDriverWait wait(int secs) {
		return new WebDriverWait(driver,secs);
	}
	
	public WebElement getElement(By by, int secs) {
		attempts=0;
		while(attempts < 2) {
			try {
				WebElement ele = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.until(ExpectedConditions.visibilityOfElementLocated(by));
				
				return ele;
			}
			catch(StaleElementReferenceException se) {}
			attempts++;
		}
		return null;
	}
	
	public WebElement getElementPresenceOfElementLocated(By by, int secs) {
		attempts=0;
		while(attempts < 2) {
			try {
				WebElement ele =  wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.until(ExpectedConditions.presenceOfElementLocated(by));
				return ele;
				
			}
			catch(StaleElementReferenceException se) {}
			attempts++;
		}
		return null;
	}
	
	public List<WebElement> getListElements(By by, int secs){
		attempts=0;
		while(attempts < 2) {
			try {
				List<WebElement> list = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
				return list;
			}
			catch(StaleElementReferenceException se) {}
			attempts++;
		}
		return null;
	}
	
	public List<WebElement> getListPresenceOfElementsLocated (By by, int secs){
		attempts = 0;
		while(attempts < 2) {
			try {
				listEle = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				return listEle; 
						
			}catch(StaleElementReferenceException se) {}
		}
		return null;
	}
	
	public Select getSelectElement(By by, int secs) {
		return new Select(getElement(by,secs));
	}
	
	public WebDriver connectDriver() {
		System.setProperty("browser","chrome");
		
		Properties propSystem = System.getProperties();
		Enumeration e = propSystem.propertyNames();
		
		Map<String,String> propMap = new HashMap<String, String>();
		propMap = CommonUtilities.putSysProps(e, propSystem);
		
		String projectPath = propMap.get("user.dir");
		String propBrowser = propMap.get("browser").trim().toLowerCase();
		String os = propMap.get("os.name").trim().toLowerCase();
				 
		if (propBrowser.isEmpty() || propBrowser == null){
			throw new IllegalArgumentException("Missing parameter value for browser");
		}
		else {
			String http_proxy = System.getProperty("http_proxy", "");
			String https_proxy = System.getProperty("https_proxy", "");
			
			if(os.indexOf("win")>=0) {
				if(propBrowser.contentEquals("chrome")) {
					
					ChromeOptions op = new ChromeOptions();
					
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					op.setCapability(CapabilityType.PROXY, proxy);
					
					System.setProperty("webdriver.chrome.driver", 
							projectPath + "\\drivers\\chromedriver\\chromedriver-77.exe");
					
					driver = new ChromeDriver(op);	
				}
				else if(propBrowser.contentEquals("ff") || 
						propBrowser.contentEquals("firefox")) {
					FirefoxOptions dc = new FirefoxOptions();
					
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					dc.setCapability(CapabilityType.PROXY, proxy);
					
					FirefoxProfile profile = new FirefoxProfile();
					profile.setAcceptUntrustedCertificates(false);
					profile.setAssumeUntrustedCertificateIssuer(true);
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					
					dc.setCapability(FirefoxDriver.PROFILE, profile);
					dc.setCapability("marionette", true);
					
					System.setProperty("webdriver.gecko.driver", 
							projectPath + "\\drivers\\firefox\\geckodriver.exe");
					System.setProperty("webdriver.firefox.marionette", 
							projectPath + "\\drivers\\firefox\\geckodriver.exe");
					driver = new FirefoxDriver(dc);
				}
				else if (propBrowser.contentEquals("ie") || 
						propBrowser.contentEquals("internetexplorer")) {
					System.setProperty("webdriver.ie.driver", 
							projectPath + "\\drivers\\ie\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
				else {
					throw new IllegalArgumentException("Browser name is not correct or is not valid...");
				}	
			}
			else if((os.indexOf("nux")>=0) || (os.indexOf("nix")>=0)) {
				if(propBrowser.contentEquals("chrome")) {
					ChromeOptions op = new ChromeOptions();
					
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					op.setCapability(CapabilityType.PROXY, proxy);
					
					System.setProperty("webdriver.chrome.driver", 
							projectPath + "/drivers/chromedriver/chromedriver");
					
					driver = new ChromeDriver(op);	
				}
				else if(propBrowser.contentEquals("ff") || 
						propBrowser.contentEquals("firefox")) {
					FirefoxOptions dc = new FirefoxOptions();
					
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					dc.setCapability(CapabilityType.PROXY, proxy);
					
					FirefoxProfile profile = new FirefoxProfile();
					profile.setAcceptUntrustedCertificates(false);
					profile.setAssumeUntrustedCertificateIssuer(true);
					profile.setPreference("app.update.enabled", false);
					profile.setPreference("browser.download.folderLisy", 2);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					
					dc.setCapability(FirefoxDriver.PROFILE, profile);
					dc.setCapability("marionette", true);
					
					System.setProperty("webdriver.gecko.driver", 
							projectPath + "\\drivers\\firefox\\geckodriver.exe");
					System.setProperty("webdriver.firefox.marionette", 
							projectPath + "\\drivers\\firefox\\geckodriver.exe");
					driver = new FirefoxDriver(dc);
				}
				else {
					throw new IllegalArgumentException("Browser name is not correct or is not valid...");
				}
			}
			else if((os.indexOf("mac")>=0) || (os.indexOf("darwin")>=0)) {
				if(propBrowser.trim().contentEquals("chrome")) {
					ChromeOptions op = new ChromeOptions();
					
					/*
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					op.setCapability(CapabilityType.PROXY, proxy);
					*/
					System.setProperty("webdriver.chrome.driver", 
							projectPath + "/drivers/chromedriver/chromedriver-77");
					
					driver = new ChromeDriver(op);	
				}
				else if(propBrowser.contentEquals("ff") || 
						propBrowser.contentEquals("firefox")) {
					FirefoxOptions dc = new FirefoxOptions();
					
					/*
					Proxy proxy = new Proxy();
					proxy.setHttpProxy(http_proxy);
					proxy.setSslProxy(https_proxy);
					
					dc.setCapability(CapabilityType.PROXY, proxy);
					*/
					
					FirefoxProfile profile = new FirefoxProfile();
					profile.setAcceptUntrustedCertificates(false);
					profile.setAssumeUntrustedCertificateIssuer(true);
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					
					dc.setCapability(FirefoxDriver.PROFILE, profile);
					dc.setCapability("marionette", true);
					
					System.setProperty("webdriver.gecko.driver", 
							projectPath + "/drivers/firefox/geckodriver");
					//System.setProperty("webdriver.firefox.marionette", 
					//		projectPath + "/drivers/firefox/geckodriver");
					driver = new FirefoxDriver(dc);
				}
				else {
					throw new IllegalArgumentException("Browser name is not correct or is not valid...");
				}
			}
			else {
				throw new IllegalArgumentException("OS out of scope ...");
			}
		}
		return driver;
	}
	
	public boolean isElementVisible(WebElement ele) {
		return ele.isDisplayed();
	}
	
	public boolean isElementEnabled(WebElement ele) {
		return ele.isEnabled();
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public String returnText(WebElement ele) {
		return ele.getText();
	}
	
}