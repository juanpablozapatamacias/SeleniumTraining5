package training.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import training.selenium.base.BasePage;
import training.selenium.utilities.CommonUtilities;
import training.selenium.utilities.Log;

public class LoginPage extends BasePage {
	
	
	WebElement ele;
	
	By byUsernameField = By.cssSelector("#email");
	By byPasswordField = By.cssSelector("#password");
	By bySubmit = By.cssSelector("body:nth-child(2) "
			+ "div.login_singup_form.content:nth-child(1) "
			+ "form:nth-child(4) > button.submit_btn.ist_button.ist_button_red.sel_login:nth-child(10)");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void fillUsername(String value) {
		Log.info("Filling the username");
		ele = getElement(byUsernameField,3);
		ele.sendKeys(value);
		CommonUtilities.sleepByNSeconds(1);
	}
	
	public void fillPassword(String value) {
		Log.info("Filling the password");
		ele = getElement(byPasswordField,3);
		ele.sendKeys(value);
		CommonUtilities.sleepByNSeconds(1);
	}
	
	public TaskPage submitLogin() {
		Log.info("Submit the login");
		ele = getElement(bySubmit,3);
		ele.submit();
		CommonUtilities.sleepByNSeconds(1);
		
		Log.info("Taking screenshot in Login frame");
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "LoginForm");
		
		Log.info("Switch to default frame");
		driver.switchTo().defaultContent();
		CommonUtilities.sleepByNSeconds(1);
		
		return new TaskPage(driver);
	}
	
}
