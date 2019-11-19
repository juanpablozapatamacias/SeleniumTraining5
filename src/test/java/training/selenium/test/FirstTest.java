package training.selenium.test;

import org.testng.annotations.Test;

import training.selenium.base.BaseTest;
import training.selenium.utilities.Log;

public class FirstTest extends BaseTest{

	@Test
	public void Test1() {
		Log.info("Test1 executed");
	}
	
	@Test
	public void Test2() {
		Log.info("Test2 executed");
	}
	
}
