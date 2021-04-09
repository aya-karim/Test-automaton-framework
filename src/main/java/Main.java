import Report.Report;
import TestCases.GetProduct;
import TestCases.GetProductOfNotExistingType;
import TestCases.GetProductOfType;
import TestCases.SortProductByPrice;
import TestCases.facebookLogIn;

/**
 * @author akarim
 * 
 *         Main Class where All needed Testcases are used if Junit is not used.
 */
public class Main {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		Report report = Report.getInstance();

		facebookLogIn fbTest = new facebookLogIn();
		GetProduct product = new GetProduct();
		SortProductByPrice sort = new SortProductByPrice();
		GetProductOfType type = new GetProductOfType();
		GetProductOfNotExistingType noType = new GetProductOfNotExistingType();

		try {
			product.run();
			sort.run();
			type.run();
			noType.run();
			fbTest.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report.flush();
	}

}