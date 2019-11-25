package training.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import training.selenium.base.BaseTest;
import training.selenium.pageobjects.HomePage;
import training.selenium.pageobjects.LoginPage;
import training.selenium.pageobjects.TaskPage;
import training.selenium.utilities.Log;

public class FirstTest extends BaseTest{
	
	HomePage home = new HomePage(getDriver());
	LoginPage login = new LoginPage(getDriver());
	TaskPage task = new TaskPage(getDriver());

	@Test
	public void loginTest() {
		Log.info("Login Test execution...");
		home.clickLoginLink();
		login = home.switchToLoginFrame();
	}
	
	@Test(dependsOnMethods="loginTest")
	public void fillLoginCredentials() {
		Log.info("Fill login credentials execution...");
		login.fillUsername(propi.getProperty("cred.username"));
		login.fillPassword(propi.getProperty("cred.password"));
		task = login.submitLogin();
	}
	
	@Test(dependsOnMethods="fillLoginCredentials")
	public void getMessageLogin(){
		Log.info("Get Message Login...");
		Assert.assertEquals(task.getMessageHeader(), true, "Wrong login");
	}
	
	@Test(dependsOnMethods="getMessageLogin")
	public void addNewTasks() {
		Log.info("Add new tasks ...");
		Assert.assertEquals(task.addTask("Test task 1"), true);
	}
	
	
}
