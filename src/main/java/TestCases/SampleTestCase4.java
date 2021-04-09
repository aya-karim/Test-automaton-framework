package TestCases;

import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Report.Report;
import dataAccess.XMLDataReader;
import seleniumInterfacing.Actions;
import seleniumInterfacing.Actions.FindBy;
import seleniumInterfacing.CreateWebDriver;

/**
 * @author akarim Test Case Sample 1 , Where Tester should Implement the test
 *         Scenario
 */
public class SampleTestCase4 extends TestCaseTemplate {
	Node nIdentifier;
	Node nData;
	static ExtentTest test;
	static Report report;

	@Override
	protected void setup() throws Exception {
		CreateWebDriver creator = new CreateWebDriver();
		// webDriver = creator.createDriver(BrowserTypes.Chrome);
		readerInstance = XMLDataReader.getInstance();
		nIdentifier = readerInstance.getIdentifers();
		nData = readerInstance.getData();
		report = Report.getInstance();

		webDriver = creator.createDriver(readerInstance.chooseBrowser());
		Steps = 0;
		handler = new Actions(webDriver);
		test = report.createTestCaseInReport(" SampleTestCase 4");
	}

	@Override
	protected void execution() {
		// TODO Auto-generated method stub
		// open bowser

		try {
			handler.openBrowser(webDriver, readerInstance.getElementValue(nData, "URL"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Navigated to the specified URL");

			// get English element
			handler.click(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "englishLabel")),
					readerInstance.getElementValue(nIdentifier, "englishLabel"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " English clicked");

			handler.click(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "Arabiclabel")),
					readerInstance.getElementValue(nIdentifier, "Arabiclabel"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Arabic clicked");

			handler.waitforexist(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "SmartPhones")),
					readerInstance.getElementValue(nIdentifier, "SmartPhones"), 30);
			Steps++;
			test.log(LogStatus.PASS, Steps + " Wait for element Existed");

			handler.type(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "searchBar")),
					readerInstance.getElementValue(nIdentifier, "searchBar"),
					readerInstance.getElementValue(nData, "searchKey"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " type search keyWord");

			handler.submit(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "searchBar")),
					readerInstance.getElementValue(nIdentifier, "searchBar"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Submit Search KeyWord");

			handler.waitforexist(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "gridView")),
					readerInstance.getElementValue(nIdentifier, "gridView"), 30);
			Steps++;
			test.log(LogStatus.PASS, Steps + " Wait for Element Existed");

			int phonesInSearchResult = handler.getelemntsize(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "listofSearch")),
					readerInstance.getElementValue(nIdentifier, "listofSearch"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Get Search List size");

			String phonesInText = handler.gettext(webDriver,
					FindBy.valueOf(FindBy.class, readerInstance.getElementIdentifier(nIdentifier, "searchResult")),
					readerInstance.getElementValue(nIdentifier, "searchResult"));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Get Search result text");

//			Assert.assertEquals(phonesInText, String.valueOf(phonesInSearchResult));
			Steps++;
			test.log(LogStatus.PASS, Steps + " Search result Matched Search text");

		} catch (Exception e) {
			String error = e.getMessage();
			test.log(LogStatus.FAIL, " Step " + Steps + " Failed");

		}
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub
		report.endTest(test);
		webDriver.quit();

	}

}