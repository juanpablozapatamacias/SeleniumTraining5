package training.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import training.selenium.base.BasePage;
import training.selenium.utilities.CommonUtilities;
import training.selenium.utilities.Log;

public class TaskPage extends BasePage {
	
	WebElement ele;
	WebElement eleAddTask;
	WebElement eleFillTaskText;
	WebElement eleAddTaskButton;
	WebElement eleDetails;
	
	By byListHolder = By.cssSelector("#list_holder");
	By byAddTaskLink= By.cssSelector(".agenda_add_task > a");
	
	By byFillTaskText= By.xpath("//div[@role=\'textbox\']");
	
	By byAddTaskButton = By.cssSelector(".item_editor_submit");
	By byTaskElementsDetails = By.cssSelector(".task_item_details");

	public TaskPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean getListHolder() {
		Log.info("List Holder is displayed");
		ele = getElement(byListHolder,3);
		
		if (ele == null) return false;
		
		CommonUtilities.sleepByNSeconds(3);
		
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "TaskMessageHeader");
		return isElementVisible(ele);
	}
	
	public boolean addTask(String value) {
		Log.info("Add a new task");
		eleAddTask = getElement(byAddTaskLink,3);
		eleAddTask.click();
		CommonUtilities.sleepByNSeconds(1);
		
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "ClickTaskLink");
		
		Log.info("Fill the name of the task");
		eleFillTaskText = getElementPresenceOfElementLocated(byFillTaskText,3);
		eleFillTaskText.sendKeys(value);
		CommonUtilities.sleepByNSeconds(1);
		
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "FillTaskName");
		
		Log.info("Click to Add the new task");
		eleAddTaskButton = getElement(byAddTaskButton,3);
		eleAddTaskButton.click();
		CommonUtilities.sleepByNSeconds(1);
		
		Log.info("Get task elements details");
		eleDetails = getElement(byTaskElementsDetails,3);
		CommonUtilities.sleepByNSeconds(1);
		
		CommonUtilities.takeScreenshot(driver, pathScreenshots, "GetTaskElements");
		
		return isElementVisible(eleDetails);
	}
}
