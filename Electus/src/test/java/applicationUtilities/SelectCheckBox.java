package applicationUtilities;

import org.openqa.selenium.WebDriver;

import applicationUtilities.ApplicationUtilities;

public class SelectCheckBox {
	WebDriver driver;
	ApplicationUtilities myUtils;
	String test;
	String test1;
	String test2;
	String test3;

	public SelectCheckBox(WebDriver driver) {
		this.driver = driver;
		myUtils = new ApplicationUtilities(driver);
	}
	
	public void selectChoice(String Cbvalue) {
		test = "//input[@value=";
		test1 = Cbvalue+ "]";
		test1 = test.concat(test1);
		myUtils.returnElement("xpath", test1).click();
				
	}

	public void selectInGroup(String state,String location,String branch) {
		test = "//input[@value=";
		test1 = state + "]";
		test2 = location + "]";
		test3 = branch + "]";
		stateName();
		locationName();
		branchName();
	}
	public void stateName()
	{
		test1 = test.concat(test1);
		myUtils.returnElement("xpath", test1).click();
		
	}
	public void locationName()
	{
		test1 = test.concat(test2);
		myUtils.returnElement("xpath", test1).click();
	}
	public void branchName()
	{
		test1 = test.concat(test3);
		myUtils.returnElement("xpath", test1).click();
	}
}
