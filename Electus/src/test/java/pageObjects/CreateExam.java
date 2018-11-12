package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import applicationUtilities.Action;
import applicationUtilities.ApplicationUtilities;
import applicationUtilities.SelectCheckBox;
import generalUtilities.Halt;
import generalUtilities.ReadProperties;
import generalUtilities.ReadXl;
import generalUtilities.SelectVisibleText;
import testSuites.TestSuites;

public class CreateExam {
	WebDriver driver;
	JavascriptExecutor js;
	ReadProperties myProp;
	ApplicationUtilities myUtils;
	ReadXl myXl;
	public static String env;
	public static String sname;
	List<WebElement> elements;
	Action parentAction;
	String text;
	WebElement element;
	List<WebElement> columns;
	Halt TS;
	Actions ClickListener;
	WebElement mySelectElement;
	Select dropdown;
	String ExamType;
	SelectCheckBox selectitem;
	SelectVisibleText vistext;

	public CreateExam(WebDriver driver) {
		this.driver = driver;
		myProp = new ReadProperties(TestSuites.configFilePath);
		env = myProp.readValue("environment");
		myUtils = new ApplicationUtilities(driver);
		myXl = new ReadXl(TestSuites.xlFilePath);
		parentAction = new Action(driver);
		TS = new Halt();
		ClickListener = new Actions(driver);
		selectitem = new SelectCheckBox(driver);
		vistext = new SelectVisibleText(driver);
	}

	public void examCreate() {
		// Create Exam Module
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 5000)");
		myUtils.returnElement("xpath", "//a[@href='load-SetExamform']//img[@id='rcorners2']").click();

		for (int i = 1; i < myXl.getRowCount(env); i++) {
			myUtils.returnElement("id", "examname").sendKeys(myXl.getCellData(env, 3, i));
			mySelectElement = driver.findElement(By.id("examtypeselectbox"));
			dropdown = new Select(mySelectElement);
			for (int i1 = 1; i1 < myXl.getRowCount(env); i1++) {
				ExamType = myXl.getCellData(env, 4, i1);
			}
			dropdown.selectByVisibleText(ExamType);
			js.executeScript("window.scrollBy(0, 200)");

			for (int i2 = 1; i < myXl.getRowCount(env); i++) {
				selectitem.selectInGroup(myXl.getCellData(env, 5, i2), myXl.getCellData(env, 6, i2),
						myXl.getCellData(env, 7, i2));
			}
			myUtils.returnElement("xpath", "//input[@value='Next']").click();
			TS.staticWait(2);
			selectitem.selectChoice(myXl.getCellData(env, 8, 1));

			mySelectElement = driver.findElement(By.id("classselect"));
			dropdown = new Select(mySelectElement);
			for (int i3 = 1; i3 < myXl.getRowCount(env); i3++) {
				dropdown.selectByVisibleText(myXl.getCellData(env, 9, i3));
			}
			// vistext.selectByText("classselect", "env", 9, 1);
			TS.staticWait(2);
			myUtils.returnElement("xpath", "//option[@value='800']").click();
			selectitem.selectChoice(myXl.getCellData(env, 11, 1));
			myUtils.returnElement("id", "noofqns_401").sendKeys(myXl.getCellData(env, 12, 1));
			js.executeScript("window.scrollBy(0, 400)");
			TS.staticWait(1);
			myUtils.returnElement("xpath", "//div[@id='topicsdiv']//input[1]").click();
			TS.staticWait(1);
			myUtils.returnElement("xpath", "//div[@id='subtopicsdiv']//input[1]").click();
			selectitem.selectChoice(myXl.getCellData(env, 13, 1));
			myUtils.returnElement("id", "nofoqus").sendKeys(myXl.getCellData(env, 12, 1));
			myUtils.returnElement("id", "nofoqusmarks").sendKeys(myXl.getCellData(env, 14, 1));
			myUtils.returnElement("id", "nofoqusnegmarks").sendKeys(myXl.getCellData(env, 15, 1));
			js.executeScript("window.scrollBy(0, 500)");
			myUtils.returnElement("xpath", "//input[@name=\"next1\"]").click();
			myUtils.returnElement("id", "slotstarttime").sendKeys(myXl.getCellData(env, 16, 1));
			myUtils.returnElement("id", "slotexamendtime").sendKeys(myXl.getCellData(env, 17, 1));
			myUtils.returnElement("xpath", "//input[@value=\"Submit\"]").click();

		}
	}
}
