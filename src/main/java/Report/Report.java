package Report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author akarim
 * 
 *         Read All needed Identifers and Data from XMl in one Static Single
 *         Document used across all Test Cases. Using Singletone Design Pattern.
 */
public class Report {
	static Report report = null;
	static ExtentReports SingleReport;

	/**
	 * Constructor that Open the XML Documents and initilize it for the first Time
	 */
	private Report() throws Exception {
		SingleReport = new ExtentReports(".//src//main//resources//ExtentReportResults.html");

	}

	/**
	 * Returns the Single Instance created or Create it if needed.
	 */
	public static Report getInstance() throws Exception {
		if (report == null) {
			synchronized (Report.class) {
				if (report == null) {
					report = new Report();
				}
			}
		}
		return report;
	}

	public static ExtentTest createTestCaseInReport(String TestCaseName) {
		return SingleReport.startTest(TestCaseName);
	}

	public static void endTest(ExtentTest test) {
		SingleReport.endTest(test);
	}

	public static void flush() {
		SingleReport.flush();
	}
}
