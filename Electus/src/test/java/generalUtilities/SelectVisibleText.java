package generalUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testSuites.TestSuites;

public class SelectVisibleText {
	WebDriver driver;
	WebElement mySelectElement;
	Select dropdown;
	ReadXl myXl;
	public static String env;
	ReadProperties myProp;
	String ExamType;

	public SelectVisibleText(WebDriver driver) {
		this.driver = driver;
		myXl = new ReadXl(TestSuites.xlFilePath);
		myProp = new ReadProperties(TestSuites.configFilePath);
		env = myProp.readValue("environment");
	}
	
	public void selectByText(String idname,String name,int coll,int roww) {
		mySelectElement = driver.findElement(By.id(idname));
		dropdown = new Select(mySelectElement);
		System.out.println("Before");
		dropdown.selectByVisibleText(myXl.getCellData(name, coll, roww));
	}
}
