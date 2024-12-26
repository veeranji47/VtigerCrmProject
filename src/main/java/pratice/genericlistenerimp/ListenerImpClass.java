package pratice.genericlistenerimp;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.genericUtilities.WebDriverUtility;

import pratice.basetest.BaseClass;

public class ListenerImpClass implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public static ExtentTest stest;
	
	@Override
	public void onStart(ISuite suite) {
		spark = new ExtentSparkReporter("./AdvancedReports/reports.html");
		spark.config().setDocumentTitle("Vtiger CRM project");
		spark.config().setReportName("Vtiger");
		spark.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version", System.getProperty("os.version"));
		reports.setSystemInfo("JDK Version", System.getProperty("java.specification.version"));
		reports.setSystemInfo("Author", System.getProperty("user.name"));
		
	}

	@Override
	public void onFinish(ISuite suite) {
		reports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.INFO, methodName + " is started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + " is passed");
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.addScreenCaptureFromBase64String(new WebDriverUtility().getScreenshotPage(BaseClass.sdriver, methodName, BaseClass.sjutil));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + " is skipped");
	}
	 
	
	
	

}
