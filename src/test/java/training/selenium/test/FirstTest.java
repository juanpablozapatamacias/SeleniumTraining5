package training.selenium.test;

import org.testng.annotations.Test;
import org.testng.Assert;

import training.selenium.base.BaseTest;
import training.selenium.pageobjects.HomePage;
import training.selenium.pageobjects.LoginPage;
import training.selenium.pageobjects.TaskPage;
import training.selenium.utilities.Log;
import training.selenium.utilities.reports.ExtentTestManager;

import java.lang.reflect.Method;

public class FirstTest extends BaseTest{
	
	HomePage home = new HomePage(getDriver());
	LoginPage login = new LoginPage(getDriver());
	TaskPage task = new TaskPage(getDriver());

	@Test(description="Access to the web site home page to open Login dialog", priority = 0)
	public void loginTest(Method method) {
		Log.info("Login Test execution...");
	
		ExtentTestManager.startTest(method.getName(), "Access to the web site home page to open Login dialog");
		ExtentTestManager.getTest().setDescription("Access to the web site home page to open Login dialog");
		
		home.clickLoginLink();
		Assert.assertEquals(home.isLoginFrameVisible(), true, "Login frame is nor visible");
		
		login = home.switchToLoginFrame();
	}
	
	@Test(dependsOnMethods="loginTest", description="Login to the application execution", priority = 1)
	public void fillLoginCredentials(Method method) {
		Log.info("Fill login credentials execution...");
		
		ExtentTestManager.startTest(method.getName(), "Login to the application execution");
		ExtentTestManager.getTest().setDescription("Login to the application execution");
		
		login.fillUsername(propi.getProperty("cred.username"));
		login.fillPassword(propi.getProperty("cred.password"));
		task = login.submitLogin();
		
		Log.info("Credentials were submitted ...");
		Assert.assertEquals(task.getListHolder(), true, "Login is not successfully, list holder is not displayed...");
	}
	
	@Test(dependsOnMethods="fillLoginCredentials", description="Add new task in the application")
	public void addNewTasks(Method method) {
		Log.info("Add new tasks ...");
		
		ExtentTestManager.startTest(method.getName(), "Add new task in the application");
		ExtentTestManager.getTest().setDescription("Add new task in the application");
		
		Assert.assertEquals(task.addTask("Test task 3"), false, "Add task execution is failed...");
	}
	
	
}
