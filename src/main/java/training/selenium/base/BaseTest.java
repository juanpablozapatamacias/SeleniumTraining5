package training.selenium.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import training.selenium.pageobjects.HomePage;
import training.selenium.utilities.CommonUtilities;
import training.selenium.utilities.Log;

public class BaseTest extends BasePage{
	
	protected WebDriver driver;
	protected Properties propi;
	
	private HomePage homePage;
	
	@BeforeSuite
	public void setup() {
		Log.startLog("Test is starting");
		
		Log.info("Opening the Driver");
		driver = getDriver();
		
		Log.info("Deleting Cookies");
		driver.manage().deleteAllCookies();
		
		Log.info("Maximizing the browser window");
		driver.manage().window().maximize();
		
		Log.info("Applying the implicit wait during 90 seconds");
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		Log.info("Open the web site to test");
		driver.navigate().to("https://todoist.com");
		
		Log.info("Set the Credentials propeties");
		propi = CommonUtilities.loadProperties(System.getProperty("user.dir") + "/src/main/resources/creds.properties");
		
		Log.info("Taking first screenshot");
		CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "/screenshots/", "OpenURL");
		
		Log.info("Initialize the Page Objects");
		initBaseTest();
	}
	
	@AfterSuite
	public void teardown() {
		try {
			Log.info("Finalize the Page Objects");
			
			resetBaseTest();
			Log.endLog("Test is ending");
		}
		catch(Exception e) {
			
		}
		finally {
			if(driver !=null) {
				propi = null;
				driver.quit();
				driver = null;
			}
		}
	}
	
	public void initBaseTest() {
		homePage = new HomePage(driver);
	}
	
	public void resetBaseTest() {
		homePage = null;
	}

}
