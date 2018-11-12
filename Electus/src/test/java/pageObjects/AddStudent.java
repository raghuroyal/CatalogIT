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

public class AddStudent {
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
	WebElement drpselect;
	Select dropdown;
	String ExamType;
	SelectCheckBox selectitem;
	SelectVisibleText vistext;
	CreateExam cexam;

	public AddStudent(WebDriver driver)
	{
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
		cexam = new CreateExam(driver);
	}
	public void addStudent()
	{
		// Add Student Module
				TS.staticWait(2);
				js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, 100)");
				myUtils.returnElement("xpath", "//a[@href='load-studentform']//img[@id='rcorners2']").click();
				myUtils.returnElement("id", "firstname").sendKeys(myXl.getCellData(env, 17, 1));
				myUtils.returnElement("id", "lastname").sendKeys(myXl.getCellData(env, 18, 1));
				myUtils.returnElement("id", "username").sendKeys(myXl.getCellData(env, 19,1));
				myUtils.returnElement("id", "password").sendKeys(myXl.getCellData(env, 20,1));
				drpselect = driver.findElement(By.id("statename"));
				dropdown = new Select(drpselect);
				dropdown.selectByVisibleText(myXl.getCellData(env, 22 , 1 ));
				myUtils.returnElement("xpath", "//a[contains(text(),'DASHBOARD')]").click();
				cexam.examCreate();
	}

}
