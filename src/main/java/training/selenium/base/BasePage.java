package training.selenium.base;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import training.selenium.utilities.CommonUtilities;
import training.selenium.utilities.Log;;


public class BasePage {

	protected WebDriver driver;
	protected WebElement ele;
	protected List<WebElement> listEle;
	
	protected String projectPath = System.getProperty("user.dir");
	protected String pathScreenshots = projectPath + "/screenshots/";
	
	protected Properties propi;
	
	int attempts;
	
	public BasePage() {}
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebDriverWait wait(int secs) {
		return new WebDriverWait(driver,secs);
	}
	
	public WebElement getElement(By by, int secs) {
		attempts=0;
		while(attempts < 2) {
			try {
				ele = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.visibilityOfElementLocated(by));
				
				//return ele;
			}
			catch(TimeoutException | NoSuchElementException e) {
				Log.fatal("Element is not found during test execution... Attempt " + (attempts+1) + " ... " + e);
				CommonUtilities.takeScreenshot(driver, pathScreenshots, "TimeoutExceptionGetHeaderMessage");
				//throw(e);
			}
			attempts++;
		}
		return ele;
	}
	
	public WebElement getElementPresenceOfElementLocated(By by, int secs) {
		attempts=0;
		while(attempts < 2) {
			try {
				ele = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfElementLocated(by));
				//return ele;
				
			}
			catch(TimeoutException | NoSuchElementException e) {
				Log.fatal("Element is not found during test execution... Attempt " + (attempts+1) + " ... " + e);
				CommonUtilities.takeScreenshot(driver, pathScreenshots, "TimeoutExceptionGetHeaderMessage");
				//throw(e);
			}
			attempts++;
		}
		return ele;
	}
	
	public List<WebElement> getListElements(By by, int secs){
		attempts=0;
		while(attempts < 2) {
			try {
				listEle = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
				//return listele;
			}
			catch(TimeoutException | NoSuchElementException e) {
				Log.fatal("Element is not found during test execution... Attempt " + (attempts+1) + " ... " + e);
				CommonUtilities.takeScreenshot(driver, pathScreenshots, "TimeoutExceptionGetHeaderMessage");
				//throw(e);
			}
			attempts++;
		}
		return listEle;
	}
	
	public List<WebElement> getListPresenceOfElementsLocated (By by, int secs){
		attempts = 0;
		while(attempts < 2) {
			try {
				listEle = wait(secs)
						.ignoring(TimeoutException.class, NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				//return listEle; 
						
			}
			catch(TimeoutException | NoSuchElementException e) {
				Log.fatal("Element is not found during test execution... Attempt " + (attempts+1) + " ... " + e);
				CommonUtilities.takeScreenshot(driver, pathScreenshots, "TimeoutExceptionGetHeaderMessage");
				//throw(e);
			}
			attempts++;
		}
		return listEle;
	}
	
	public Select getSelectElement(By by, int secs) {
		return new Select(getElement(by,secs));
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