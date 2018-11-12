package testSuites;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import generalUtilities.ReadProperties;
import testCases.TestCases;

public class TestSuites {
	TestCases myTc;
	ReadProperties myProp;
	public static String configFilePath;
	public static String xlFilePath;
	public static String excelFilePath;
	ExtentReports myExtent;
	ExtentTest parentTest;
	ExtentTest child1;
	ExtentTest child2;
	boolean flag;

	public TestSuites() {
		configFilePath = "TestData/TestData.properties";
		myProp = new ReadProperties("TestData/TestData.properties");
		xlFilePath = myProp.readValue("xlFilePath");
		// excelFilePath = myProp.readValue(excelFilePath);
		myTc = new TestCases();
		myExtent = new ExtentReports("TestReports/MyReports.html");
		parentTest = myExtent.startTest("Smoke Test", "Build Validation Test");
		child1 = myExtent.startTest("BookFlightAndCar");

	}

	@Test
	public void myDataDrivenTest() {
		myTc.dataDrivenTest();
	}

	@Test
	public void runUserChoice() {
		if (myProp.readValue("testingType").equalsIgnoreCase("SMOKE")) {
			smokeSuite();
		} else if (myProp.readValue("testingType").equalsIgnoreCase("REGRESSION")) {
			regressionSuite();
		} else {
			smokeSuite();
		}
	}

	@Test
	public void smokeSuite() {
		System.out.println("Test Suite : Smoke");
		parentTest.appendChild(child1);
		logReport(child1, myTc.bookBusAndHotel(), "FlightCar");
		flag = myTc.bookBusAndHotel();

		if (flag) {
			child1.log(LogStatus.PASS, "FlightAndCar", "Vacation Planned with Filght and Car");
		} else {
			child1.log(LogStatus.FAIL, "Failed to Book Filght and Car",
					child1.addScreenCapture(myTc.takeScreenShot("FlightCar")));
		}

		parentTest.log(LogStatus.INFO, "Execution Done");
		myExtent.flush();
		myExtent.endTest(parentTest);

	}

	public void logReport(ExtentTest myTest, boolean status, String description) {
		if (status) {
			myTest.log(LogStatus.PASS, description, " : Successfull");
		} else {
			myTest.log(LogStatus.FAIL, description, myTest.addScreenCapture(myTc.takeScreenShot(description)));
		}
	}

	public void regressionSuite() {
		System.out.println("Test Suite : Regression");

	}

}
