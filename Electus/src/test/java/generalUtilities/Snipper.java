package generalUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Snipper {
	String screenshotfilepath;
	WebDriver driver;

	public Snipper(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public String takeErrorScreenShot(String fname) {
		String path = System.getProperty("user.dir");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotfilepath = path + "\\ScreenShots\\" + fname + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(screenshotfilepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scrFile = null;
		System.out.println(screenshotfilepath);
		return screenshotfilepath;
	}

}
