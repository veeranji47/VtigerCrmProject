package pratice.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsSampleTest {
	ExtentSparkReporter spark;
	ExtentReports reports ;
	ExtentTest test;
	
	
	@BeforeSuite
	public void configBS() {
		spark = new ExtentSparkReporter("./AdvancedReports/sampleReports.html");
		spark.config().setDocumentTitle("Sample Test Scripts");
		spark.config().setReportName("VTiger");
		spark.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS ", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version ", System.getProperty("os.version"));
		reports.setSystemInfo("JDK Version ", System.getProperty("java.specification.version"));
		reports.setSystemInfo("Author ", System.getProperty("user.name"));
		reports.setSystemInfo("Browser Name", System.getProperty(""));
	}
	@AfterSuite
	public void tearDownSuite() {
		reports.flush();
	}
	
	@Test
	public void createContactTest() {
		test = reports.createTest("contactWithOrgTest");
		test.log(Status.INFO, "launch browser ");
		test.log(Status.INFO, "Navigate the application");
		test.log(Status.INFO, "login application");
		test.log(Status.INFO, "open contact page");
		test.log(Status.INFO, "create contact");
		if("Veera".equals("Vera"))
			test.pass("Contact created");
		else
			test.fail("Contact is not created");
		test.log(Status.INFO, "logout the application");
	}
	@Test
	public void contactWithOrgTest() {
		test = reports.createTest("contactWithOrgTest");
		test.log(Status.INFO, "launch browser ");
		test.log(Status.INFO, "Navigate the application");
		test.log(Status.INFO, "login application");
		test.log(Status.INFO, "open contact page");
		test.log(Status.INFO, "create contact with organization name ");
		if("Veera".equals("Vera"))
			test.pass("Contact created");
		else
			test.fail("Contact is not created");
		test.log(Status.INFO, "logout the application");
	}
	@Test
	public void createContactWithDateTest() {
		test = reports.createTest("contactWithOrgTest");
		test.log(Status.INFO, "launch browser ");
		test.log(Status.INFO, "Navigate the application");
		test.log(Status.INFO, "login application");
		test.log(Status.INFO, "open contact page");
		test.log(Status.INFO, "create contact with support date and end date");
		if("Veera".equals("Vera"))
			test.pass("Contact created");
		else
			test.fail("Contact is not created");
		test.log(Status.INFO,"logout the application");
	}
	
	

}
