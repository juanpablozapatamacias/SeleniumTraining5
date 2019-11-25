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
	
	By byMessageHeader = By.cssSelector(".empty-state-illustration");
	By byAddTaskLink= By.linkText("Add task");
	
	By byFillTaskText = By.cssSelector("div.main_content "
			+ "div.section_day "
			+ "ul.items.day_list.ul_today:nth-child(2) "
			+ "li.manager form.item_editor "
			+ "div.item_editor_details "
			+ "div.richtextinput.item_editor_input "
			+ "div.DraftEditor-root div.DraftEditor-editorContainer "
			+ "div.notranslate.public-DraftEditor-content div:nth-child(1) "
			+ "div:nth-child(1) "
			+ "div.public-DraftStyleDefault-block.public-DraftStyleDefault-ltr > span:nth-child(1)");
	
	By byAddTaskButton = By.cssSelector(".item_editor_submit");
	By byTaskElementsDetails = By.cssSelector(".task_item_details");

	public TaskPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean getMessageHeader() {
		Log.info("Message Header is displayed");
		ele = getElement(byMessageHeader,3);
		CommonUtilities.sleepByNSeconds(3);
		
		return ele.isDisplayed();
	}
	
	public boolean addTask(String value) {
		Log.info("Add a new task");
		eleAddTask = getElement(byAddTaskLink,3);
		eleAddTask.click();
		CommonUtilities.sleepByNSeconds(1);
		
		Log.info("Fill the name of the task");
		eleFillTaskText = getElement(byFillTaskText,3);
		eleFillTaskText.sendKeys(value);
		
		Log.info("Click to Add the new task");
		eleAddTaskButton = getElement(byAddTaskButton,3);
		eleAddTaskButton.click();
		
		Log.info("Get task elements details");
		eleDetails = getElement(byTaskElementsDetails,3);
		return eleDetails.isDisplayed();
	}
}
