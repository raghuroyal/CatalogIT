package applicationUtilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import generalUtilities.ReadXl;


public class Action {
	WebDriver driver;
	String xlfilepath;
	ReadXl myXl;
	ApplicationUtilities myUtils;
	Actions myActions;

	public Action(WebDriver driver) {
		this.driver = driver;
		myXl = new ReadXl("TestData/TestData.xls");
		myUtils = new ApplicationUtilities(driver);
		myActions = new Actions(driver);
	}

	public void singleKeyDown(String propType, String propValue, String SheetName, int col, int row) {

		myActions.moveToElement(myUtils.returnElement(propType, propValue))
				.sendKeys(myXl.getCellData(SheetName, col, row)).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build()
				.perform();
	}

}
