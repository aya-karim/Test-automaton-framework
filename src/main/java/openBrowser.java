import Report.Report;
import TestCases.SampleTestCase;
import TestCases.SampleTestCase4;

/**
 * @author akarim
 * 
 *         Main Class where All needed Testcases are used if Junit is not used.
 */
public class openBrowser {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Report report = Report.getInstance();

		SampleTestCase tc = new SampleTestCase();
		SampleTestCase4 tc4 = new SampleTestCase4();
		try {
			tc.run();
			tc4.run();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report.flush();
	}

}