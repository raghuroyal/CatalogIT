package applicationUtilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ApplicationUtilities {
	List<WebElement> elements;
	WebDriver driver;

	public ApplicationUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void selectElementFromDropDown(String propType, String propVal, int index) {
		new Select(returnElement(propType, propVal)).selectByIndex(index);
	}

	public void selectElementFromDropDown(String propType, String propVal, String text) {
		new Select(returnElement(propType, propVal)).selectByVisibleText(text);
	}

	public void selectAnElmentFromCollection(String propType, String propVal, String attType, String attVal) {
		elements = returnCollection(propType, propVal);
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getAttribute(attType);
			// System.out.println("innerText is :" + text);
			if (text.equals(attVal)) {
				elements.get(i).click();
				break;
			}
		}
	}

	public WebElement returnAnElmentFromCollection(String propType, String propVal, String attType, String attVal) {
		elements = returnCollection(propType, propVal);
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getAttribute(attType);
			// System.out.println("innerText is :" + text);
			if (text.equals(attVal)) {
				element = elements.get(i);
				break;
			}
		}
		return element;
	}

	WebElement element;

	public List<WebElement> returnCollection(String propType, String propVal) {
		if (propType.equals("cssSelector"))
			elements = driver.findElements(By.cssSelector(propVal));
		else if (propType.equals("className"))
			elements = driver.findElements(By.className(propVal));
		else if (propType.equals("id"))
			elements = driver.findElements(By.id(propVal));
		else if (propType.equals("name"))
			elements = driver.findElements(By.name(propVal));
		else if (propType.equals("linkText"))
			elements = driver.findElements(By.linkText(propVal));
		else if (propType.equals("partialLinkText"))
			elements = driver.findElements(By.partialLinkText(propVal));
		else if (propType.equals("xpath"))
			elements = driver.findElements(By.xpath(propVal));
		return elements;
	}

	public WebElement returnElement(WebDriver driver, String propType, String propVal) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (propType.equals("cssSelector"))
			element = driver.findElement(By.cssSelector(propVal));
		else if (propType.equals("className"))
			element = driver.findElement(By.className(propVal));
		else if (propType.equals("id"))
			element = driver.findElement(By.id(propVal));
		else if (propType.equals("name"))
			element = driver.findElement(By.name(propVal));
		else if (propType.equals("linkText"))
			element = driver.findElement(By.linkText(propVal));
		else if (propType.equals("partialLinkText"))
			element = driver.findElement(By.partialLinkText(propVal));
		else if (propType.equals("xpath"))
			element = driver.findElement(By.xpath(propVal));
		if (element.isEnabled())
			return element;
		else
			return null;
	}

	public WebElement returnElement(String propType, String propVal) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (propType.equals("cssSelector"))
			element = driver.findElement(By.cssSelector(propVal));
		else if (propType.equals("className"))
			element = driver.findElement(By.className(propVal));
		else if (propType.equals("id"))
			element = driver.findElement(By.id(propVal));
		else if (propType.equals("name"))
			element = driver.findElement(By.name(propVal));
		else if (propType.equals("linkText"))
			element = driver.findElement(By.linkText(propVal));
		else if (propType.equals("partialLinkText"))
			element = driver.findElement(By.partialLinkText(propVal));
		else if (propType.equals("xpath"))
			element = driver.findElement(By.xpath(propVal));
		if (element.isEnabled())
			return element;
		else
			return null;
	}

	public void selectAnElmentFromCollection() {
		elements = driver.findElements(By.cssSelector(".RveJvd"));
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getAttribute("innerText");
			// System.out.println("innerText is :" + text);
			if (text.equals("NEXT")) {
				elements.get(i).click();
				break;
			}
		}
	}

}
