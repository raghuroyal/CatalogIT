package pageObjects;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import testCases.TestCases;
import testSuites.TestSuites;

public class Login {
	JavascriptExecutor js;
	WebDriver driver;
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
	AddStudent addst;

	public Login(WebDriver driver) {
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
		addst = new AddStudent(driver);

	}

	public void launchApplication() {
		System.out.println("RC : Launch Application");
		driver.manage().window().maximize();
		driver.get(myXl.getCellData(TestCases.env, 0, 1));
	}

	public void loginToApplication_DataDriven() {
		System.out.println("RC : Login To Application");
		js = (JavascriptExecutor) driver;

		myUtils.returnElement("xpath", "//a[contains(text(),'Online Exam')]").click();
		ClickListener.moveToElement(myUtils.returnElement("xpath", "//span[contains(text(),'-- Choose One --')]"))
				.click().sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
		myUtils.returnElement("xpath", "//input[@value='SUBMIT']").click();
		myUtils.returnElement("xpath", "//a[contains(text(),'Online Exam')]").click();
		TS.staticWait(2);
		for (int i = 1; i < myXl.getRowCount(env); i++) {
			myUtils.returnElement("id", "username").sendKeys((myXl.getCellData(env, 1, i)));
			myUtils.returnElement("id", "password").sendKeys((myXl.getCellData(env, 2, i)));
			myUtils.returnElement("xpath", "//button[@value='signin']").click();
		}

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println("came");
		}

		addst.addStudent();
		

	}

	public void logoutFromApplication() {
		System.out.println("RC : Logout From Application");
		myUtils.returnElement("linkText", "SIGN-OFF").click();
	}

	public void closeApplication() {
		System.out.println("RC : Close Application");
	}
}
