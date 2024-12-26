package pratice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pratice.genericUtilities.ExcelUtility;

public class DataProviderSimpleProgram {

	@Test(dataProvider = "getData")
	public void sampleTest(String frstName, String lastName) {
		
		System.out.println("First name : "+frstName +"\t" + "Last Name : "+lastName);
	}
	@DataProvider
	public Object[][] getData() {
		ExcelUtility xutil = new ExcelUtility();
		xutil.intiExcel();
		
		Object[][] data = new Object[3][2];
		// This is not for proper manner so keep the test data into excel read the data 
//		data[0][0] = xutil.getDataFromExcel("products", 1, 0);
//		data[0][1] = xutil.getDataFromExcel("products", 1, 1);;
//		
//		data[1][0] = xutil.getDataFromExcel("Names", 2, 0);;
//		data[1][1] = xutil.getDataFromExcel("Names", 2, 1);;
//		
//		data[2][0] = xutil.getDataFromExcel("Names", 3, 0);;
//		data[2][1] = xutil.getDataFromExcel("Names", 3, 1);;
		
		for(int i = 0; i < xutil.rowCount("Names"); i++) {
			data[i][0] = xutil.getDataFromExcel("Names", i+1, 0);
			data[i][1] = xutil.getDataFromExcel("Names", i+1, 1);
		}
		
		return data;
	}

}
