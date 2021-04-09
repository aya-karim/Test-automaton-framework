package TestCases;

import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Report.Report;
import dataAccess.XMLDataReader;
import seleniumInterfacing.Actions;
import seleniumInterfacing.Actions.FindBy;
import seleniumInterfacing.CreateWebDriver;

public class facebookLogIn extends TestCaseTemplate {
	Node nIdentifier;
	Node nData;
	static ExtentTest test;
	static Report report;

	@Override
	protected void setup() throws Exception {
		// TODO Auto-generated method stub
		CreateWebDriver creator = new CreateWebDriver();
		readerInstance = XMLDataReader.getInstance();
		nIdentifier = readerInstance.getIdentifers();
		nData = readerInstance.getData();
		report = Report.getInstance();
		webDriver = creator.createDriver(readerInstance.chooseBrowser());
		handler = new Actions(webDriver);
		test = report.createTestCaseInReport("FaceBook Log In Test");
		Steps = 0;
	}

	@Override
	protected void execution() throws Exception {
		// TODO Auto-generated method stub
		handler.openBrowser(webDriver, readerInstance.getElementValue(nData, "URL"));
		Steps++;
		test.log(LogStatus.PASS, Steps + " Navigated to the specified URL");
		handler.type(webDriver,
				FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "emailField")),
				readerInstance.getElementValue(nIdentifier, "emailField"),
				readerInstance.getElementValue(nData, "userName"));
		Steps++;
		test.log(LogStatus.PASS, Steps + " Fill User Name");

		handler.type(webDriver,
				FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "passwordField")),
				readerInstance.getElementValue(nIdentifier, "passwordField"),
				readerInstance.getElementValue(nData, "password"));
		Steps++;
		test.log(LogStatus.PASS, Steps + " Fill Password");

		handler.click(webDriver, FindBy.Name, readerInstance.getElementValue(nIdentifier, "logInButton"));
		Steps++;
		test.log(LogStatus.PASS, Steps + " Press Log in");
	}

	@Override
	protected void cleanup() throws Exception {
		// TODO Auto-generated method stub
		report.endTest(test);
		webDriver.quit();

	}

}
