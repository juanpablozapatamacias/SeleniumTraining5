package training.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import training.selenium.base.BasePage;
import training.selenium.utilities.CommonUtilities;
import training.selenium.utilities.Log;

public class HomePage extends BasePage {
	
	WebElement ele;
	WebElement frame;
	
	By byLoginLink = By.cssSelector(".xC29iLDJOfZeDm_x5o7DH");
	By byLoginPageFrame = By.cssSelector("._3ga5XTxZEFAiG-Q7KQfQnT");
	
	public HomePage (WebDriver driver) {
		super(driver);
	}
	
	public void clickLoginLink() {
		Log.info("Click on login link");
		ele = getElement(byLoginLink, 3);
		ele.click();
		CommonUtilities.sleepByNSeconds(1);
	}
	
	public LoginPage switchToLoginFrame() {
		Log.info("Switch to Login page in a frame");
		frame = getElement(byLoginPageFrame,3);
		
		driver.switchTo().frame(frame);
		CommonUtilities.sleepByNSeconds(1);
		
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "HomePage");
		return new LoginPage(driver);
	}
	

}
