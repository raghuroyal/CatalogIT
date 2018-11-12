package testCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import generalUtilities.ReadProperties;
import generalUtilities.ReadXl;
import generalUtilities.Snipper;
import pageObjects.Login;
import testSuites.TestSuites;

public class TestCases {
	WebDriver driver;
	Login myLogin;
	ReadXl myXl;
	public static String env;
	public static String sname;
	ReadProperties myProp;
	boolean result;
	String screenshotfilepath;
	Snipper snip;

	public TestCases() {
		myProp = new ReadProperties(TestSuites.configFilePath);
		env = myProp.readValue("environment");
		sname = myProp.readValue("Filesheetname");
		myXl = new ReadXl(TestSuites.xlFilePath);
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\browser\\Chrome-2.40\\chromedriver.exe");
		driver = new ChromeDriver();
		myLogin = new Login(driver);
		snip = new Snipper(driver);
	}

//	@Test
//	public void readXlData() throws BiffException, IOException {
//		FileInputStream myFile = new FileInputStream("TestData/TestData.xls");
//		Workbook myBook = Workbook.getWorkbook(myFile);
//		Sheet mySheet = myBook.getSheet("DEV");
//		int cc = mySheet.getColumns();
//		System.out.println("Column count :" + cc);
//		int rc = mySheet.getRows();
//		System.out.println("Row count :" + rc);
//		System.out.println("URL :" + mySheet.getCell(0, 1).getContents());
//		System.out.println("URL :" + mySheet.getCell(1, 1).getContents());
//		System.out.println("URL :" + mySheet.getCell(2, 1).getContents());
//	}

	public void dataDrivenTest() {

		for (int i = 1; i < myXl.getRowCount(env); i++) {
			myLogin.launchApplication();
			myLogin.loginToApplication_DataDriven();
		}
	}

	public String takeScreenShot(String name) {
		return snip.takeErrorScreenShot(name);
	}

	

	@Test
	public boolean bookBusAndHotel() {
		System.out.println("Test Case : bookBusAndHotel");

		try {
			myLogin.launchApplication();
			// child.log("");
			

			result = true;
		} catch (Exception e) {
			result = false;
		}
		return true;

	}

	public void bookHotelAndBus() {
		System.out.println("Test Case : bookHotelAndBus");
		myLogin.launchApplication();
		myLogin.logoutFromApplication();
		myLogin.closeApplication();
	}

}
